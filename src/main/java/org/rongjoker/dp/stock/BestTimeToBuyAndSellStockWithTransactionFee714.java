package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 *
 * @date 04/07/2021
 *
 * 714. 买卖股票的最佳时机含手续费 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 类似309. 最佳买卖股票时机含冷冻期
 *
 */
public class BestTimeToBuyAndSellStockWithTransactionFee714 {

    @Test
    public void test714(){
        int[] array = {1, 3, 2, 8, 4, 9};

        System.out.println(maxProfit( array,2));
    }

    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if (len < 2)
            return 0;

        int[][] dp = new int[len][2];//0为买入，1为卖出
        dp[0][0] = - prices[0];//持有股票的情况，第一天只有买入这一种情况，无法从之前买入的持有下去
        dp[1][0] = Math.max(dp[0][0],-prices[1]);//持有股票的情况
        dp[1][1] = Math.max(prices[1]-prices[0]- fee,0);
        for (int i = 2; i < len; i++) {
            //卖出,两种情况比较：1，不操作，还是从上一步卖出的收益延续下来；2，卖出，从上一步的买入延续下来 ++
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] + prices[i]-fee);
            //买入，两种种情况:1,不操作，从上一步的买入的收益延续下来;2，买入，从上步的卖出的收益延续 --
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]-prices[i]);
        }

        return Math.max(dp[len-1][1],0);

    }
}
