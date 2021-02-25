package org.rongjoker.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 02/25/2021
 * 34. 在排序数组中查找元素的第一个和最后一个位置  https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 利用二分实现O(log n)
 * 两次二分找到最小的和最大的
 */
public class SearchRange34 {


    @Test
    public void test34() {

        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange(nums, target)));

    }


    public int[] searchRange(int[] nums, int target) {
        int len = nums.length, offset01=Integer.MAX_VALUE,offset02=-1;
        if (len == 0) {
            return new int[]{-1, -1};
        }

        int start =0,end=nums.length-1;

        while (end>=start){
            int middle = start + (end - start) / 2;

            if(nums[middle]==target){
                end = middle-1;
                offset01 = Math.min(offset01,middle);
            }else if(nums[middle]<target){
                start = middle+1;
            }else
                end = middle-1;
        }

        if(offset01 == Integer.MAX_VALUE)offset01 =-1;

        start =0;end=nums.length-1;


        while (end>=start){
            int middle = start + (end - start) / 2;

            if(nums[middle]==target){
                start = middle+1;
                offset02 = Math.max(offset02,middle);
            }else if(nums[middle]<target){
                start = middle+1;
            }else
                end = middle-1;
        }


        return new int[]{offset01, offset02};




    }

}
