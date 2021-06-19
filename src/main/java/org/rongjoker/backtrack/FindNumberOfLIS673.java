package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @date 06/18/2021
 *673. 最长递增子序列的个数  https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
 *
 */
public class FindNumberOfLIS673 {

    @Test
    public void test673(){
//        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(findNumberOfLIS(new int[]{2,2,2,2,2}));
    }


    /**
     * dfs回溯法会超时
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        if(nums.length<=1)return nums.length;
        ans = new int[2];
        track(nums,nums.length,0,0);
//        System.out.println(permute);
        return ans[1];

    }


    int[] ans;
    Deque<Integer> queue = new ArrayDeque<>();
//    List<List<Integer>> permute = new ArrayList<>();

    public void track(int[] nums,int len,int start,int cur){
        if(start==len)return;

        if(queue.isEmpty() || nums[start] >queue.peekLast()) {
            queue.addLast(nums[start]);
            if(queue.size()>ans[0]){
                ans[0] = queue.size();
                ans[1] = 1;
            }else if(queue.size()==ans[0]){
                ans[1]++;
            }
//            permute.add(new ArrayList<>(queue));
            track(nums,len,start+1,cur+1);
            queue.removeLast();
            track(nums,len,start+1,cur+1);
        }else track(nums,len,start+1,cur);


    }







}
