package org.rongjoker.dp.pack01;

import org.junit.Test;

/**
 * 01背包，最原始的方法
 * 01背包的条件是每个物品最多只有1个，这个是很关键的一个条件，极大的简化了问题的难度，相当于一个最原始的问题
 * @date 01/05/2021
 */
public class ZeroOnePackage {

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

        //数组初始化都是0，不必要初始化
        int[][] dp = new int[N + 1][W + 1];


        for (int i = 1; i < N + 1; i++) {

            for (int j = 1; j < W + 1; j++) {

                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }


        return dp;
    }
}
