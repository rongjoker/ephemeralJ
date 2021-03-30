package org.rongjoker.sw;

import org.junit.Test;

import java.util.*;

/**
 * @date 01/15/2021
 * 15. 三数之和 https://leetcode-cn.com/problems/3sum/
 * 排序在双指针和贪心算法等问题中往往必备
 */
public class Sum3 {


    @Test
    public void test15() {


        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSumsOptimize(nums));

    }

    /**
     * 双指针
     * 去重复 利用首位元素和前一位比较来去重复（很巧妙的操作，节省时空）
     * 因为是有序的数组，所以只需要沿着数组两边向中间推进就可以，O(n)的复杂度就可以处理完一轮
     * 从最左边和左右边来开始的原理是，比如两数之和为0，必然是-4/4;-3/3;-2/2;-1/1即左侧肯定和右侧是两个极端，不会漏掉
     *
     * 67.45% 42.37%
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumsOptimize(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 3) return lists;
        Arrays.sort(nums);
        if (nums[0] > 0) return lists;//最小值肯定要小于等于=0
        int n = nums.length, left, right;
        for (int i = 0; i < n - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;//排序+比较来去重复

            left = i + 1;
            right = n - 1;
            while (right > left) {

                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[left]);
                    integers.add(nums[right]);
                    lists.add(integers);
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    while (right > left && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] > 0) right--;
                else left++;
            }


        }

        return lists;


    }


}
