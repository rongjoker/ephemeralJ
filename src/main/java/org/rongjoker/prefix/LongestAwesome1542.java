package org.rongjoker.prefix;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 07/01/2021
 * <p>
 * 1542. 找出最长的超赞子字符串 https://leetcode-cn.com/problems/find-longest-awesome-substring/
 * <p>
 * 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
 * <p>
 * 「超赞子字符串」需满足满足下述两个条件：
 * <p>
 * 该字符串是 s 的一个非空子字符串
 * 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
 *
 *
 *
 *
 *
 */
public class LongestAwesome1542 {


    @Test
    public void test1542() {

        System.out.println(longestAwesome("3242415"));
        System.out.println(longestAwesome("12345678"));
        System.out.println(longestAwesome("213123"));
        System.out.println(longestAwesome("00"));
        System.out.println(longestAwesome("373781"));

    }


    /**
     *
     *
     * 状态压缩，标记第一次出现的位置
     * @param s
     * @return
     */
    public int longestAwesome(String s) {

        int len = s.length();
        if (len == 1) return 1;
        int ans = 1;
        int mask = 0;

        int[] index = new int[1 << 10];
        Arrays.fill(index, Integer.MAX_VALUE);
        index[0] = -1;//相当于偶数初始化

        for (int i = 0; i < len; i++) {
            mask ^= (1 << (s.charAt(i) - '0'));
            if (mask == 0) ans = i + 1;//是0说明是偶数，直接就是最大值
            if (index[mask] != Integer.MAX_VALUE) ans = Math.max(ans, i - index[mask]);//偶数-前面是偶数的部分，就是最大长度
            for (int j = 0; j < 10; ++j) {
                ans = Math.max(ans, i - index[mask ^ (1 << j)]);//偶数-前面是(偶数/奇数)的部分，就是最大长度,差一位
            }
            if (index[mask] == Integer.MAX_VALUE) index[mask] = i;
        }

        return ans;


    }
}
