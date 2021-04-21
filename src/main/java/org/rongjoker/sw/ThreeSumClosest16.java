package org.rongjoker.sw;

import java.util.Arrays;

/**
 * @date 04/18/2021
 *
 * 16. 最接近的三数之和 https://leetcode-cn.com/problems/3sum-closest/
 * 类似三数之和
 *
 */
public class ThreeSumClosest16 {

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        int sum,left,right,temp,current = Integer.MAX_VALUE;
        for(int i=0;i<len;++i){
            if(i>0 && nums[i]==nums[i-1])continue;

            left = i+1;right = len-1;
            while(left<right){
                sum = nums[i] + nums[left] + nums[right];
                if(sum==target)return target;
                temp = Math.abs(sum - target);
                if(temp< current){
                    current = temp;
                    min = sum;
                }
                if(sum <target){
                    while(left<right &&nums[left+1]==nums[left])left++;
                    left++;
                } else{
                    while(left<right &&nums[right-1]==nums[right])right--;
                    right--;
                }
            }
        }
        return min;

    }
}
