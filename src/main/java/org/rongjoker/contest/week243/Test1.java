package org.rongjoker.contest.week243;

import org.junit.Test;

public class Test1 {

    @Test
    public void test1(){

        System.out.println(isSumEqual("aaa","a","aaaa"));
        System.out.println(isSumEqual("acb","cba","cdb"));

    }


    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {

        return convert(firstWord) + convert(secondWord) == convert(targetWord);

    }

    public int convert(String word){
        int len = word.length();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = word.toCharArray();
        for(char c:chars){
            stringBuilder.append(c - 'a');
        }

        return Integer.parseInt(stringBuilder.toString());


    }


}
