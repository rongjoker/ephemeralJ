package org.rongjoker.prefix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 06/30/2021
 * 974. 和可被 K 整除的子数组 https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
 *
 * 取模回归正数
 */
public class SubarraysDivByK974 {


    @Test
    public void test974(){
        System.out.println(subarraysDivByK3(new int[]{4,5,0,-2,-3,1},5));

    }


    /**
     * 前缀和，会超时
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByK(int[] nums, int k) {

        int ans = 0, pre = 0, len = nums.length;
        int[] prefix = new int[len + 1];

        for (int i = 0; i < len; i++) {
            pre += nums[i];
            prefix[i + 1] = pre;
            for (int j = 0; j <= i; j++) {
                if ((pre - prefix[j]) % k == 0) ans++;
            }
        }

        return ans;

    }


    /**
     * 前缀和+迭代hash，超时
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByK2(int[] nums, int k) {

        int ans = 0, pre = 0;

        Map<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);

        for (int num : nums) {
            pre += num;

            for (Integer kk : hash.keySet()) {
                if ((pre - kk) % k == 0) ans+=hash.get(kk);
            }
            hash.put(pre, hash.getOrDefault(pre, 0) + 1);//保存前缀

        }
        return ans;
    }


    /**
     * 前缀和+求余数优化
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByK3(int[] nums, int k) {

        int ans = 0, pre = 0;
        Map<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);

        for (int num : nums) {
            pre += num;
            int seq = (pre % k + k) % k;
            ans += (hash.getOrDefault(seq, 0));//到hash里找需要减去的前缀
            hash.put(seq, hash.getOrDefault(seq, 0) + 1);//保存前缀

        }
        return ans;
    }

}
