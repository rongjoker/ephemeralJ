package org.rongjoker.dp.ways;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 01/14/2021
 * 494. 目标和 https://leetcode-cn.com/problems/target-sum/
 */
public class targetSum {

    @Test
    public void test494() {

        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(findTargetSumWays(nums, S));

    }


    public int findTargetSumWays(int[] nums, int S) {

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);
        int n = nums.length, left,right,temp,tl,tr;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;
            for (int k = 0; k <= S; k++) {
                dp.putIfAbsent(k, 0);temp = dp.get(k);
                left=nums[i];right=left*(-1);

                dp.putIfAbsent(k-left, 0);
                dp.putIfAbsent(k-right, 0);
                dp.put(k, dp.get(k-left) + dp.get(k-right));
                dp.put(k-left, temp);
                dp.put(k-right, temp);

            }
        }

        return dp.get(S);

    }
}
