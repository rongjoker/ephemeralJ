package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 * 股票买卖相关第一个题目
 *
 * @date 01/08/2021
 * 121. 买卖股票的最佳时机  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * dp:利用差分+线段和
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


    }

    /**
     * 动态规划
     * 利用差分+线段和
     * 需要考虑全部为负数的情况
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len<2)return 0;

        int max = 0,temp=0;
        for(int i=1;i<len;++i){
            if(temp<0)temp = (prices[i] - prices[i-1]);
            else temp+=(prices[i] - prices[i-1]);
            max = Math.max(temp,max);
        }
        return max;

    }


}
