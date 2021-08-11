package org.rongjoker.bytedance;


import org.junit.Test;

/**
 * @date 08/11/2021
 *
 * 圆环回原点问题
 *
 *
 *
 */
public class BackToOrigin {


    @Test
    public void test1(){
        System.out.println(backToOrigin(2));
        System.out.println(backToOrigin(20));
    }



    public int backToOrigin(int n){

        int[][] dp = new int[n+1][10];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for(int j = 0;j<10;++j){
                dp[i][j] = dp[i-1][(j+1)%10] + dp[i-1][(j+9)%10];
            }

        }

        return dp[n][0];


    }



}
