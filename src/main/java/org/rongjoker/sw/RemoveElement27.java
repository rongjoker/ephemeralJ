package org.rongjoker.sw;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 04/06/2021
 * 27. 移除元素 https://leetcode-cn.com/problems/remove-element/
 * 双指针-快慢指针
 *
 */
public class RemoveElement27 {


    @Test
    public void test27(){

        int[] nums = {0,1,2,2,3,0,4,2};int val = 2;
        System.out.println(removeElement(nums,val));
        System.out.println(Arrays.toString(nums));

    }



    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        if(len==0) return 0;
        if(len==1){
            if(nums[0]==val)return 0;
            else return 1;
        }
        int left=-1,right=0;
        while(right<len){
            if(nums[right]!=val){
                ++left;
                if(right>left)nums[left] = nums[right];

            }
            ++right;
        }
        return left+1;
    }
}
