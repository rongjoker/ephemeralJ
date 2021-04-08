package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 * 股票买卖相关第四个题目
 *
 * @date 04/07/2021
 * 123. 买卖股票的最佳时机  IV  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 方法二，较难想出，但更有通用性(能适用于分割成2、3.。。。k笔交易)，也是更高阶的状态dp
 */
public class BestTimeToBuyAndSellStock188 {


    @Test
    public void test123() {

        int[] array = {3, 2, 6, 5, 0, 3};

        System.out.println(maxProfit(0, array));


    }


    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;

        if(k==0) return 0;

        k = Math.min(k, len>>1);//最多不会超过一半的次数

        int[][][] dp = new int[len][k][2];
        for (int i = 0; i < k; i++) {
            dp[0][i][0] = -prices[0];
        }

        //记录前缀状态
        for (int i = 1; i < len; i++) {

            for (int j = 0; j < k; j++) {
                if (j > 0)
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] - prices[i]);//持有第j笔股票的最好值=j-1笔收益-购买当前股票
                else dp[i][j][0] = Math.max(dp[i - 1][j][0], -prices[i]);//持有第j笔股票的最好值=j-1笔收益-购买当前股票

                dp[i][j][1] = Math.max(dp[i - 1][j][1], prices[i] + dp[i - 1][j][0]);//卖出第j笔股票的最好值
            }

        }

        return dp[len - 1][k - 1][1];
    }


}
