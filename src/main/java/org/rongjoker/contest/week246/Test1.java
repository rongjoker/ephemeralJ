package org.rongjoker.contest.week246;

import org.junit.Test;

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
        System.out.println(largestOddNumber("1"));
        System.out.println(largestOddNumber("2"));
        System.out.println(largestOddNumber("52"));
        System.out.println(largestOddNumber("4206"));
        System.out.println(largestOddNumber("35427"));





    }

    public String largestOddNumber(String num) {

        int end = -1,index=0,len = num.length();
        while (index<len){
            if((num.charAt(index) -'0')%2==1)end = index;
            index++;
        }

        return num.substring(0,end+1);


    }


}
