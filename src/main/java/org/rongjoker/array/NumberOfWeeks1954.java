package org.rongjoker.array;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * @date 08/01/2021
 * 你可以工作的最大周数  https://leetcode-cn.com/problems/maximum-number-of-weeks-for-which-you-can-work/
 *
 */
public class NumberOfWeeks1954 {


    @Test
    public void test1954(){
        System.out.println(numberOfWeeks(new int[]{5,7,5,7,9,7}));

    }

    public long numberOfWeeks(int[] milestones) {
        int n = milestones.length;
        if(n==1)return 1;
        Arrays.sort(milestones);
        long ans = 0;

        int index = n-1,available = 0;
        while(index>=0){
            if(available>0){
                ans += (Math.min(available,milestones[index])*2);
                available = Math.abs(available - milestones[index]);
            }else{
                available =  milestones[index];
            }
            index--;
        }
        if(available>0) ans++;

        return ans;

    }
}
