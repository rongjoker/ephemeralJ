package org.rongjoker.array;


import org.junit.Test;

import java.util.*;

/**
 * @date 06/29/2021
 * 128. 最长连续序列 https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * hash做桶排序可以优化到o(n)
 *
 *
 *
 */
public class LongestConsecutive128 {


//    [100,4,200,1,3,2]
//            [0,3,7,2,5,8,4,6,0,1]

    @Test
    public void test128(){
//        System.out.println(longestConsecutive(new int[]{0,1,2,4,8,5,6,7,9,3,55,88,77,99,999999999}));
        System.out.println(longestConsecutive2(new int[]{0,1,2,4,8,5,6,7,9,3,55,88,77,99,999999999}));
//        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
//        System.out.println(longestConsecutive2(new int[]{100,4,200,1,3,2}));

    }


    public int longestConsecutive(int[] nums) {
        if(nums.length==0)return 0;
        Arrays.sort(nums);
        int ans = 1,temp=1,len = nums.length;
        for(int i=1;i<len;++i){
            if(nums[i]==nums[i-1]+1)temp++;
            else if (nums[i]>=nums[i-1]+1){
                ans = Math.max(ans,temp);
                if(nums[i]>nums[i-1]+1)temp=1;
            }
        }
        return Math.max(ans,temp);

    }


    //[0,1,2,4,8,5,6,7,9,3,55,88,77,99,999999999]
    //[0,-1]
    public int longestConsecutive2(int[] nums) {
        if(nums.length==0)return 0;
        int ans = 0,temp=0;
        Set<Integer> dict = new HashSet<>();
        Map<Integer,Integer> used = new HashMap<>();

        for(int n:nums)dict.add(n);

        loop:for(int n:nums){
            if(used.containsKey(n))continue;

            temp = 1;
            int t = n;
            while (dict.contains(t+1)){
                if(used.containsKey(t+1)){
                    temp += used.get(t+1);
                    used.put(n,temp);
                    ans = Math.max(temp,ans);
                    continue loop;
                }else used.put(t+1,1);
                temp++;
                t++;
            }

            used.put(n,temp);
            ans = Math.max(temp,ans);

        }

        return Math.max(ans,temp);

    }
}
