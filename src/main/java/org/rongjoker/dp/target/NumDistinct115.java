package org.rongjoker.dp.target;

import java.util.Arrays;

/**
 * @date 08/30/2021
 * 115. 不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumDistinct115 {


    public int numDistinct(String s, String t) {
        int len_s = s.length(), len_t = t.length();
        if (len_s < len_t) {
            return 0;
        }
        if (len_s == len_t) {
            if (s.equals(t)) {
                return 1;
            } else {
                return 0;
            }
        }
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        int[][] dp = new int[len_t + 1][len_s + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < len_t; i++) {
            for (int j = 0; j < len_s; j++) {
                if (ss[j] == tt[i]) {
                    dp[i + 1][j + 1] = dp[i][j];
                }else dp[i + 1][j + 1] = Math.max(dp[i+1][j],dp[i][j+1]);
            }

        }


        return -1;


    }

}
