package org.rongjoker.dp.target;

/**
 * @date 04/05/2021
 *
 * 357. 计算各个位数不同的数字个数  https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 * dp类算数题目
 */
public class CountNumbersWithUniqueDigits357 {

    public int countNumbersWithUniqueDigits(int n) {

        if(n==0) return 1;
        if(n==1) return 10;
        if(n==2) return 91;
        int[] dp = new int[n+1];dp[0]=10;dp[1]=10;dp[2]=81;
        int sum=dp[1]+dp[2];
        for(int i=3;i<=n;++i){
            dp[i] = (11-i)*dp[i-1];
            sum+=dp[i];
        }

        return sum;

    }
}
