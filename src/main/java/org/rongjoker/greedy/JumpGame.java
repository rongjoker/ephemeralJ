package org.rongjoker.greedy;

import org.junit.Test;

/**
 * @date 01/12/2021
 * 55. 跳跃游戏 https://leetcode-cn.com/problems/jump-game/
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 */
public class JumpGame {

    @Test
    public void test55(){

//        int [] nums = {2,0,1,1,2,1,0,0,0};
        int [] nums = {2};

        System.out.println(canJump(nums));

    }


    /**
     * 贪心算法，有优化空间：从后向前遍历
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        int current = 0,length = nums.length,step = nums[0],temp=0,tempStep=0,index=0,space = 0;

        while (step>0 && (current+nums[current]) <length-1){
            for (index = 1; index <= step; index++) {
                tempStep = (current+index)<length?(index+nums[current+index]):(index+nums[length-1]);
                if(tempStep>temp){
                    space = index;
                    temp = tempStep;
                }
            }
            current = current + space;temp=0;space=0;
            step = nums[current];
        }
        return (current+nums[current]) >= length-1;

    }

}
