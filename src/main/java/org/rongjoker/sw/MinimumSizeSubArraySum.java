package org.rongjoker.sw;

import org.junit.Test;

/**
 * @date 01/14/2021
 * 209. 长度最小的子数组 https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * 双指针
 */
public class MinimumSizeSubArraySum {


    @Test
    public void test209() {
        int[] nums = {6};
        int s = 7;
        System.out.println(minSubArrayLen(s, nums));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        int left = 0, right = 0, ts = 0;
        while (left <= n - 1 && right <= n - 1) {
            ts += nums[right];
            if (ts >= s) {
                while (left <= right && ts >= s) {
                    min = Math.min(min, right - left + 1);
                    ts -= nums[left++];
                }
            }
            right++;

        }
        return min == Integer.MAX_VALUE ? 0 : min;

    }
}
