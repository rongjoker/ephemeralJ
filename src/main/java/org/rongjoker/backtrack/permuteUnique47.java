package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @date 03/04/2021
 * 47. 全排列 II  https://leetcode-cn.com/problems/permutations-ii/
 * 46题的升级版本，给定一个可包含重复数字的序列
 * 依然是回溯法，需要标记已经使用过的指针数字,做减枝
 */
public class permuteUnique47 {


    @Test
    public void test47() {

        int[] nums = {1, 3, 2};

        permute = permuteUnique(nums);

        permute.forEach(System.out::println);


    }

    List<List<Integer>> permute = new ArrayList<>();

    int len, temp;

    public List<List<Integer>> permuteUnique(int[] nums) {
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



        Set<Integer> set = new HashSet<>();

        for (int i = start; i < len; i++) {
//            System.out.println("start:"+start+";i:"+i);
            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            swap(nums, i, start);
            permute(nums, start + 1);
            swap(nums, i, start);//这一行即所谓的[回溯]

        }



    }







}
