package org.rongjoker.sw;

import org.junit.Test;

import java.util.HashSet;

/**
 * @date 01/14/2021
 * 219. 存在重复元素 II https://leetcode-cn.com/problems/contains-duplicate-ii/
 */
public class ContainsDuplicateIi {

    @Test
    public void test219() {

        int[] nums = {1, 0, 1, 1};
        int k = 0;

        System.out.println(containsNearbyDuplicate(nums, k));
        System.out.println(containsNearbyDuplicateSw(nums, k));


    }


    //1442 ms,24.23%，几乎相当于暴力
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length==0)return false;

        int left = 0, n = nums.length;
        for (int i = 1; i <= k; i++) {
            while (left + i < n) {
                if (nums[left] == nums[left + i]) return true;
                else left++;
            }
            left = 0;
        }
        return false;
    }


    /**
     * 11ms，42.81%
     * 滑动窗口,这个最大值k就是一个窗口，只要窗口内有重复数据就返回true
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicateSw(int[] nums, int k) {
        if(nums.length==0)return false;
        if(k==0)return false;

        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(set.contains(nums[i]))return true;

            set.add(nums[i]);

            if(set.size()>k)
                set.remove(nums[i-k]);//相当于窗口向右移动一位，最左边删掉一个
        }
        return false;
    }

}
