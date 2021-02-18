package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 02/18/2021
 * 704. 二分查找 https://leetcode-cn.com/problems/binary-search/
 * 典型的分治法，适用于有序数列
 * middle = start + (end — start) / 2
 * 递归是最容易想出来的，实际上也可以用while循环
 *
 */
public class BinarySearch704 {

    @Test
    public void test704() {
        int[] nums = {1,2,3,4,5};
        System.out.println(searchOptimize(nums, 2));


    }

    /**
     * 迭代版本，空间复杂度降低
     * @param nums
     * @param target
     * @return
     */
    public int searchOptimize(int[] nums, int target) {
        int start =0,end=nums.length-1;

        while (end>=start){
            int middle = start + (end - start) / 2;

            if(nums[middle]==target)
                return middle;
            else if(nums[middle]<target){
                start = middle+1;
            }else
                end = middle-1;
        }

        return -1;

    }


    /**
     * 递归版本
     * @param nums
     * @param target
     * @return
     */
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
