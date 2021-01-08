package org.rongjoker.dp.stock;

import org.junit.Test;

/**
 * 股票买卖相关第三个题目
 *
 * @date 01/08/2021
 * 123. 买卖股票的最佳时机 III  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStock3 {


    /**
     * 123. 买卖股票的最佳时机 III   https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
     */
    @Test
    public void test123() {

        int[] array = {3, 3, 5, 0, 0, 3, 1, 4};
        array = new int[]{1,2,3,4,5};

        System.out.println(maxProfitDp(array));

//        array = new int[]{0, 2, -5, 0, 3, -2, 3};
//        System.out.println("-------kkkk-------");
//        System.out.println(max(array,0,4));
//        System.out.println(max(array, 4, array.length));


    }


    /**
     * 动态规划
     * 利用差分+线段和,超时
     * 需要考虑全部为负数的情况
     *
     * @param prices
     * @return
     */
    public int maxProfitDp(int[] prices) {
        if (prices.length < 2)
            return 0;
        int[] difference = new int[prices.length - 1];

        boolean minus = true;
        for (int i = 1; i < prices.length; i++) {
            difference[i - 1] = prices[i] - prices[i - 1];
            if (difference[i - 1] > 0)
                minus = false;
        }

        if (minus)
            return 0;

        if(difference.length==1)
            return difference[0];

        int max = 0;

        for (int i = 0; i <= difference.length - 1; i++) {

            max = Math.max(
                    max(difference, i , difference.length) + max(difference, 0, i)
                    , max);
        }

        return max;

    }


    public int max(int[] difference, int low, int high) {

        if (high-low == 0)
            return 0;
        if (high-low == 1)
            return difference[low];


        int max = difference[low];
        int temp = 0;
        for (int i = low; i < high; i++) {

            if (temp > 0)
                temp = temp + difference[i];
            else temp = difference[i];
            max = Math.max(temp, max);
        }

        return max;

    }


}
