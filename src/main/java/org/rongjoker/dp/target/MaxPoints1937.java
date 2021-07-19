package org.rongjoker.dp.target;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 07/19/2021
 * 扣分后的最大得分 https://leetcode-cn.com/problems/maximum-number-of-points-with-cost/
 *
 *
 */
public class MaxPoints1937 {


    @Test
    public void test1937(){
        
        //[[0,3,0,4,2},{5,4,2,4,1},{5,0,0,5,1},{2,0,1,0,3]]

        System.out.println(maxPoints(new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
        System.out.println(maxPointsOptimize(new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
        System.out.println(maxPoints(new int[][]{{0,3,0,4,2},{5,4,2,4,1},{5,0,0,5,1},{2,0,1,0,3}}));
        System.out.println(maxPointsOptimize(new int[][]{{0,3,0,4,2},{5,4,2,4,1},{5,0,0,5,1},{2,0,1,0,3}}));
        System.out.println(maxPoints(new int[][]{{1,5},{2,3},{4,2}}));
        System.out.println(maxPointsOptimize(new int[][]{{1,5},{2,3},{4,2}}));


    }


    public long maxPointsOptimize(int[][] points) {

        int n = points[0].length;

        long[] dp = new long[n];

        for (int[] point : points) {

            long[] cur = new long[n];

            long max = Integer.MIN_VALUE;

            //从左边循环一圈，找最大值
            for (int j = 0; j < n; ++j) {
                max = Math.max(dp[j], max - 1);
                cur[j] = max + point[j];
            }

            max = Integer.MIN_VALUE;

            //从右边循环一圈，找最大值，并且和左边找到的做比较
            for (int j = n-1; j >=0; --j) {
                max = Math.max(dp[j], max - 1);
                cur[j] = Math.max(cur[j],max + point[j]);
            }

            dp = cur;
        }

        return Arrays.stream(dp).max().getAsLong();

    }


    /**
     * 原生dp方案，会超时
     * @param points
     * @return
     */
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
