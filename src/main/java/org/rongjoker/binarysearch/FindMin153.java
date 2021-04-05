package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 04/04/2021
 * 153. 寻找旋转排序数组中的最小值 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * 二分查找一个被破坏过的有序数组的最小值
 * 二分查找，注意左边界和右边界的边界条件
 *
 */
public class FindMin153 {


    @Test
    public void test153(){

        int[] numbers = {1,3,5};
        System.out.println(findMin(numbers));

    }



    public int findMin(int[] nums) {
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.min(nums[0],nums[1]);
        int left=0,right=nums.length-1,middle,len=nums.length;
        while (right>=left){
            middle = left + ((right - left)>>1);

            if(nums[middle]>nums[0]){
                if(middle==len-1) return nums[0];
                if(nums[middle+1]<nums[0])return nums[middle+1];
                else left = middle+1;
            }else if(nums[middle]<nums[0]){
                if(nums[middle-1]>nums[0])return nums[middle];
                else right = middle-1;
            }else return nums[1];
        }

        return -1;


    }


}
