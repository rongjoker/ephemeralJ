package org.rongjoker.contest.week248;

import org.junit.Test;

public class Test3 {

    @Test
    public void test3() {

//        System.out.println(countGoodNumbers(14));
//        System.out.println(countGoodNumbers(4));
        System.out.println(countGoodNumbers(50));


    }


    public int countGoodNumbers(long n) {

        int M = 1000000007;

        long[][] dp = new long[2][2];
        dp[0][0] = 5;//偶数
        dp[0][1] = 1;//奇数
        for(int i=1;i<n;++i){
            dp[i%2][0] = Math.max(dp[(i+1)%2][0],dp[(i+1)%2][1]*5);
            dp[i%2][1] = Math.max(dp[(i+1)%2][1],dp[(i+1)%2][0]*4);
            dp[i%2][0]%=M;
            dp[i%2][1]%=M;
        }
        return (int)Math.max(dp[(int)((n+1)%2)][0],dp[(int)((n+1)%2)][1]);

    }
}
