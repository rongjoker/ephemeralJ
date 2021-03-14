package org.rongjoker.sw;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 03/14/2021
 * 1213. 三个有序数组的交集  https://leetcode-cn.com/problems/intersection-of-three-sorted-arrays/
 * 双指针
 * 给出三个均为 严格递增排列 的整数数组 arr1，arr2 和 arr3。
 */
public class ArraysIntersection1213 {


    @Test
    public void test1213(){
        int[] arr1 = {1,2,3,4,5},arr2={1,2,5,7,9},arr3={1,3,4,5,8};
        System.out.println(arraysIntersection(arr1,arr2,arr3));

    }



    //有序数组，不需要排序,双指针要注意边界情况
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {

        int len1 = arr1.length,max2 = arr2.length-1,max3 = arr3.length-1,p1,p2=0,p3=0,temp;

        List<Integer> list = new ArrayList<>();

        for (p1 = 0; p1 < len1; p1++) {

            temp = arr1[p1];

            while (arr2[p2]<arr1[p1] && p2<max2)++p2;
            while (arr3[p3]<arr1[p1]&& p3<max3)++p3;

            if(arr2[p2]==arr1[p1] && arr1[p1]==arr3[p3]){
                list.add(temp);
                if(p2<max2)++p2;
                if(p3<max3)++p3;
            }

        }

        return list;

    }


}
