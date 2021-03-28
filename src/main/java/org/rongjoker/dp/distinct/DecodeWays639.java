package org.rongjoker.dp.distinct;

import org.junit.Test;

/**
 * @date 03/28/2021
 * 639. 解码方法 II https://leetcode-cn.com/problems/decode-ways-ii/
 * 较难的路径dp问题
 * long->int防止溢出
 *
 */
public class DecodeWays639 {

    @Test
    public void test639(){

        System.out.println(numDecodings("7*9*3*6*3*0*5*4*9*7*"));

    }



    public int numDecodings(String s) {

        int M = 1000000007;

        if(s.charAt(0)=='0')return 0;
        int len = s.length();
        long [] dp = new long[len+1];
        dp[0]=1;
        if(s.charAt(0)=='*')dp[1]=9;
        else dp[1]=1;
        for(int i=1;i<len;++i){
            if(s.charAt(i)=='0'){
                if(s.charAt(i-1)=='*'){
                    dp[i+1] = (dp[i-1]*2)%M;//只能和前一位组成10/20，无法单独作为数字
                }else if(s.charAt(i-1)=='0' || s.charAt(i-1)>'2') return 0;//不合法
                else
                    dp[i+1] = dp[i-1];//只能和前一位组成数字，无法单独作为数字
            }else{
                if(s.charAt(i)=='*'){
                    if(s.charAt(i-1)=='*'){
                        dp[i+1] = (dp[i-1]*15 + (dp[i]*9))%M;//1*和2*两个情况共计15(需要-1)
                    }else{
                        if(s.charAt(i-1)=='0' || s.charAt(i-1)>'2'){
                            dp[i+1] = (dp[i]*9)%M ;
                        }else if(s.charAt(i-1)=='1'){
                            dp[i+1] = ((dp[i]*9) + dp[i-1]*9)%M;
                        }else{
                            dp[i+1] = ((dp[i]*9) + dp[i-1]*6)%M;
                        }

                    }
                }else{
                    if(s.charAt(i-1)=='*'){
                        if(s.charAt(i)<'7')
                            dp[i+1] = (dp[i] + (dp[i-1]*2)%M)%M;
                        else
                            dp[i+1] = (dp[i] + dp[i-1])%M;

                    }else{
                        if(s.charAt(i-1)=='0' || Integer.parseInt(s.substring(i-1,i+1)) >26)
                            dp[i+1] = dp[i];
                        else dp[i+1] = (dp[i] + dp[i-1])%M;
                    }


                }


            }
        }
        return (int)dp[len];
    }
}
