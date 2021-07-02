package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.*;

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

        if(start==len){
            return;
        }

        path.add(nums[start]);
        permute.add(new ArrayList<>(path));
        backtrack(start+1,len,nums);
        path.remove(path.size()-1);
        backtrack(start+1,len,nums);//这一步很关键，与普通的回溯法不同

    }


    @Test
    public void test78_2(){

        System.out.println(subsets2(new int[]{1,2,3}));

    }

    public List<List<Integer>> subsets2(int[] nums) {
        ans = new ArrayList<>();
        ans.add(new ArrayList<>());

        if(nums.length==0)
            return ans;

        backtrack2(0,nums.length,nums);

        return ans;
    }


    List<List<Integer>> ans;
    Deque<Integer> queue = new ArrayDeque<>();


    public void backtrack2(int start,int len,int[] nums){

        if(start==len){
            return;
        }
        queue.addLast(nums[start]);
        ans.add(new ArrayList<>(queue));
        backtrack2(start+1,len,nums);
        queue.removeLast();
        backtrack2(start+1,len,nums);//这一步很关键，与普通的回溯法不同

    }


    @Test
    public void test78_3(){

        System.out.println(subsets3(new int[]{1,2,2}));

    }

    public List<List<Integer>> subsets3(int[] nums) {
        ans3 = new ArrayList<>();
        ans3.add(new ArrayList<>());

        if(nums.length==0)
            return ans3;

        backtrack3(0,nums.length,nums);

        return ans3;
    }


    List<List<Integer>> ans3;
    Deque<Integer> queue3 = new ArrayDeque<>();


    public void backtrack3(int start,int len,int[] nums){

        if(start==len){
            return;
        }

        for(int i=start;i<len;++i){
            queue3.addLast(nums[i]);
            ans3.add(new ArrayList<>(queue3));
            backtrack3(i+1,len,nums);
            queue3.removeLast();

        }
    }


    @Test
    public void test78_4(){

        System.out.println(subsets4(new int[]{1,1,2,2}));

    }

    public List<List<Integer>> subsets4(int[] nums) {

        ans4 = new ArrayList<>();
        if(nums.length==0)
            return ans4;

        Arrays.sort(nums);

        backtrack4(0,nums.length,nums);

        return ans4;
    }


    List<List<Integer>> ans4;
    Deque<Integer> queue4 = new ArrayDeque<>();


    public void backtrack4(int start,int len,int[] nums){
        ans4.add(new ArrayList<>(queue4));
        for(int i=start;i<len;++i){
            //去重,在[这一轮循环]里，不能重复,如果是这一轮的第一个，与前面的重复，不是这一轮的重复
            if (i != start && nums[i] == nums[i - 1])continue;
            queue4.addLast(nums[i]);
            backtrack4(i+1,len,nums);
            queue4.removeLast();
        }
    }


}
