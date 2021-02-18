package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 02/18/2021
 * 704. 二分查找 https://leetcode-cn.com/problems/binary-search/
 * 典型的分治法，适用于有序数列
 * middle = start + (end — start) / 2
 *
 */
public class BinarySearch704 {

    @Test
    public void test704() {
        int[] nums = {1,2,3,4,5};
        System.out.println(search(nums, 2));


    }

    public int search(int[] nums, int target) {
        return binSearch(nums,target,0,nums.length-1);
    }


    public int binSearch(int[] nums, int target,int start,int end) {

        if(start>end)return -1;

        if(start==end)
            return nums[start]==target?start:-1;

        int middle = start + (end - start) / 2;

        if(nums[middle]==target)
            return middle;
        else if(nums[middle]<target){
            return binSearch(nums,target,middle+1,end);
        }else
            return binSearch(nums,target,start,middle-1);

    }








}
