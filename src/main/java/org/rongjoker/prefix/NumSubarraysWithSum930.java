package org.rongjoker.prefix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  @date 06/30/2021
 *  930. 和相同的二元子数组 https://leetcode-cn.com/problems/binary-subarrays-with-sum/
 *
 *
 */
public class NumSubarraysWithSum930 {


    @Test
    public void test930(){
        System.out.println(numSubarraysWithSum(new int[]{1,0,1,0,1},2));
    }


    /**
     *
     * 前缀和 超时
     * 用hash优化
     *
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int ans = 0, pre = 0;

        Map<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);

        for (int num : nums) {
            pre += num;
            ans += hash.getOrDefault(pre - goal, 0);//到hash里找需要减去的前缀
            hash.put(pre, hash.getOrDefault(pre, 0) + 1);//保存前缀

        }

        return ans;

    }





}
