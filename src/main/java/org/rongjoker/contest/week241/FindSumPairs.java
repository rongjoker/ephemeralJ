package org.rongjoker.contest.week241;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @date 05/16/2021
 * 5761. 找出和为指定值的下标对 https://leetcode-cn.com/problems/finding-pairs-with-a-certain-sum/
 * 吸纳了两数之和的思想
 */
public class FindSumPairs {


    int[] nums1, nums2;

    Map<Integer, Integer> hash = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int j : nums2) {
            hash.put(j, hash.getOrDefault(j, 0) + 1);
        }
    }

    public void add(int index, int val) {
        hash.put(this.nums2[index], hash.get(this.nums2[index]) - 1);
        this.nums2[index] += val;
        hash.put(this.nums2[index], hash.getOrDefault(this.nums2[index],0) + 1);

    }

    public int count(int tot) {
        int c = 0;
        for (int j : nums1) {
            Integer cur = hash.get(tot - j);
            if(null!=cur)
                c += cur;
        }
        return c;
    }


}
