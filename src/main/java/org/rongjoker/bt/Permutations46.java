package org.rongjoker.bt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 03/03/2021
 * @date 03/04/2021
 * @date 03/05/2021
 * 46. 全排列 https://leetcode-cn.com/problems/permutations/
 * 回溯法的入门题目,每一次指针从左走到右，就相当于一种情况
 */
public class Permutations46 {


    @Test
    public void test46() {

        int[] nums = {1, 2, 3};

        permute = permute(nums);

        permute.forEach(System.out::println);


    }

    List<List<Integer>> permute = new ArrayList<>();

    int len, temp;

    public List<List<Integer>> permute(int[] nums) {
        len = nums.length;
        permute(nums, 0);
        return permute;
    }

    public void swap(int[] nums, int x, int y) {

        if (x == y) return;

        temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;

    }

    public void permute(int[] nums, int start) {

        if (start == len - 1) {//关键的一步可以把start看作指针，指针每一轮指到最右则，说明找到了一种排序,也就是阶乘
            List<Integer> ints = new ArrayList<>();
            for (int num : nums) {
                ints.add(num);
            }
            permute.add(ints);
            return;//相当于只有1个数字做全排列
        }

        for (int i = start; i < len; i++) {
            swap(nums, i, start);
            permute(nums, start + 1);
            swap(nums, i, start);//这一行即所谓的[回溯]
        }

    }


}
