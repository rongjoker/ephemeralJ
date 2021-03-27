package org.rongjoker.list;

import org.junit.Test;

/**
 * @date 03/27/2021
 * 189. 旋转数组 https://leetcode-cn.com/problems/rotate-array/
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 这个题目有进阶的就地解法(三次数组反转)
 *
 */
public class RotateArray189 {

    @Test
    public void test189(){

        int[] nums = {1,2,3,4,5,6,7};
        rotateInPlace(nums,2);


    }

    public void rotate(int[] nums, int k) {
        if(k==0)return;
        if(nums.length==1)return;
        int len=nums.length;
        k%=len;
        if(k==0)return;

        int[] copy = new int[len];

        int left=0,right=len-k;
        while (right<len){
            copy[left] = nums[right];
            ++left;
            ++right;
        }
        right=0;
        while (left<len){
            copy[left] = nums[right];
            ++left;
            ++right;
        }
        System.arraycopy(copy, 0, nums, 0, len);
    }


    //就地解法(三次数组反转)
    public void rotateInPlace(int[] nums, int k) {
        if(k==0 || nums.length==1)return;

        k%=nums.length;
        if(k==0)return;

        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);

    }


    public void reverse(int[] nums,int left,int right){
        int temp;
        while (right>left){
            temp = nums[left];
            nums[left]=nums[right];nums[right]=temp;
            --right;
            ++left;
        }
    }


}
