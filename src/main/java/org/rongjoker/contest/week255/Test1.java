package org.rongjoker.contest.week255;

import java.util.Arrays;
import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {
        System.out.println(findGCD(new int[]{2,5,6,9,10}));
        System.out.println(findGCD(new int[]{7,5,6,8,3}));
        System.out.println(findGCD(new int[]{3,3}));

    }

    public int findGCD(int[] nums) {

        Arrays.sort(nums);
        int min = nums[0],max = nums[nums.length-1];
        int ans = 1;
        for(int i = 2;i<=min;++i){
            if(min%i==0 && max%i==0)ans = i;
        }
        return ans;
    }



}
