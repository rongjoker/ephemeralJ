package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 * 股票买卖相关第一个题目
 *
 * @date 01/08/2021
 * 121. 买卖股票的最佳时机  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 *
 */
public class BestTimeToBuyAndSellStock {


    /**
     * 121. 买卖股票的最佳时机  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     */
    @Test
    public void test121(){

        int[] array = {7,1,5,3,6,4};
        System.out.println(maxProfit(array));
        System.out.println(maxProfitDp(array));
        System.out.println(maxProfitGreedy(array));


    }

    /**
     * 蛮力算法
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            for (int j = i-1; j >=0; j--) {
                max = Math.max(prices[i]-prices[j],max);
            }
        }
        return max;

    }

    /**
     * 动态规划
     * 利用差分+线段和
     * 需要考虑全部为负数的情况
     * @param prices
     * @return
     */
    public int maxProfitDp(int[] prices) {
        if (prices.length<2)
            return 0;
        int[] difference = new int[prices.length-1];

        boolean minus = true;
        for (int i = 1; i < prices.length; i++) {
            difference[i-1] = prices[i] - prices[i-1];
            if(difference[i-1]>0)
                minus = false;
        }

        if(minus)
            return 0;

        int max = difference[0];
        int temp = difference[0];
        for (int i = 1; i < difference.length; i++) {

            if(temp>0)
                temp = temp+difference[i];
            else temp = difference[i];
            max = Math.max(temp,max);
        }


        return max;

    }


    /**
     *
     *
     * @param prices
     * @return
     */
    public int maxProfitGreedy(int[] prices) {
        if (prices.length<2)
            return 0;

        int min = prices[0];
        int max = prices[1]-prices[0];

        for (int i = 1; i < prices.length; i++) {

            min = Math.min(prices[i],min);
            max = Math.max(prices[i] - min,max);
        }

        return Math.max(max, 0);

    }




}
