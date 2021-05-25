package org.rongjoker.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @date 01/15/2021
 * 56. 合并区间  https://leetcode-cn.com/problems/merge-intervals/
* @date 01/27/2021
 * 先根据前缀排序，然后入队第一个，判断第二个的前缀是否大于第一个的后缀，如果大于，则直接入队第二个；如果小于，则判断两个的后缀，取大的来更新入队的数据后缀，不增加数据
 *
 */
public class MergeIntervals {


    @Test
    public void test56(){


        int[][] intervals =new int[2][2];

        intervals[0] = new int[]{1,4};
        intervals[1] = new int[]{2,3};
//        intervals[2] = new int[]{8,10};
//        intervals[3] = new int[]{9,18};


        merge(intervals);


    }





    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1)return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>();

        int n=intervals.length,length;

        for (int i = 0; i < n; i++) {
            length = list.size();

            if(length==0 || intervals[i][0]>list.get(length-1)[1])
                list.add(intervals[i]);
            else if(intervals[i][1]>list.get(length-1)[1]){
                list.get(length-1)[1]=intervals[i][1];
            }
        }

        return list.toArray(new int[list.size()][]);

//        int[][] array = list.toArray(new int[list.size()][]);
//        return array;

    }
}
