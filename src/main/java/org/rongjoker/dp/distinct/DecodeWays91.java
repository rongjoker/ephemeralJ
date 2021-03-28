package org.rongjoker.dp.distinct;

import org.junit.Test;

/**
 * @date 03/28/2021
 * 91. 解码方法 https://leetcode-cn.com/problems/decode-ways/
 * 典型的路径dp问题
 *
 */
public class DecodeWays91 {

    @Test
    public void test91(){

        System.out.println(numDecodings("2206"));

    }



    public int numDecodings(String s) {
        if(s.charAt(0)=='0')return 0;
        int len = s.length();
        if(len==1)return 1;
        int [] dp = new int[len+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=1;i<len;++i){
            if(s.charAt(i)=='0'){
                if(s.charAt(i-1)=='0' || s.charAt(i-1)>'2') return 0;//不合法
                dp[i+1] = dp[i-1];//只能和前一位组成数字，无法单独作为数字
            }else{
                if(s.charAt(i-1)=='0' || Integer.parseInt(s.substring(i-1,i+1)) >26)
                    dp[i+1] = dp[i];
                else dp[i+1] = dp[i] + dp[i-1];
            }
        }
        return dp[len];

    }
}
