package org.rongjoker.bt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date 03/10/2021
 * 90. 子集 II https://leetcode-cn.com/problems/subsets-ii/
 * 包含重复元素的整数数组
 * 无顺序要求的回溯算法:两次递归，实现回溯算法,如何剪掉重复的字段是个大问题
 */
public class SubsetsWithDup90 {

    @Test
    public void test90() {

        subsetsWithDup(new int[]{1, 1, 2, 2});

        permute.forEach(System.out::println);
    }

    List<List<Integer>> permute = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, 0, nums.length);
        return permute;
    }


    public void backtrack(int[] nums, int index, int len) {
        permute.add(new ArrayList<>(path));

        for (int i = index; i < len; i++) {
            if (i != index && nums[i] == nums[i - 1])//去重
                continue;

            path.add(nums[i]);
            backtrack(nums, i + 1, len);
            //回溯
            path.remove(path.size() - 1);
        }
    }


}
