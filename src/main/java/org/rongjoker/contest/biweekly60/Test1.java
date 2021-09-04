package org.rongjoker.contest.biweekly60;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {

        System.out.println(findMiddleIndex(new int[]{2,3,-1,8,4}));
        System.out.println(findMiddleIndex(new int[]{1,-1,4}));
        System.out.println(findMiddleIndex(new int[]{2,5}));
        System.out.println(findMiddleIndex(new int[]{1}));

    }

    public int findMiddleIndex(int[] nums) {
        int len = nums.length;
        if(len==0)return 0;

        int[] prefix = new int[len+1];
        for (int i = 0; i < len; i++) prefix[i+1] = prefix[i]+nums[i];
        for (int i = 0; i < len; i++) {
            int left = prefix[i];
            int right = prefix[len] - prefix[i+1];
            if(left==right)return i;
        }
        return -1;

    }


}
