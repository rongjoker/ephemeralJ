package org.rongjoker.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 *
 *  @date 08/17/2021
 * 1679. K 和数对的最大数目 https://leetcode-cn.com/problems/max-number-of-k-sum-pairs/
 *
 * 给你一个整数数组 nums 和一个整数 k 。
 *
 * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
 *
 * 返回你可以对数组执行的最大操作数。
 *
 * 输入：nums = [1,2,3,4], k = 5
 * 输出：2
 * 解释：开始时 nums = [1,2,3,4]：
 * - 移出 1 和 4 ，之后 nums = [2,3]
 * - 移出 2 和 3 ，之后 nums = []
 * 不再有和为 5 的数对，因此最多执行 2 次操作。
 *
 *
 */
public class MaxOperations1679 {


    @Test
    public void test1679(){
        System.out.println(maxOperations(new int[]{1,2,3,4},5));
        System.out.println(maxOperations(new int[]{3,1,3,4,3},6));

    }


    public int maxOperations(int[] nums,int k){

        int ans = 0;
        int len = nums.length;
        if(len<=1)return ans;

        Map<Integer, Integer> dict = new HashMap<>();
        for (int num : nums) {
            if(num<k){
                int key = k - num;
                int tt = dict.getOrDefault(key, 0);
                if (tt > 0) {
                    dict.put(key, tt - 1);
                    ans++;
                } else dict.put(num, dict.getOrDefault(num, 0) + 1);
            }else dict.put(num, dict.getOrDefault(num, 0) + 1);

        }

        return ans;

    }

}
