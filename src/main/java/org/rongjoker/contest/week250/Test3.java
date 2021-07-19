package org.rongjoker.contest.week250;

import org.junit.Test;

public class Test3 {

    @Test
    public void test3() {

        System.out.println(maxPoints(new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
        System.out.println(maxPoints(new int[][]{{1,5},{2,3},{4,2}}));





    }


    public long maxPoints(int[][] points) {

        int m = points.length,n = points[0].length;

        long[] dp = new long[n];

        long ans = 0;

        for(int i=0;i<m;++i){

            long[] cur = new long[n];

            for(int j=0;j<n;++j){

                for(int k=0;k<n;++k){
                    cur[j] = Math.max(cur[j],points[i][j] + dp[k] - Math.abs(j-k)) ;
                }

                if(i==m-1){
                    ans = Math.max(ans,cur[j]);
                }

            }

            dp = cur;
        }

        return ans;



    }



}
