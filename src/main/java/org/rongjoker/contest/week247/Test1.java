package org.rongjoker.contest.week247;

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



    }

    public int maxProductDifference(int[] nums) {

        Arrays.sort(nums);
        int len = nums.length-1;

        return (nums[len] * nums[len-1]) - (nums[0]*nums[1]);

    }




}
