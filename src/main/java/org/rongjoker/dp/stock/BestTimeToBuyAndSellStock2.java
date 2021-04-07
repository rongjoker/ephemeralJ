package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 * 股票买卖相关第二个题目
 *
 * @date 01/08/2021
 * 122. 买卖股票的最佳时机 II  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 *
 */
public class BestTimeToBuyAndSellStock2 {


    /**
     * 122. 买卖股票的最佳时机 II  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     */
    @Test
    public void test122(){

        int[] array = {7,6,4,3,1};
        System.out.println(maxProfitDp(array));
//        System.out.println(maxProfitGreedy(array));


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

        int max = 0,temp;

        for (int i = 1; i < prices.length; i++) {
            temp = prices[i] - prices[i-1];
            if(temp>0)
                max+=temp;
        }
        return max;

    }


}
