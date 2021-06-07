package org.rongjoker.dp.distinct;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @date 01/14/2021
 * @date 01/15/2021
 *  经典dp题目
 * 494. 目标和 https://leetcode-cn.com/problems/target-sum/
 */
public class targetSum {

    @Test
    public void test494() {

//        [9,7,0,3,9,8,6,5,7,6]
//        2

        int[] nums = {9,7,0,3,9,8,6,5,7,6};
//        nums = new int[]{1};
        int S = 2;
        System.out.println(findTargetSumWaysOptimize(nums, S));
        System.out.println(findTargetSumWays2(nums, S));

    }


    /**
     * 有点类似分组背包的强制最大长度模式
     * 这种模式会有很多的无用功
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (S > 1000 || S < -1000)
            return 0;
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> current = null;

        for (int k = -1000; k <= 1000; k++) {
            previous.put(k, 0);
        }
        previous.put(0, 1);
        int n = nums.length, left, right;
        for (int i = 0; i < n; i++) {
            current = new HashMap<>();
            Set<Integer> integers = previous.keySet();
            for (int k : integers) {
                left = nums[i];right = left * (-1);
                current.put(k, (null == previous.get(k - left) ? 0 : previous.get(k - left)) + (null == previous.get(k - right) ? 0 : previous.get(k - right)));
            }
            previous = current;

        }

        return current != null ? current.get(S) : 0;

    }


    /**
     * 优化为动态hashmap,这种有bug，无法处理数组包含重复的情况
     * 用-left 替换-1*left能节省速度
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWaysOptimize(int[] nums, int S) {
        int n = nums.length, left, right;
        int sum =0;
        for (int i = 0; i < n; i++) {
            sum+=nums[i];
        }

        if (S > sum || S < -sum)
            return 0;
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> current = null;

        //所有这些数字能组成的最大值和最小值
        for (int k = -sum; k <= sum; k++) {
            previous.put(k, 0);
        }
        previous.put(0, 1);

        //交替使用两个hashmap（其实就是压缩dp空间到2个）
        for (int i = 0; i < n; i++) {
            current = new HashMap<>();
            Set<Integer> integers = previous.keySet();
            for (int k : integers) {
                left = nums[i];right = -left;
                current.put(k, (null == previous.get(k - left) ? 0 : previous.get(k - left)) + (null == previous.get(k - right) ? 0 : previous.get(k - right)));
            }
            previous = current;

        }

        return current != null ? current.get(S) : 0;

    }

    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum+=num;
        if(sum<Math.abs(target))return 0;

        //这一步费解
        if((sum + target) % 2 == 1) {
            return 0;
        }

        Map<Integer,Integer> dp = new HashMap<>();
        dp.put(0,1);
        Map<Integer,Integer> dp2;
        for(int num:nums){
            dp2 = new HashMap<>();
            for(Integer k:dp.keySet()){
                dp2.put(k-num,dp.getOrDefault(k,0)+dp2.getOrDefault(k-num,0));
                dp2.put(k+num,dp.getOrDefault(k,0)+dp2.getOrDefault(k+num,0));
            }
            dp = dp2;

        }

        return dp.getOrDefault(target,0);

    }


}
