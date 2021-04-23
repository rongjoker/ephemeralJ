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
        System.out.println(hammingWeight2(00000000000000000000000010000000));
    }


    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {

        System.out.println(3 & 1);

//        return n == 0 ? 0 : hammingWeight((n & (~n + 1)) ^ n) + 1;

        int total = 0;
        for (int i = 0; i < 32; i++) {
            if ( (n & (1<<i)) !=0){
                ++total;
            }
        }
        return total;
    }

    public int hammingWeight2(int n) {
        int len = Integer.toBinaryString(n).length();
        int sum = 0;
        for(int i=0;i<len;++i){
            if ((n & (1<< i)) !=0) {
                ++sum;
            }
        }
        return sum;
    }
}
