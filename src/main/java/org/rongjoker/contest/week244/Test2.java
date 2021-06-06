package org.rongjoker.contest.week244;

import org.junit.Test;

import java.util.Arrays;

public class Test2 {

    @Test
    public void test2(){

        System.out.println(reductionOperations(new int[]{5,1,3}));
        System.out.println(reductionOperations(new int[]{1,1,1}));
        System.out.println(reductionOperations(new int[]{1,1,2,2,3}));

    }


    public int reductionOperations(int[] nums) {

        Arrays.sort(nums);

        int len = nums.length;
        int ans = 0;
        int count=0;
        for (int i = len-1; i >0; --i) {
            count++;
            if(nums[i]!=nums[i-1])ans += count;
        }
        return ans;

    }
}
