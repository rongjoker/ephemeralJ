package org.rongjoker.bit;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 05/06/2021
 * 1720. 解码异或后的数组 https://leetcode-cn.com/problems/decode-xored-array/
 * 异或运算满足交换律
 *
 */
public class DecodeXoredArray1720 {

    @Test
    public void test1720(){

        System.out.println(Arrays.toString(decode(new int[]{6, 2, 7, 3}, 4)));
        System.out.println(Arrays.toString(decode(new int[]{1,2,3}, 1)));


    }

    public int[] decode(int[] encoded, int first) {
        int len = encoded.length;
        int[] ans = new int[len+1];
        ans[0] =first;
        for (int i = 0; i < len; i++) {
            first ^= encoded[i];
            ans[i+1] =first;
        }

        return ans;

    }


}
