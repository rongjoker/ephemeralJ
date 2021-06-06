package org.rongjoker.bit;

import org.junit.Test;

/**
 * 思路正确，不过具体做法可以转换
 */
public class MinOperations1758 {

    @Test
    public void test1758(){

        System.out.println(minOperations("0100"));
        System.out.println(minOperations("10"));
        System.out.println(minOperations("1111"));
        System.out.println(minOperations("10010100"));
        System.out.println(minOperations("1"));

    }


    public int minOperations(String s) {
        char[] chars = s.toCharArray();

        return Math.min(change('0',chars),change('1',chars));

    }


    public int change(char start,char[] res){
        int ans = 0;
        for(char c:res){
            if(c!=start)ans++;
            start = start=='1'?'0':'1';
        }

        return ans;
    }


}
