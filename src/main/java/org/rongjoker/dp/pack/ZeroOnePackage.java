package org.rongjoker.dp.pack;

import org.junit.Test;

/**
 * 01背包，最原始的方法
 * 01背包的条件是每个物品最多只有1个，这个是很关键的一个条件，极大的简化了问题的难度，相当于一个最原始的问题
 *
 * @date 01/05/2021
 */
public class ZeroOnePackage {

    @Test
    public void test01package() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{0, 2, 3, 4};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{0, 5, 6, 7};
        //总物品数
        int N = 3;
        //背包重量
        int V = 7;

//        int result[][] = maxValue(N, V, w, v);
//        System.out.println(result[N][V]);

        System.out.println(maxValue3(N, V, w, v));


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
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);//从上一行延续
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }


        return dp;
    }

    /**
     * 优化为2列数组，利用对2取模来取对应的行
     *
     * @param N
     * @param W
     * @param w
     * @param v
     * @return
     */
    public static int[][] maxValue2(int N, int W, int w[], int v[]) {

        for (int i = 0; i < 3; i++) {//打印出来最大是2
            System.out.println("i:" + i);
        }

        //数组初始化都是0，不必要初始化
        int[][] dp = new int[2][W + 1];


        for (int i = 1; i < N + 1; i++) {

            int previous = (i + 1) % 2;
            int current = i % 2;

            for (int j = 1; j < W + 1; j++) {

                if (j >= w[i]) {
                    dp[current][j] = Math.max(dp[previous][j], dp[previous][j - w[i]] + v[i]);
                } else {
                    dp[current][j] = dp[previous][j];
                }
            }

        }


        return dp;
    }

    /**
     * 优化成一维数组
     * <p>
     * 01背包的特殊性，可以从右边向左边去计算
     * @param N
     * @param W
     * @param w
     * @param v
     * @return
     */
    public static int maxValue3(int N, int W, int w[], int v[]) {

        //数组初始化都是0，不必要初始化
        int[] dp = new int[W + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = W; j >= w[i]; j--) {//极限优化，省去小于当前数字的那些循环,从后向前
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }

        return dp[W];
    }
}
