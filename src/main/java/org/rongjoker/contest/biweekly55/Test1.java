package org.rongjoker.contest.biweekly55;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {

//        System.out.println(canBeIncreasing(new int[]{1,2,10,5,7}));
//        System.out.println(canBeIncreasing(new int[]{2,3,1,2}));
        System.out.println(canBeIncreasing(new int[]{100,21,100}));



    }


    public boolean canBeIncreasing(int[] nums) {
        int len = nums.length;
        boolean del = false;
        int pre = nums[0];
        for(int i=1;i<len;++i){
            if(nums[i]<=pre){
                if(del)return false;
                del = true;
                if(i-2>=0){
                    if(nums[i]>nums[i-2])pre = nums[i];
                    else pre = nums[i-1];
                }else pre = nums[i];
            }else pre = nums[i];
        }

        return true;

    }

}
