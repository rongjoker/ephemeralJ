package org.rongjoker.dp.target;

/**
 * @date 03/26/2021
 * 718. 最长重复子数组 https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 * 本质是条件背包，代码可以写得很简洁
 *
 */
public class MaximumLengthOfRepeatedSubarray718 {


    public int findLength(int[] A, int[] B) {

        if(A.length==0 || B.length==0)return 0;

        int m = A.length,n=B.length;

        int[][] dp = new int[m+1][n+1];
        int max = 0;

        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(A[i] == B[j]){
                    dp[i+1][j+1] = dp[i][j]+1;//这一步不需要比较
                    max = Math.max(max,dp[i+1][j+1]);
                }//反条件就是赋值为0，所以不需要

            }
        }

        return max;


    }


}
