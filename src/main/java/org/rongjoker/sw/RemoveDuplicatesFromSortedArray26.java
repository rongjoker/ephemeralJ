package org.rongjoker.sw;

import org.junit.Test;

/**
 * @date 04/06/2021
 *
 * 80. 删除有序数组中的重复项 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 原数组比如10个数字，重复了2个，去掉后长度是8，也就是返回8就可以，就是保证前8个不重复就可以了，后2个不变
 *
 * 双指针,快慢指针
 *
 */
public class RemoveDuplicatesFromSortedArray26 {

    @Test
    public void test26(){

        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));


    }


    public int removeDuplicates(int[] nums) {

        int len = nums.length;
        if(len<2)return len;
        int left=0;
        int right=1;
        while (right<len){

            if(nums[right]!=nums[left]){//两个指针一起增加，否则只增加快指针
                ++left;
                if(left<right)
                    nums[left]=nums[right];
            }
            ++right;
        }


        return left+1;

    }
}
