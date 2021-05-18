package org.rongjoker.bit;


import org.junit.Test;

/**
 *
 *
 * 260. 只出现一次的数字 III https://leetcode-cn.com/problems/single-number-iii/
 *
 * 位运算的妙用
 * 与运算的妙用
 * 其实就是把数组分成2部分，分别异或运算。最终剩余2个数字就是结果，难点是找出这2个数字的区分点
 * 2个数字不同，所以总有一位异或值是1，然后利用与运算 ==0 来分割数组为2部分即可
 *
 *
 */
public class SingleNumber260 {


    @Test
    public void test260(){

        System.out.println(Integer.toBinaryString(-12));
        System.out.println(Integer.toBinaryString(12));

    }


    public int[] singleNumber(int[] nums) {
        int[] ans = new int[2];

        int k = 0;
        for (int num:nums)
            k ^= num;
        int div = 1;
        while ((div & k) == 0)
            div <<= 1;
        for(int num:nums){
            if ((num & div) == 0)
                ans[0] ^= num;
            else
                ans[1] ^= num;
        }

        return ans;

    }



}
