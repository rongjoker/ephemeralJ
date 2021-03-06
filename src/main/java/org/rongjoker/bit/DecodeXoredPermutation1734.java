package org.rongjoker.bit;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 05/11/2021
 * 1734. 解码异或后的排列 https://leetcode-cn.com/problems/decode-xored-permutation/
 * 它是前 n 个正整数的排列，这句话是关键，由于异或运算满足交换律，可以直接运算出所有值的异或叠加
 *
 */
public class DecodeXoredPermutation1734 {

    @Test
    public void test1734(){

        System.out.println(Arrays.toString(decode(new int[]{6,5,4,6})));


    }

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] ans = new int[n];
        // 求得除了 ans[0] 的所有异或结果
        int less1 = 0;
        for (int i = 1; i < n; i += 2) less1 ^= encoded[i];
        // 求得 ans 的所有异或结果(它是前 n 个正整数的排列)
        int all = 0;
        for (int i = 1; i <= n; i++) all ^= i;
        ans[0] = less1 ^ all;//相异或运算，求得第一个数值

        for (int i = 0; i < n-1; i++) {
            ans[i+1] = encoded[i] ^ ans[i];
        }
        return ans;
    }

}
