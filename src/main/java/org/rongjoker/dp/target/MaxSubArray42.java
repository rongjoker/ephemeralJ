package org.rongjoker.dp.target;

import org.junit.Test;

/**
 *
 * @date 03/14/2021
 *
 * 剑指 Offer 42. 连续子数组的最大和 https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * 与53题相同
 */
public class MaxSubArray42 {


    @Test
    public void test42(){

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArray(nums));

    }


    public int maxSubArray(int[] nums) {

        int len = nums.length,max=Integer.MIN_VALUE,temp=0;

        for (int i = 0; i < len; i++) {
            temp+=nums[i];
            //不需要考虑当前值大于还是小于0，只要比当前小，就替换,比如-2,-1这种情况
            if(temp<nums[i])
                temp=nums[i];

            if(temp>max)max=temp;

        }

        return max;
    }


}
