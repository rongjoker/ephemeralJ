package org.rongjoker.dp.distinct;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 02/05/2021
 * @date 06/24/2021
 *
 * 377. 组合总和 Ⅳ https://leetcode-cn.com/problems/combination-sum-iv/
 *
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 *
 * 典型的dp套路题目，完全背包的变形，几分钟秒做出来
 *
 */
public class CombinationSum4_377 {

    @Test
    public void test377(){

        int[] nums = {1,2,3};
        int target = 4;
//        System.out.println(combinationSum4(nums, target));
        System.out.println(combinationSum4_2(new int[]{1,2,3},4));


    }

    /**
     * 这种可以允许顺序不同，即不去重，比如1/1/2和1/2/1
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {

        if(nums.length==0 || target==0)return 0;

        int[] dp = new int[target+1];
        dp[0]=1;
        int len=nums.length;

        for (int i = 1; i <= target; i++) {

            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];

    }


    /**
     * 这种会去重
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4_2(int[] nums, int target) {

        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = num; j <= target; ++j) {
                if (dp[j - num] > 0)
                    dp[j] += dp[j - num];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[target];

    }

}
