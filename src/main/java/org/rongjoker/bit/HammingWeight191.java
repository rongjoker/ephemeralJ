package org.rongjoker.bit;

import org.junit.Test;

/**
 * @date 03/22/2021
 * 191. 位1的个数 https://leetcode-cn.com/problems/number-of-1-bits/
 *
 *
 */
public class HammingWeight191 {


    @Test
    public void test191(){
        System.out.println(hammingWeight(00000000000000000000000000001011));
    }


    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {

        return n == 0 ? 0 : hammingWeight((n & (~n + 1)) ^ n) + 1;

    }
}
