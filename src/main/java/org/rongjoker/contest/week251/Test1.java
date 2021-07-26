package org.rongjoker.contest.week251;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {
        System.out.println(getLucky("iiii",1));
        System.out.println(getLucky("leetcode",2));
    }


    public int getLucky(String s, int k) {
        StringBuilder sr = new StringBuilder();
        char[] chars = s.toCharArray();
        for(char c:chars){
            sr.append((c-'a')+1);
        }

        while (sr.length()>1 && k>0){
            int count = 0;
            for(int i=0;i<sr.length();++i){
                count += (sr.charAt(i) - '0');
            }
            sr = new StringBuilder().append(count);
            k--;
        }

        return Integer.parseInt(sr.toString());

    }


}
