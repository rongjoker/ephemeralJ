package org.rongjoker.contest.biweekly50;

import java.util.Arrays;
import java.util.Comparator;

public class Test2 {

    public void test2(){


    }

    public int[] countPoints(int[][] points, int[][] queries) {
        int len = queries.length,lenp=points.length;
        int[] sum = new int[len];
        int[][][] xy = new int[len][2][3];
        int[] x = new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE},y =new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE};
        for (int i = 0; i < queries.length; i++) {

            xy[i][0] = new int[]{queries[i][0]-queries[i][2],queries[i][0]+queries[i][2],i};
            x[0] = Math.min(x[0],xy[i][0][0]);
            x[1] = Math.max(x[1],xy[i][0][1]);
            xy[i][1] = new int[]{queries[i][1]-queries[i][2],queries[i][1]+queries[i][2],i};
            y[0] = Math.min(y[0],xy[i][1][0]);
            y[1] = Math.max(y[1],xy[i][1][1]);
        }

        Arrays.sort(xy, Comparator.comparingInt(a -> a[0][0]));

        int index;
        for (int i = 0; i < lenp; i++) {
            if(points[i][0] < x[0] || points[i][0] > x[1] || points[i][1] < y[0] || points[i][1] > y[1] ) continue;
            for (int j = 0; j <len; j++) {
                index = xy[j][0][2];
                if(points[i][0] < xy[j][0][0]) break;
                if( points[i][0] > xy[j][0][1] || points[i][1] < xy[j][1][0] || points[i][1] > xy[j][1][1])continue;
                if(Math.pow(points[i][0] - queries[index][0],2) + Math.pow(points[i][1] - queries[index][1],2) <= Math.pow(queries[index][2],2)){
                    sum[index]++;
                }

            }

        }

        return sum;



    }
}
