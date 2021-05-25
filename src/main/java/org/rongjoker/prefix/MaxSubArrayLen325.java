package org.rongjoker.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 03/26/2021
 * <p>
 * 325. 和等于 k 的最长子数组长度 https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/
 * <p>
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。
 *
 * 前缀和，结合hash表来优化 是560题的进阶
 */
public class MaxSubArrayLen325 {


    public int maxSubArrayLen(int[] nums, int k) {

        int pre=0,max=0,len = nums.length;

        //记录前缀和对应的最小长度
        Map<Integer, Integer> hash = new HashMap<>();
        hash.put(0, -1);
        for (int i = 0; i < len; i++) {
            pre += nums[i];
            if(hash.get(pre - k)!=null){
                max = Math.max(max,i-hash.get(pre - k));
            }

            hash.putIfAbsent(pre, i);//保存前缀的最小下标，这个方法性能更好

        }

        return max;

    }
}
