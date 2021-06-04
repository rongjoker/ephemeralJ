package org.rongjoker.dp.target;

import org.junit.Test;

public class TwoEggDrop1884 {

    @Test
    public void test1884(){
        System.out.println(twoEggDrop(100));
        System.out.println(twoEggDrop(2));
    }


    public int twoEggDrop(int n) {

        dp = new int[n+1][n+1];

        return drop(0,n);


    }

    int[][] dp;

    public int drop(int left,int right){

        if(left==right)return 0;

        if(dp[left][right]!=0)return dp[left][right];

        int middle = left + ((right - left) >>1);

        int ans = Math.max(middle-left,drop(middle+1,right))+1;
//        int ans = middle-left+drop(middle+1,right)+1;

        dp[left][right] = ans;

        return ans;

    }



}
