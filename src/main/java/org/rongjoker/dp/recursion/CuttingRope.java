package org.rongjoker.dp.recursion;

import org.junit.Test;

/**
 * @date 04/02/2021
 * 剑指 Offer 14- I. 剪绳子 https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
 *
 */
public class CuttingRope {

    @Test
    public void testCuttingRope(){

        System.out.println(cuttingRope(8));

    }

    int[] dp;


    public int cuttingRope(int n) {
        if(n==2)return 1;

        dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;


        int max = 0;
        for (int i = 1; i <= n>>1; i++) {
            max = Math.max(cutting(i)*cutting(n-i),max);

        }

        return max;

    }

    public int cutting(int index) {

        int max = index;

        if(index==1)return 1;

        if(dp[index]!=0)return dp[index];

        for (int i = 2; i < index; i++) {
                max = Math.max(cutting(i)*cutting(index-i),max);
        }

        dp[index] = max;

        return max;

    }
}
