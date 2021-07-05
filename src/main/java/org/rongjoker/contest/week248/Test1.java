package org.rongjoker.contest.week248;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * https://www.cnblogs.com/lolybj/p/9588059.html
 * 矩阵转换
 *
 *
 */
public class Test1 {



    @Test
    public void test1(){

        System.out.println(Arrays.toString(buildArray(new int[]{0, 2, 1, 5, 3, 4})));



    }


    public int[] buildArray(int[] nums) {

        int len = nums.length;
        int[] ans = new int[len];

        for(int i=0;i<len;i++){
            ans[i] = nums[nums[i]];
        }

        return ans;

    }



}
