package org.rongjoker.contest.biweekly58;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {

        System.out.println(makeFancyString("leeetcode"));
        System.out.println(makeFancyString("aaabaaaa"));
        System.out.println(makeFancyString("aab"));


    }

    public String makeFancyString(String s) {
        int len = s.length();
        if(len<3)return s;
        char[] chars = s.toCharArray();
        StringBuilder sr = new StringBuilder();
        int count = 1;
        sr.append(chars[0]);
        for(int i = 1;i<len;++i){
            if(chars[i]==chars[i-1])count++;
            else count =1;
            if(count<3)sr.append(chars[i]);
        }

        return sr.toString();

    }

}
