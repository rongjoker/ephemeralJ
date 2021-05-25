package org.rongjoker.dp.longest;

import org.junit.Test;

/**
 * @date 03/27/2021
 * 516. 最长回文子序列 https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * 区间dp的经典板子题目
 * i-j的区间内部；从后向前，这样才能保证无后效性和重叠子问题;从前向后会漏掉
 * 区间dp的复杂度一般在n^3
 *
 *
 */
public class LongestPalindromeSubseq516 {


    @Test
    public void test516(){

        System.out.println(longestPalindromeSubseq("skskkskskkskskkmmmmmmimmmmmm"));

    }




    //递归+区间dp的解法，速度非常慢，有很多重复的计算
    public int longestPalindromeSubseq(String s) {
        int len = s.length();int max=0;
        if(len==1)return 1;
        if(len==2){
            if(s.charAt(0)==s.charAt(1))return 2;
            else return 1;
        }
        int[][] dp = new int[len][len];

        for(int i=0;i<len;++i){
            max = Math.max(max,getMax(s,0,i,dp));
        }
        return max;
    }

    public int getMax(String s,int left,int right,int[][] dp){
        if(right <left) return 0;

        if(dp[left][right]>0) return dp[left][right];

        if(left==right){
            dp[left][right] = 1;
            return 1;
        }

        //三种区间可能性
        if(s.charAt(left) == s.charAt(right)){
            dp[left][right] = getMax(s,left+1,right-1,dp)+2;
        }else
            dp[left][right] = Math.max(getMax(s,left+1,right,dp),
                getMax(s,left,right-1,dp));
        return dp[left][right];
    }


    //迭代+区间dp的解法，节省很多重复计算
    public int longestPalindromeSubseqOptimize(String s) {
        int len = s.length();int max=0;
        if(len==1)return 1;
        if(len==2){
            if(s.charAt(0)==s.charAt(1))return 2;
            else return 1;
        }
        int[][] dp = new int[len][len];
        for(int i=0;i<len;++i){
            dp[i][i]=1;
        }

        //i-j的区间内部；从后向前，这样才能保证无后效性和重叠子问题;从前向后会漏掉
        //从后向前，区域一直很小，慢慢扩大
        //从前向后，起初j就可以从0直接计算到len-1，大量未计算的过程存在
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][len-1];//从0到len-1整个范围
    }

}
