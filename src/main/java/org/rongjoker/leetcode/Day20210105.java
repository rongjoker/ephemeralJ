package org.rongjoker.leetcode;

import org.junit.Test;

/**
 * 01背包
 */
public class Day20210105 {


    @Test
    public void test01package() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{0,2, 4, 3};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{0,5, 6, 7};
        //总物品数
        int N = 3;
        //背包重量
        int V = 7;

        int result[][] = maxValue(N, V, w, v);
        System.out.println(result[N][V]);


    }

    public static int[][] maxValue(int N, int W, int w[], int v[]) {

        for (int i = 0; i < 3; i++) {//打印出来最大是2
            System.out.println("i:" + i);
        }

        int[][] dp = new int[N + 1][W + 1];

        //数组初始化都是0，不必要初始化
//        for (int i = 0; i < W; i++) {
//            System.out.println(res[0][i]);
//        }

        for (int i = 1; i < N + 1; i++) {

            for (int j = 1; j < W + 1; j++) {

                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }

//        for (int i = 0; i < N; i++) {
//
//            for (int j = 0; j < W; j++) {
//
//                if (j >= w[i]) {
//                    res[i + 1][j + 1] = Math.max(res[i][j + 1], res[i][j + 1 - w[i]] + v[i]);
//                } else {
//                    res[i + 1][j + 1] = res[i][j + 1];
//                }
//            }
//
//        }

        return dp;
    }







    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 可以转换为背包问题
     */
    @Test
    public void testLeetCode416(){






    }


}
