package org.rongjoker.dp.coin;

import org.junit.Test;

/**
 * date 02/19/2021
 * 518. 零钱兑换 II https://leetcode-cn.com/problems/coin-change-2/
 * <p>
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * 本质是充分+完全背包,但是进行反向操作,先循环硬币再循环金额，避免因为硬币顺序出现重复的种数
 * <p>
 * 与322 不同src/main/java/org/rongjoker/dp/coin/CoinChange.java （322为求最少的硬币数量）
 * 这个问题的棘手的地方在于方案可能会重复 1 1 2和2 1 1
 */
public class CoinChange518 {

    @Test
    public void test518() {

        int[] coins = {1, 2,5};
        int amount = 0;
        System.out.println(coinChange(amount, coins));


    }

    /**
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int amount, int[] coins) {

        if (amount == 0) return 1;

        int n = coins.length;

        int[] dp = new int[amount + 1];


        dp[0] = 1;//关键点1
        //关键点2,反向操作,先循环硬币再循环金额，避免因为硬币顺序出现重复的种数
        for (int j = 0; j < n; j++) {
            for (int i = coins[j]; i <= amount; i++) {
                if (i - coins[j] >= 0)
                    dp[i] += dp[i - coins[j]];
            }
        }

        return dp[amount];


    }


}
