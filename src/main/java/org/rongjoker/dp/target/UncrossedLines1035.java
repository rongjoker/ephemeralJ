package org.rongjoker.dp.target;

import org.junit.Test;

/**
 *
 * @date 05/20/2021
 * 1035. 不相交的线 https://leetcode-cn.com/problems/uncrossed-lines/
 *
 * dp板子题
 *
 */
public class UncrossedLines1035 {

    @Test
    public void test1035(){
        System.out.println(maxUncrossedLines(new int[]{1,4,2},new int[]{1,2,4}));
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 0;i<len1;++i){
            for(int j = 0;j<len2;++j){
                //这里写得太冗余
//                dp[i+1][j+1] = Math.max(dp[i][j] + (nums1[i]==nums2[j]?1:0),Math.max(dp[i+1][j],dp[i][j+1]));
                if(nums1[i]==nums2[j])dp[i+1][j+1] = dp[i][j] + 1;
                else dp[i+1][j+1] = Math.max(dp[i+1][j],dp[i][j+1]);
            }
        }
        return dp[len1][len2];

    }

}
