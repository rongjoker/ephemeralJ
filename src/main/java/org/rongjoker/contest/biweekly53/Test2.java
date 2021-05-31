package org.rongjoker.contest.biweekly53;

import org.junit.Test;

import java.util.Arrays;

public class Test2 {


    @Test
    public void test2(){

        System.out.println(minPairSum(new int[]{3,5,4,2,4,6}));
        System.out.println(minPairSum(new int[]{3,5,2,3}));

    }

    public int minPairSum(int[] nums) {

        Arrays.sort(nums);
        int len = nums.length;
        int l = 0,r = len-1;
        int ans = Integer.MIN_VALUE;
        while(l<r){
            ans = Math.max(ans,nums[l] + nums[r]);
            ++l;
            --r;
        }

        return ans;



    }
}
