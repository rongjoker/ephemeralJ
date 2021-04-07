package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 04/07/2021
 * 81. 搜索旋转排序数组 II https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * 二分查找，注意左边界和右边界的边界条件
 * <p>
 * 33题的高阶应用题，包含重复数据
 * 4月7日 每日一题
 */
public class SearchInRotatedSortedArray81 {


    @Test
    public void test81() {

        int[] nums = {2,2,2,2,2,2,0};
        System.out.println(search(nums, 0));

    }


    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }
        if (len == 1) {
            return nums[0] == target;
        }
        return search(nums, target, 0, len - 1, len)!=-1;
    }


    public int search(int[] nums, int target, int left, int right, int len) {
        if (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[0] < nums[middle]) {
                if (nums[0] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
                return search(nums, target, left, right, len);
            } else if (nums[0] > nums[middle]) {
                if (nums[middle] < target && target <= nums[len - 1]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
                return search(nums, target, left, right, len);
            } else {

                int search = search(nums, target, middle + 1, right, len);
                if (search == -1) return search(nums, target, left, middle - 1, len);
                else return search;
            }
        }
        return -1;


    }


}
