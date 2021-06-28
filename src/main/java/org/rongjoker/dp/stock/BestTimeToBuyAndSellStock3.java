package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 * 股票买卖相关第三个题目
 *
 * @date 01/08/2021
 * 123. 买卖股票的最佳时机 III  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * @date 01/10/2021 解决
 * 较难
 * @date 04/07/2021
 * 有2种方法
 * 方法一：从中间分割为2笔交易，左边的找到最小值，当前值减去最小值即当前点的最大收益；右边从右向左运行，找到最大值，减去当前值即为右边的最大值；然后左边两边遍历结果，求每个的和，比较每个和的最大值
 * 方法二，较难想出，但更有通用性(能适用于分割成2、3.。。。k笔交易)，也是更高阶的状态dp
 *
 *
 */
public class BestTimeToBuyAndSellStock3 {


    @Test
    public void test123() {

        int[] array = {1,2,3,4,5};//[3,2,6,5,0,3]
//        int[] array = {3,3,5,0,0,3,1,4};//[3,2,6,5,0,3]

        System.out.println(maxProfit(array));
        System.out.println(maxProfitStatus(array));


    }

    /**
     * 方法一，切分成2个分块进行dp
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices){
        int len = prices.length;
        if (len<2)
            return 0;


        int[] dp_left = new int[len],dp_right = new int[len];

        int min=prices[0];
        for (int i = 1; i <len; i++) {
            dp_left[i] = Math.max(dp_left[i-1],prices[i] - min );
            min = Math.min(min,prices[i]);
        }

        int  max = prices[len-1];
        for (int i = len-2; i >0; i--) {
            dp_right[i] = Math.max(dp_right[i+1],max - prices[i]);
            max = Math.max(max,prices[i]);
        }

        max = 0;
        for (int i = 1; i < len; i++) {
            max = Math.max(max,dp_left[i] + dp_right[i]);
        }

        return max;

    }



    public int maxProfitStatus(int[] prices){
        int len = prices.length;
        if (len<2)
            return 0;

        int[][] dp = new int[len][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];//收益从前面顺延过来，并不是购买了第二笔股票


        //记录前缀状态
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(-prices[i],dp[i-1][0]);//持有第一笔股票的最好值
            dp[i][1] = Math.max(dp[i-1][1],prices[i] + dp[i-1][0]);//卖出第一笔股票的最好值
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]-prices[i]);//持有第二笔股票的最好值
            dp[i][3] = Math.max(dp[i-1][3],prices[i] + dp[i-1][2]);//卖出第二笔股票的最好值


            }


        return dp[len-1][3];


    }




}
