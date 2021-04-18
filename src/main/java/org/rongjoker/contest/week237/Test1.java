package org.rongjoker.contest.week237;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Test1 {

    @Test
    public void test1(){

//        System.out.println(minOperations(new int[]{1,1,1}));

//        System.out.println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        System.out.println(checkIfPangram("leetcode"));
    }

    public boolean checkIfPangram(String sentence) {
        int len = sentence .length();
        if(len<26)return false;
        Set<Character> set = new HashSet<>();
        String sentence2 = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0;i<26;++i){
            set.add(sentence2.charAt(i));
        }
        for(int i=0;i<len;++i){
            set.remove(sentence.charAt(i));
            if(set.size()==0)return true;
        }
        return false;


    }
}
