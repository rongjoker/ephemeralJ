package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 *
 *
 * @date 04/05/2021
 * 309. 最佳买卖股票时机含冷冻期  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * dp
 * 较难理解，每一天有2种情况：持入股票和不持入股票
 * 第一天的持有股票只有一种可能，也就是买入；第二天有两种可能，要么昨天买的，今天不操作继续持有（延续）；要么是昨天不买，今天买
 * 不持入股票的情况，第一天就是0（无法卖出）；第二天要么延续第一天的卖出情况，要么在第一天买入的情况下卖出
 * 后面的以此类推,只是下次买入，要在上上次卖出（不持有）的基础上操作
 */
public class BestTimeToBuyAndSellStock309 {

    /**
     *
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    @Test
    public void test309() {

//        int[] array = {1,1,1,1,2,1};//[3,2,6,5,0,3]
        int[] array = {3,3,5,0,0,3,1,4};//[3,2,6,5,0,3]

        System.out.println(maxProfit(array));
        System.out.println(maxProfit2(array));


    }


    public int maxProfit(int[] prices) {

        int len = prices.length;

        if(len<2)return 0;
        if(len==2)return Math.max(0,prices[1]-prices[0]);

        int[][] dp = new int[len][2];//0为买入，1为卖出
        dp[0][0] = - prices[0];//持有股票的情况，第一天只有买入这一种情况，无法从之前买入的持有下去
        dp[1][0] = Math.max(dp[0][0],-prices[1]);//持有股票的情况
        dp[1][1] = Math.max(prices[1]-prices[0],0);
        for (int i = 2; i < len; i++) {
            //卖出,两种情况比较：1，不操作，还是从上一步卖出的收益延续下来；2，卖出，从上一步的买入延续下来 ++
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] + prices[i]);
            //买入，两种种情况:1,不操作，从上一步的买入的收益延续下来;2，买入，从上上步的卖出种延续 --
            dp[i][0] = Math.max(dp[i-1][0],dp[i-2][1]-prices[i]);
        }

        return Math.max(dp[len-1][1],0);

    }

    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];
        for(int i=1;i<len;i++){
            dp[i][0] =Math.max( dp[i-1][0], dp[i-1][2]-prices[i]);
            dp[i][1] =Math.max( dp[i-1][1], dp[i-1][0]+prices[i]);
            dp[i][2] =Math.max( dp[i-1][2], dp[i-1][1]);
        }

        return Math.max(dp[len-1][1],dp[len-1][2]);

    }





}
