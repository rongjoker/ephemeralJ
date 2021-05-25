package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.*;

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

        int[] nums = {2,3,5};
        int target = 8;

        permute = combinationSum(nums, target);

        permute.forEach(System.out::println);


    }

    List<List<Integer>> permute = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();


    int len;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        len = candidates.length;

        Arrays.sort(candidates);//排序方便剪枝

        combination(candidates, target, 0);

        return permute;

    }


    //不停的调整target，即可优化性能
    public void combination(int[] candidates, int target, int start) {

        if (target == 0) {
            permute.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {

            if(candidates[i] >target)break;//剪枝

            path.addLast(candidates[i]);

            combination(candidates, target - candidates[i], i);//允许重复

            //这一段即所谓的[回溯]
            path.removeLast();


        }


    }
}
