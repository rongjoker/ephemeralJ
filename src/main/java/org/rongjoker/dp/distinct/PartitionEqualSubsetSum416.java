package org.rongjoker.dp.distinct;

import org.junit.Test;

/**
 * @date 02/09/2021
 * 416. 分割等和子集 https://leetcode-cn.com/problems/partition-equal-subset-sum/
 *  给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *  本质是01充分背包(必须装满)，容量是总容量的一半
 *
 *
 */
public class PartitionEqualSubsetSum416 {


    @Test
    public void test416(){

        int[] nums = {14,9,8,4,3,2};
        System.out.println(canPartition(nums));



    }




    public boolean canPartition(int[] nums) {


        int len = nums.length,sum =0;

        if(len<2)return false;

        for (int i = 0; i <len; i++) {
                sum+=nums[i];
        }

        if(sum%2>0)return false;
        sum/=2;

        int[] dp = new int[sum+1];
        for (int i = 1; i <=sum; i++) {
            dp[i] = -1;
        }

        for (int i = 0; i < len; i++) {

            System.out.println(nums[i]);

            for (int j = sum; j >=nums[i]; j--) {

                if(dp[j]<0){
                    if (dp[j- nums[i]]>-1){
                        dp[j] = 1;
                    }else dp[j] = -1;
                }
            }

            if(dp[sum]>0)return true;



        }


        return false;

    }


}
