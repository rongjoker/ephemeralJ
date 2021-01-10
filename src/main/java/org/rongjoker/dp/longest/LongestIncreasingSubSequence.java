package org.rongjoker.dp.longest;


import org.junit.Test;

/**
 * @date 01/10/2021
 * 300. 最长递增子序列 https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestIncreasingSubSequence {

    @Test
    public void test300() {

        int[] nums = new int[]{7, 7, 7, 7, 7, 7, 7};

        System.out.println(lengthOfLIS(nums));


    }


    /**
     * ,O(n^2)的复杂度的dp
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        int max = 1;
        int n = nums.length;
        int[] dp = new int[n];

        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp = 1;

            for (int j = 0; j < i && j >= 0; j++) {
                if (nums[i] > nums[j]) {
                    temp = Math.max(dp[j] + 1, temp);
                }
            }
            dp[i] = temp;

            max = Math.max(max, temp);

        }

        return max;
    }


}
