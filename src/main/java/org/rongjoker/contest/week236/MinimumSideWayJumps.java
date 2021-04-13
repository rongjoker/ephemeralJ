package org.rongjoker.contest.week236;

import org.junit.Test;


/**
 *
 *@date 04/13/2021
 * 1824. 最少侧跳次数
 *
 * 只能从同侧来跳跃
 *
 * 降维度能大幅度降低复杂度
 */
public class MinimumSideWayJumps {


    @Test
    public void testMinimumSideWayJumps(){

        System.out.println(minSideJumps(new int[]{0,1,2,3,0}));

    }


    public int minSideJumps(int[] obstacles) {
        int len = obstacles.length;
        if(len==2)return 0;
        long[] dp = new long[4];

        dp[1]=dp[3]=1;

        for(int i=1;i<len;++i){

            if(obstacles[i]==1)
                dp[1] = Integer.MAX_VALUE;
            if(obstacles[i]==2)
                 dp[2] = Integer.MAX_VALUE;
            if(obstacles[i]==3)
                dp[3] = Integer.MAX_VALUE;


            if(obstacles[i]!=1)
                dp[1] = Math.min(Math.min(dp[2],dp[3])+1,dp[1]);
            if(obstacles[i]!=2)
                dp[2] = Math.min(Math.min(dp[1],dp[3])+1,dp[2]);
            if(obstacles[i]!=3)
                dp[3] = Math.min(Math.min(dp[1],dp[2])+1,dp[3]);

        }

        return (int) Math.min(Math.min(dp[1],dp[2]),dp[3]);//任意一条赛道都可以

    }


}
