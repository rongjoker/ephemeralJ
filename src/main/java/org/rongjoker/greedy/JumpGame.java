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
//        int [] nums = {2};

//        System.out.println(canJump(nums));
        //[5,9,3,2,1,0,2,3,3,1,0,0]

        System.out.println(canJump4(new int[]{3,2,1,0,4}));
        System.out.println(canJump(new int[]{3,2,1,0,4}));
        System.out.println("-----");
        System.out.println(canJump4(new int[]{2,0,1,1,2,1,0,0,0}));
        System.out.println(canJump(new int[]{2,0,1,1,2,1,0,0,0}));
        System.out.println("-----");
        System.out.println(canJump4(new int[]{5,9,3,2,1,0,2,3,3,1,0,0}));
        System.out.println(canJump(new int[]{5,9,3,2,1,0,2,3,3,1,0,0}));
        System.out.println("-----");
        System.out.println(canJump4(new int[]{2,0,0,0,1,1,1}));
        System.out.println(canJump(new int[]{2,0,0,0,1,1,1}));



    }


    public boolean canJump4(int[] nums) {
        int len = nums.length,index=nums[0],cur;
        for(int i=0;i<len;i++){
            cur = nums[i] + i;
            index = Math.max(index,cur);
            if(i>=index && i<len-1)return false;
        }

        return true;
    }

    public boolean canJump3(int[] nums) {
        int len = nums.length,index=nums[0];
        int[] step = new int[len];
        for(int i=0;i<len;i++)step[i] = nums[i] + i;

        for(int i=0;i<len;i++){
            index = Math.max(index,step[i]);
            if(i>=index && i<len-1)return false;
        }

        return true;
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

    public boolean canJump2(int[] nums) {
        int len = nums.length,cur_index = 0,cur=nums[cur_index];
        while(cur_index + cur < len-1){
            int temp_space = 0;
            int next_index = cur_index;
            for(int i = 1;i<=cur;++i){//等于很关键
                if(nums[cur_index+i] +i>= temp_space ){//等于很关键，让index尽量走快
                    next_index = cur_index+i;
                    temp_space = nums[next_index] +i;
                }
            }
            if(temp_space==0)break;
            cur_index = next_index;
            cur=nums[cur_index];
        }
        return cur_index+ cur>= len -1;


    }

}
