package org.rongjoker.binarysearch;


import org.junit.Test;

/**
 *  @date 04/07/2021
 *  69. x 的平方根 https://leetcode-cn.com/problems/sqrtx/
 *  巧妙的利用了二分查找的数学题目
 *
 */
public class Sqrt69 {

    @Test
    public void test69(){
        System.out.println(mySqrt(15));
    }

    public int mySqrt(int x) {

        if(x<2)return x;

        int left=0,right=x,middle;
        while (right>=left){
            middle = left +((right - left)>>1);
            if(middle * middle ==x) return middle;
            if(middle * middle >x)right=middle-1;
            if(middle * middle <x)left=middle+1;
        }

        return (right + left)>>1;
    }
}
