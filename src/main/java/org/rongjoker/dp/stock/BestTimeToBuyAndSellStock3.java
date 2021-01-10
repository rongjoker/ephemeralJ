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
     * <p>
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void test123() {

        int[] array = {1};//[3,2,6,5,0,3]
//        int[] array = {3,3,5,0,0,3,1,4};//[3,2,6,5,0,3]

        System.out.println(maxProfitDp(array));


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

        int n = prices.length-1;

        if (n < 1)
            return 0;
        int[] difference = new int[n];

        boolean minus = true;
        for (int i = 1; i <=n; i++) {
            difference[i - 1] = prices[i] - prices[i - 1];
            if (difference[i - 1] > 0)
                minus = false;
        }

        if (minus)
            return 0;

        if (n == 1)
            return difference[0];

        int[][] dp = new int[n][4];

        int temp = 0;
        for (int i = 0; i < n ; i++) {

            if (temp > 0)
                temp = temp + difference[i];
            else temp = difference[i];

            dp[i][0] = temp;
            dp[i][1] = Math.max(temp,i>0?dp[i-1][1]:0);
        }

        temp = 0;
        for (int i = n-1; i >1 ; i--) {//可以第二笔交易的范围

            if (temp > 0)
                temp = temp + difference[i];
            else temp = difference[i];

            dp[i-2][2] = temp;
            dp[i-2][3] = Math.max(temp,i>n-2?dp[i-1][3]:0);

        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i][1] + dp[i][3]);
        }
        return max;

    }


}
