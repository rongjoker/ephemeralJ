package org.rongjoker.merge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @date 01/15/2021
 * 56. 合并区间  https://leetcode-cn.com/problems/merge-intervals/
 *  TODO: 2021/1/15  有各种特殊情况
 *
 */
public class MergeIntervals {


    @Test
    public void test56(){


        int[][] intervals =new int[4][2];

        intervals[0] = new int[]{1,4};
        intervals[1] = new int[]{2,3};
        intervals[2] = new int[]{8,10};
        intervals[3] = new int[]{9,18};


        merge(intervals);


    }





    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1)return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>();

        int left=0,right=1,n=intervals.length;int[] temp;
        while (right<n){
            if(intervals[right][0]>intervals[left][1]){
                temp = new int[2];temp[0]=intervals[left][0];temp[1]=intervals[right-1][1];
                list.add(temp);left=right;
            }
            right++;
        }

        temp = new int[2];temp[0]=intervals[left][0];temp[1]=intervals[right-1][1];
        list.add(temp);
        n=list.size();
        intervals =new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i] = list.get(i);
        }

        return intervals;

    }
}
