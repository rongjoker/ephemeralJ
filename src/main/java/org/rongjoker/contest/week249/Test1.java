package org.rongjoker.contest.week249;

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

        System.out.println(Arrays.toString(getConcatenation(new int[]{1, 2, 1})));
        System.out.println(Arrays.toString(getConcatenation(new int[]{1, 3, 2, 1})));




    }

    public int[] getConcatenation(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len*2];
        for (int i = 0; i < len; i++) {
            ans[i] = nums[i];
            ans[len+i] = nums[i];

        }

        return ans;

    }



}
