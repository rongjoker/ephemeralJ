package org.rongjoker.contest.biweekly52;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortSentence {

    @Test
    public void test1(){

        System.out.println(sortSentence("is2 sentence4 This1 a3"));
        System.out.println(sortSentence("Myself2 Me1 I4 and3"));

    }


    public String sortSentence(String s) {

        String[] ss = s.split(" ");
        if(ss.length==1)return s.substring(0,s.length()-1);
        List<String> list = Arrays.stream(ss).sorted(Comparator.comparingInt(s2 -> s2.charAt(s2.length() - 1))).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (String s1 : list) {
            stringBuilder.append(s1, 0, s1.length() - 1).append(" ");
        }
        String ans = stringBuilder.toString();
        return ans.substring(0,ans.length()-1);


    }
}
