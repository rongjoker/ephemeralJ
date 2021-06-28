package org.rongjoker.dp.stock;


/**
 *
 * @date 06/28/2021
 *
 * 1911. 最大子序列交替和
 *
 * 本质是股票问题 https://leetcode-cn.com/problems/maximum-alternating-subsequence-sum/
 *
 *
 *
 */
public class MaxAlternatingSum1911 {



    public long maxAlternatingSum(int[] nums) {
        int len = nums.length;
        long[][] dp = new long[len][2];
        dp[0][0] = nums[0];
        for(int i=1;i<len;++i){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + nums[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] - nums[i]);
        }
        return Math.max(dp[len-1][0],dp[len-1][1]);

    }
}
