package org.rongjoker.contest.week237;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 04/18/2021
 *
 * 5735. 雪糕的最大数量 https://leetcode-cn.com/contest/weekly-contest-237/problems/maximum-ice-cream-bars/
 */
public class Test2 {

    @Test
    public void test2(){

        System.out.println(maxIceCream(new int[]{1,3,2,4,1},7));
    }

    public int maxIceCream(int[] costs, int coins) {

        int len = costs.length;

        int[] dp = new int[coins+1];

        for(int i=0;i<len;++i){
            for(int j = coins;j>=costs[i];--j){
                dp[j] = Math.max(dp[j], dp[j -costs[i]] +1);
            }
        }

        return dp[coins];

    }


    //排名第一的优化版本
    public int maxIceCream2(int[] costs, int coins) {

        Arrays.sort(costs);
        int ans = 0;
        for(int c : costs){
            if(c <= coins){
                coins -= c;
                ans++;
            }
        }
        return ans;

    }

}
