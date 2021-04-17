package org.rongjoker.contest.biweekly50;

import org.junit.Test;

public class Test1 {

    @Test
    public void test1(){

        System.out.println(minOperations(new int[]{1,1,1}));
    }

    public int minOperations(int[] nums) {
        int len = nums.length;
        if(len==1) return 0;
        int sum = 0;
        int index = 1;
        int previous =nums[0],current;
        while(index<len){
            current = nums[index];
            if(current<=previous){
                sum += (previous - current +1);
                previous = previous+1;
            }else
                previous = current;

            ++index;


        }

        return sum;

    }


}
