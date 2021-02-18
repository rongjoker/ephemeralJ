package org.rongjoker.maths;

import org.junit.Test;

/**
 * @date 02/08/2021
 * 263. 丑数  https://leetcode-cn.com/problems/ugly-number/
 * 编写一个程序判断给定的数是否为丑数。
 * <p>
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * <p>
 * 数学问题，可以用巧妙的方式解决，先计算大的，再计算小的
 */
public class UglyNumber263 {

    @Test
    public void test684() {
        System.out.println(isUgly(6));
    }


    //2,3,5
    public boolean isUgly(int num) {
        if (num <= 0)
            return false;

        while (num % 30 == 0)
            num /= 30;
        while (num % 15 == 0)
            num /= 15;
        while (num % 10 == 0)
            num /= 10;
        while (num % 6 == 0)
            num /= 6;
        while (num % 5 == 0)
            num /= 5;
        while (num % 3 == 0)
            num /= 3;
        while (num % 2 == 0)
            num >>= 1;//右移替代除法

        return num == 1;


    }


}
