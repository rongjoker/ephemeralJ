package org.rongjoker.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 04/14/2021
 * 31. 下一个排列 https://leetcode-cn.com/problems/next-permutation/
 * 遇到非降序排列的，停止，然后从右边找到最小的大于左边的数字去替换，然后再把右边改为升序
 *
 *
 */
public class NextPermutation31 {


    @Test
    public void test31(){

        int[] nums = new int[]{1,3,2};//1,3,2,1

        nextPermutation(nums);

        System.out.println(Arrays.toString(nums));


    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if(len==1) return;

        int left = len-2,right = len-1,temp;
        boolean change = false;

        while(left>=0){

            if(nums[left]<nums[right]){
                change = true;
                break;
            }

            --left;--right;

        }

        if(!change){
            swap(nums,0,len-1);
        }else {
            while (right<=len-1){
                if(nums[left]>=nums[right])break;
                ++right;
            }

            temp = nums[left];
            nums[left] = nums[right-1];
            nums[right-1] = temp;
            swap(nums,left+1,len-1);
        }

    }

    public void swap(int[] nums,int left,int right){
        int temp;
        while (left<right){
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            ++left;
            --right;
        }
    }



}


