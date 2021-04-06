package org.rongjoker.sw;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 04/06/2021
 *
 * 80. 删除有序数组中的重复项 II  https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 4月6日 每日一题
 * 双指针-快慢指针,26题进阶
 *
 */
public class RemoveDuplicatesFromSortedArrayIi80 {

    @Test
    public void test80(){
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));


    }


    public int removeDuplicates(int[] nums) {

        int len = nums.length;
        if(len<3)return len;


        int slow = 2, fast = 2;
        while (fast < len) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;


    }
}
