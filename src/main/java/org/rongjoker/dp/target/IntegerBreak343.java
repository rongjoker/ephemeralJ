package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * date 02/19/2021
 * 343. 整数拆分 https://leetcode-cn.com/problems/integer-break/
 * 利用dp可以求解，复杂度为n^2
 * 递推方程为，比如18=9*9，最大值为要么9不拆分的最大值，要么为9再次拆分的最大值，不拆分最大值为81，拆分则去找记录了9的拆分最大值相乘做比较
 * 关键点：迭代的基准点是拆分出的第一个正整数，意味着j不可再拆分
 *
 */
public class IntegerBreak343 {


    @Test
    public void test343(){

        System.out.println(integerBreak(10));


    }


    public int integerBreak(int n) {

        int[] dp = new int[n+1];

        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {//关键点j为拆分出的第一个正整数，意味着j不可再拆分
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),dp[i-j]*j));//继续分割和不继续分割的情况下，哪种更大
            }
        }

        return dp[n];

    }


}
