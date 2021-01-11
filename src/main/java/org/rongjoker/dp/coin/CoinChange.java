package org.rongjoker.dp.coin;

import org.junit.Test;

/**
 * date 01/11/2021
 *  322. 零钱兑换 https://leetcode-cn.com/problems/coin-change/
 *
 *  给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 * 本质是充分+完全背包
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CoinChange {

    @Test
    public void test322(){

        int[] coins = {1};
        int amount = 2;
        System.out.println(coinChange(coins,amount));


    }

    /**
     * 本质上是完全背包 + 最短路径
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        if(amount==0)return 0;

        int[] dp = new int[amount+1];
        int n = coins.length;

        for (int i = 1; i <= amount; i++) {
            dp[i] = -1;
        }

        for (int i = 0; i <n; i++) {
            int space = coins[i];
            for (int j = space; j <= amount; j++) {

                if(dp[j-space]!=-1){
                    if(dp[j]>-1){
                        dp[j] = Math.min(dp[j],dp[j-space]+1);//取最短的次数
                    }else dp[j] = dp[j-space]+1;
                }

            }
        }

        return dp[amount];


    }



}
