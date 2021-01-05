package org.rongjoker.dp.pack01;

import org.junit.Test;

/**
 * 01背包，要求填充满背包
 * 将dp全部赋值为-1，完全一致才填充数据（比如初始的0就是0，递归的时候2=2）
 * 也可以将dp全部赋值为-无穷，程序会很简单
 *
 * @date 01/05/2021
 */
public class ZeroOnePackageFull {

    @Test
    public void test01packageFull() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{0, 2, 4, 3};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{0, 5, 6, 7};
        //总物品数
        int N = 3;
        //背包重量
        int V = 7;

        int result[][] = maxValue(N, V, w, v);
        System.out.println(result[N][V]);


    }

    public static int[][] maxValue(int N, int W, int w[], int v[]) {

        //数组初始化都是0，不必要初始化
        int[][] dp = new int[N + 1][W + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }


        for (int i = 1; i < N + 1; i++) {

            for (int j = 1; j < W + 1; j++) {

                if (dp[i - 1][j] != -1) {

                    if (j >= w[i] && dp[i - 1][j - w[i]] != -1) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }

                } else if (j >= w[i] && dp[i - 1][j - w[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - w[i]] + v[i];
                }

            }

        }


        return dp;
    }
}
