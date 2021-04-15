package org.rongjoker.contest.week233;

public class Question1 {

    public int maxAscendingSum(int[] nums) {

        int len = nums.length;
        if(len==1)return nums[0];
        int index=1,max=nums[0],temp=nums[0];
        while(index<len){
            if(nums[index]<=nums[index-1]){
                max = Math.max(max,temp);
                temp=0;
            }
            temp+=nums[index];
            ++index;
        }

        max = Math.max(max,temp);

        return max;

    }
}
