package org.rongjoker.offer;

import org.junit.Test;

/**
 *  @date 04/04/2021
 *  剑指 Offer 11. 旋转数组的最小数字 https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 */
public class MinArray11 {


    @Test
    public void test11(){

        int[] numbers = {3, 4, 5, 1, 2};
        System.out.println(minArray(numbers));

    }



    public int minArray(int[] nums) {
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.min(nums[0],nums[1]);
        int len=nums.length;

        int minK = findMinK(nums, 0, len - 1, len);

        return minK!=-1?minK:nums[0];


    }


    public int findMinK(int[] nums,int left,int right,int len) {
        if(left<0 || right>len-1) return -1;

        if (right>=left){
            int middle = left + ((right - left)>>1);

            if(nums[middle]>nums[0]){
                if(middle==len-1) return nums[0];
                if(nums[middle+1]<=nums[0])return nums[middle+1];
                else return findMinK(nums,middle+1,right,len);
            }else if(nums[middle]<nums[0]){
                if(nums[middle-1]>=nums[0])return nums[middle];
                else return findMinK(nums,left,middle-1,len);
            }else {
                int tem = findMinK(nums,left,middle-1,len);
                if(tem==-1) return findMinK(nums,middle+1,right,len);
                else return tem;
            }
        }

        return -1;


    }

}
