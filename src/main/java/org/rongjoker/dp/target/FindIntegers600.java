package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 09/11/2021
 * 600. 不含连续1的非负整数
 * https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
 * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
 *
 */
public class FindIntegers600 {


    @Test
    public void test600(){
        System.out.println(findIntegers(5));
    }


    public int findIntegers(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }

        return res;
    }

}
