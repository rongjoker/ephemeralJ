package org.rongjoker.dp.target;

import org.junit.Test;

/**
 *  @date 03/14/2021
 *  213. 打家劫舍 II  https://leetcode-cn.com/problems/house-robber-ii/
 *
 *  这个地方所有的房屋都 围成一圈
 *  比较麻烦的地方在于如何记录第一个位置是否被选中
 *
 *
 *
 */
public class HouseRobber213 {



    @Test
    public void test213(){

//        int[] array = {2,1,1,3};
        int[] array = {1,3,1,3,100};

        System.out.println(rob(array));
    }

    public int rob(int[] nums) {

        if(nums.length==0)return 0;

        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);

        int len = nums.length;

        //0-len-1和1-len两个范围
        return Math.max(rob_try(nums,0,len-1),rob_try(nums,1,len));
    }

    public int rob_try(int[] nums,int start,int end) {

        if(nums.length==0)return 0;

        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);

        int dp1 = 0,dp2 = 0;


        while (start<end){
            int dp = Math.max(dp1 +nums[start],dp2);
            dp1 = dp2;dp2 = dp;//互相滚动
            ++start;
        }

        return dp2;
    }

}
