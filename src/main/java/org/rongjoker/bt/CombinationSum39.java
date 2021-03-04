package org.rongjoker.bt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 03/05/2021
 * 39. 组合总和  https://leetcode-cn.com/problems/combination-sum/
 * <p>
 * 回溯法，很接近dfs的一个题目，但是数字可以重复被选取,这一步较难
 * <p>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 */
public class CombinationSum39 {


    @Test
    public void test39() {

        int[] nums = {2, 3, 6, 7};
        int target = 7;

        permute = combinationSum(nums, target);

        permute.forEach(System.out::println);


    }

    List<List<Integer>> permute = new ArrayList<>();


    int len;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        len = candidates.length;

        combination(candidates,target,0,new ArrayList<>());

        return permute;

    }


    public void combination(int[] candidates,  int target,int start, List<Integer> integers) {

        if (start == len) {
            return;
        }

        if(target==0){
            permute.add(integers);
        }

        for (int i = start; i < len; i++) {

            if (candidates[i] == target) {
                integers.add(candidates[i]);

            } else if (candidates[i] < target) {

                int s = target / candidates[i];

                for (int j = 0; j < s; j++) {
                    integers.add(candidates[i]);
                    target -= candidates[i];
                    combination(candidates,  target,start + 1, integers);
                }


            }


        }


    }
}
