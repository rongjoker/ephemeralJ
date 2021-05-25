package org.rongjoker.prefix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 03/02/2021
 * <p>
 * 560. 和为K的子数组 https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 前缀和，结合hash表来优化
 */
public class SubArraySum560 {

    @Test
    public void test560() {
        int[] nums = {1, 1, 1};
        System.out.println(subarraySumOptimize(nums, 2));


    }


    /**
     * 默认的前缀和，复杂度在O(n^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        int len = nums.length, total = 0;

        int[] ps = new int[len + 1];

        for (int i = 0; i < len; i++) {
            ps[i + 1] = ps[i] + nums[i];
        }

        for (int i = 1; i < len + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (ps[i] - ps[j] == k) ++total;
            }
        }

        return total;


    }


    /**
     * 利用哈希表来优化O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumOptimize(int[] nums, int k) {

        int len = nums.length, total = 0, pre = 0;

        Map<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);

        for (int i = 0; i < len; i++) {
            pre += nums[i];
            total += hash.getOrDefault(pre - k, 0);//到hash里找需要减去的前缀
            hash.put(pre, hash.getOrDefault(pre, 0) + 1);//保存前缀

        }

        return total;


    }


}
