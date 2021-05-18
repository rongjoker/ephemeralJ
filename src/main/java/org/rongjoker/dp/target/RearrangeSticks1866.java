package org.rongjoker.dp.target;

import org.junit.Test;


/**
 * @date 05/18/2021
 * 1866. 恰有 K 根木棍可以看到的排列数目 https://leetcode-cn.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible/
 */
public class RearrangeSticks1866 {


    @Test
    public void test1866() {
//        System.out.println(rearrangeSticks(23,12));
//        System.out.println(rearrangeSticks(3, 2));
        System.out.println(rearrangeSticks(23,12));
        System.out.println(rearrangeSticks2(23,12));
        //822131484
        //647427950
    }


    //滚动数组版本
    public int rearrangeSticks(int n, int k) {
        int mod = (int) (Math.pow(10, 9) + 7);
        long[][] dp = new long[2][k + 1];
        dp[0][0] = 1L;
        int cur = 0, prev;
        for (int i = 0; i < n; ++i) {
            cur = (i + 1) % 2;
            prev = i % 2;
            for (int j = 0; j < k; ++j) {
                dp[cur][j + 1] = (dp[prev][j] + dp[prev][j + 1]*i) % mod;
            }
            dp[0][0] = 0L;//滚动数组有bug，重复使用，后面应该回归为0
        }
        return (int) dp[cur][k];

    }


    //未优化空间的版本

    /**
     *                 //分2种情况，第一种，最大的数据在最右边，也就是肯定可以被看见，只需要考虑n-1的数据看到k-1的情况即可；
     *                 // 第二种，最大的数据不在最右边，肯定不会被看见，需要考虑n-1的数据看到k的情况,这种情况下,最右边有k-1种可能
     *                 //两者相加即可
     *                 //就像其他dp情况一样，dp[0][0]设置为1作为初始化
     *                 所以状态转移的过程就是考虑最右侧数字的过程
     * @param n
     * @param k
     * @return
     */
    public int rearrangeSticks2(int n, int k) {
        int mod = (int) (Math.pow(10, 9) + 7);
        long[][] dp = new long[n+1][k + 1];
        dp[0][0] = 1L;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                dp[i+1][j + 1] = (dp[i][j] + dp[i][j + 1]*i) % mod;
            }
        }
        return (int) dp[n][k];

    }
}
