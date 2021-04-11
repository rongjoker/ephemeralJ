package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 04/11/2021
 * 474. 一和零  https://leetcode-cn.com/problems/ones-and-zeroes/
 * <p>
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * dp类问题，可以用二维背包解决
 */
public class OnesAndZeroes474 {

    @Test
    public void test474() {

        String[] strs = new String[]{"10","0001","111001","1","0"};
        System.out.println(findMaxFormOptimize(strs, 5, 3));
    }


    /**
     * 自己根据01背包改造，速度非常慢，
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;

        int[] counts;
        int[][][] dp = new int[m + 1][n + 1][len + 1];

        for (int k = 0; k < len; k++) {
            counts = countzeroesones(strs[k]);

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (counts[0] <= i && counts[1] <= j) {
                        dp[i][j][k + 1] = Math.max(dp[i][j][k], dp[i - counts[0]][j - counts[1]][k] + 1);
                    } else
                        dp[i][j][k + 1] = dp[i][j][k];
                }
            }
        }

        return dp[m][n][len];
    }

    //类似01背包，降维，从后向前计算，去掉无意义的计算
    public int findMaxFormOptimize(String[] strs, int m, int n) {

        int[] counts;
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            counts = countzeroesones(str);

            for (int i = m; i >= counts[0]; i--) {
                for (int j = n; j >= counts[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - counts[0]][j - counts[1]] + 1);
                }
            }
        }

        return dp[m][n];
    }



    public int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
}
