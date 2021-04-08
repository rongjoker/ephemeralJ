package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 04/04/2021
 * 153. 寻找旋转排序数组中的最小值 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * 二分查找一个被破坏过的有序数组的最小值
 * 二分查找，注意左边界和右边界的边界条件
 * 不去判断跟最左边的比较，而是始终和最右边比较，正确的肯定是夹在中间的左边
 * 4越8日每日一题
 *
 */
public class FindMin153 {


    @Test
    public void test153(){

        int[] numbers = {3,1,2};
        System.out.println(findMin(numbers));

    }



    public int findMin(int[] nums) {
        int len=nums.length;
        if(len==1) return nums[0];
        int left=0,right=nums.length-1,middle;
        while (right>left){
            middle = left + ((right - left)>>1);
            if(nums[middle]<nums[right]){//在最右边的左边
                right = middle;
            }else left = middle+1;//在左边的右边
        }

        return nums[left];


    }


}
