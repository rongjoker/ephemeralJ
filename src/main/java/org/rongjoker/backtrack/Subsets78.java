package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 03/09/2021
 * @date 03/10/2021
 * 78. 子集 https://leetcode-cn.com/problems/subsets/
 * 无顺序要求的回溯算法:两次递归，实现回溯算法
 *
 */
public class Subsets78 {

    @Test
    public void test78(){

        subsets(new int[]{1,2,3});

        permute.forEach(System.out::println);
    }

    public List<List<Integer>> subsets(int[] nums) {

        permute.add(new ArrayList<>());

        if(nums.length==0)
            return permute;

        backtrack(0,nums.length,nums);


        return permute;
    }

    List<List<Integer>> permute = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public void backtrack(int start,int len,int[] nums){

        if(start==len)return;

        path.add(nums[start]);

        permute.add(new ArrayList<>(path));

        //包含当前点向下递归
        backtrack(start+1,len,nums);

        //不包含当前点向下递归，即回溯算法
        path.remove(path.size()-1);
        backtrack(start+1,len,nums);

    }







}
