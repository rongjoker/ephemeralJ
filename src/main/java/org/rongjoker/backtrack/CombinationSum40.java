package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.*;

/**
 * @date 03/05/2021
 * 40. 组合总和 II  https://leetcode-cn.com/problems/combination-sum-ii/
 * <p>
 * 回溯法，39题目的升级版本，但是不允许重复使用,实际上反而更容易
 */
public class CombinationSum40 {


    @Test
    public void test40() {

        int[] nums = {10,1,2,7,6,1,5};
        int target = 8;

        permute = combinationSum2(nums, target);

        permute.forEach(System.out::println);


    }

    List<List<Integer>> permute = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();


    int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

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

            if (i>start && candidates[i] ==candidates[i-1])//i>start而不是i>0
                continue;

            path.addLast(candidates[i]);
            combination(candidates, target - candidates[i], i+1);//允许重复

            //这一段即所谓的[回溯]
            path.removeLast();
        }


    }
}
