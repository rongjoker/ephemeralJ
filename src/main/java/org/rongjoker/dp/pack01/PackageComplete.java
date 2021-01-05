package org.rongjoker.dp.pack01;

import org.junit.Test;

/**
 * 完全背包，类似01背包最优化的方案，从左向右操作,非常精巧
 * @date 01/05/2021
 */
public class PackageComplete {

    @Test
    public void testCompletePackage() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{0,2, 4, 3};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{0,5, 6, 7};
        //总物品数
        int N = 3;
        //背包重量
        int V = 7;

        int result[] = maxValue(N, V, w, v);
        System.out.println(result[V]);


    }

    public static int[] maxValue(int N, int W, int w[], int v[]) {

        //数组初始化都是0，不必要初始化
        int[] dp = new int[W + 1];

        for (int i = 1; i < N + 1; i++) {

            for (int j = 0; j <W+1; j++) {//反向操作
                if (j >= w[i]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }

            }

        }


        return dp;
    }
}
