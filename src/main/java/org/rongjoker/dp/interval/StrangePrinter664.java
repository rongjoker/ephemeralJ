package org.rongjoker.dp.interval;

import org.junit.Test;

/**
 * @date 05/24/2021
 * 664. 奇怪的打印机 https://leetcode-cn.com/problems/strange-printer/
 * 区间dp
 * i-j的区间内部；从后向前，这样才能保证无后效性和重叠子问题;从前向后会漏掉
 * 区间dp的复杂度一般在n^3
 * 区间内部循环分块目的是保证aaaabbb这类，最长的aaaa能被计算到，而不是错误的被分割
 *
 *
 */
public class StrangePrinter664 {

    @Test
    public void test664(){
        System.out.println(strangePrinter("aaabbb"));
        System.out.println(strangePrinter("aba"));
    }


    public int strangePrinter(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i=0;i<len;++i)
            dp[i][i] = 1;
        for(int i=len-1;i>=0;--i){
            for(int j=i+1;j<len;++j){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i][j-1];
                }else{
                    int min = Integer.MAX_VALUE;
                    for(int k=i;k<j;++k){//这一步是最关键的一步，保证了aaaabb中最长的aaaa可以被一次打印
                         min= Math.min(dp[i][k]+dp[k+1][j],min);
                    }
                    dp[i][j] = min;

                }

            }
        }
        return dp[0][len-1];

    }



}
