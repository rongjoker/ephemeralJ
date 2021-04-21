package org.rongjoker.bit;

import org.junit.Test;

/**
 * @date 04/20/2021
 *
 * 两个数字之间，只要有1个是0，就是0
 * 101 与 111 之间有101 | 110 | 111 = 100 后两位都变成0了，也就是只要有1位不同，则都不同
 *
 * 201. 数字范围按位与 https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 *
 */
public class BitwiseAndOfNumbersRange201 {

    @Test
    public void test201(){
        System.out.println(rangeBitwiseAnd(1,2147483647));
    }

    public int rangeBitwiseAnd(int left, int right) {
        String sl = Integer.toBinaryString(left);
        String sr = Integer.toBinaryString(right);

        if (sl.length()!=sr.length())return  0 ;

        int ret = 0;

        int len = sl.length();
        int index = 0;
        while (len>0){
            len--;
            if(sl.charAt(index) ==sr.charAt(index)){
                if(sl.charAt(index)=='1')
                    ret |= (1 <<  len);

                index++;

            }else break;
        }

        return ret;

    }


}
