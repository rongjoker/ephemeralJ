package org.rongjoker.prefix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 06/30/2021
 * 1590. 使数组和能被 P 整除 https://leetcode-cn.com/problems/make-sum-divisible-by-p/
 *
 *
 *
 */
public class MinSubarray1590 {


    @Test
    public void test1590(){
        System.out.println(minSubarray(new int[]{4,4,2},7));
//        System.out.println(minSubarray(new int[]{3,1,4,2},6));
//        System.out.println(minSubarray(new int[]{6,3,5,2},9));
//        System.out.println(minSubarray(new int[]{1,2,3},3));
//        System.out.println(minSubarray(new int[]{1,2,3},7));
//        System.out.println(minSubarray(new int[]{1000000000,1000000000,1000000000},3));
    }


    public int minSubarray(int[] nums, int p) {

        int  ans = Integer.MAX_VALUE;long pre = 0,sum=0,len = nums.length;
        Map<Long, Integer> hash = new HashMap<>();
        hash.put(0L, -1);

        for (int num : nums) sum += num;

        if(sum%p==0)return 0;
        if(sum<p)return -1;

        long target = sum%p;

        for (int i = 0; i < len; i++) {
            pre += nums[i];
            long seq = pre % p;

            long a = (pre - target)%p;

            if(hash.containsKey(a)){
                ans = Math.min(ans,i - hash.get(a));
            }
            hash.put(seq, i);//保存下标
        }

        return (ans==Integer.MAX_VALUE || ans == len)?-1:ans;


    }



}
