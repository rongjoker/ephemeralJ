package org.rongjoker.binarysearch;


import org.junit.Test;

/**
 * @date 02/25/2021
 * 287. 寻找重复数 https://leetcode-cn.com/problems/find-the-duplicate-number/
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以在不修改数组 nums 的情况下解决这个问题吗？
 * 你可以只用常量级 O(1) 的额外空间解决这个问题吗？
 * 你可以设计一个时间复杂度小于 O(n2) 的解决方案吗？
 * <p>
 * 利用抽屉原理解决
 */
public class FindDuplicate287 {

    @Test
    public void test287() {

        int[] nums = {3, 1, 3, 4, 2};

        System.out.println(findDuplicate(nums));


    }


    public int findDuplicate(int[] nums) {

        int start = 1, end = nums.length - 1, size = 0;//不是序号而是数字

        while (end > start) {
            int middle = (start + end) >>> 1;
            for (int num : nums) {
                if (num <= middle) {
                    size += 1;
                }
            }

            if (size > middle) end = middle;
            else
                start = middle + 1;


            size = 0;

        }

        return start;

    }


}
