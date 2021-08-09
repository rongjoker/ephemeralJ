package org.rongjoker.sw;

import org.junit.Test;

/**
 *
 *  @date 01/15/2021
 *   713. 乘积小于K的子数组 https://leetcode-cn.com/problems/subarray-product-less-than-k/
 *   双指针
 *
 *
 */
public class NumSubarrayProductLessThanK713 {

    @Test
    public void test713(){
//        System.out.println(numSubarrayProductLessThanK(new int[]{10,5,2,6},100));
//        System.out.println(numSubarrayProductLessThanK(new int[]{4,5,6},3));
        System.out.println(numSubarrayProductLessThanK(new int[]{1,1,1},1));

    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1)return 0;//注意，小于等于1都应该跳过

        int ans = 0;
        int len = nums.length;
        if(len==1)return nums[0]<k?1:0;

        int left = 0,right = 1;
        int product = nums[left];
        for(int i=0;i<len;i++){
            if(i>0){
                product/=nums[i-1];
            }
            while(product<k && right<len){
                product*=nums[right];
                right++;
            }
            if(product<k)ans+=(right - i );
            else ans+=(right - i -1);
        }

        return ans;

    }
}
