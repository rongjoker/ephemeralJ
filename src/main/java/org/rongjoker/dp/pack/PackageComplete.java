package org.rongjoker.dp.pack;

import org.junit.Test;

/**
 * 完全背包，类似01背包最优化的方案，从左向右操作,非常精巧
 * 完全背包也是很多dp题目的母题，如322. 零钱兑换，983. 最低票价
 * @date 01/05/2021
 */
public class PackageComplete {

    @Test
    public void testCompletePackage() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{3,  5};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{3, 5};

        //背包重量
        int V = 8;

        int result[] = maxValueFull( V, w, v);
        System.out.println(result[V]);


    }


    /**
     * 用01背包的思路解完全背包
     * 比如有物品1无数个，物品2无数个，背包容量7
     * 可以把物品1，物品1，物品1，物品1...看作01背包中的每个不同的物品，每一个都是从自己的上次转移过来的
     * @param V 背包容量
     * @param w 物品重量
     * @param v 物品价值
     * @return
     */
    public static int[] maxValue01(int V, int w[], int v[]) {

        int[] dp = new int[V + 1];

        int n = w.length;

        for (int i = 0; i < n; i++) {

            for (int j = w[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }

        }

        return dp;
    }


    /**
     * 必须装满
     * @param V
     * @param w
     * @param v
     * @return
     */
    public static int[] maxValueFull(int V, int w[], int v[]) {

        int[] dp = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            dp[i] = -1;
        }

        int n = w.length;

        for (int i = 0; i < n; i++) {

            for (int j = w[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - w[i]]!=-1 ? (dp[j - w[i]] + v[i]):-1);
            }

        }

        return dp;
    }





}
