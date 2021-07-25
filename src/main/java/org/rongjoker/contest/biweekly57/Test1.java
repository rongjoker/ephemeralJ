package org.rongjoker.contest.biweekly57;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {

        System.out.println(areOccurrencesEqual("abacbc"));
        System.out.println(areOccurrencesEqual("aaabb"));


    }


    public boolean areOccurrencesEqual(String s) {

        int[] dict = new int[26];
        char[] chars = s.toCharArray();
        for(char c:chars){
            dict[c-'a']++;
        }

        int count = 0;
        for(int d:dict){
            if(d!=0){
                if(count==0)count=d;
                else if(d!=count)return false;
            }
        }

        return true;


    }


}
