package org.rongjoker.dp.longest;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @date 06/18/2021
 * @date 06/25/2021
 *673. 最长递增子序列的个数  https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
 * 最长递增子序列的进阶问题，难度高很多
 *
 */
public class FindNumberOfLIS673 {

    @Test
    public void test673(){
        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(findNumberOfLIS2(new int[]{2,2,2,2,2}));
        System.out.println(findNumberOfLIS2(new int[]{1,2,4,3,5,4,7,2}));
    }


    public int findNumberOfLIS2(int[] nums) {
        if(nums.length==0)return 0;
        int max = 1,len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int count = 1;
        for(int i = 1;i<len;++i){
            int t = 1,cur_count = 1;
            for(int j = 0;j<i;j++){
                if(nums[i]>nums[j]) {
                    if(dp[j][0]+1>t){
                        t = dp[j][0]+1;//最长的子序列
                        cur_count = dp[j][1];

                    }else if (dp[j][0]+1==t) cur_count+=dp[j][1];
                }
            }
            dp[i][0] = t;
            dp[i][1] = cur_count;

            if(t>max){
                count = cur_count;
                max = t;
            }else if(t==max) count +=cur_count;
        }
        return count;

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
