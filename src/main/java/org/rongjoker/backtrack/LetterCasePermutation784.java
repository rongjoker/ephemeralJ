package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列 https://leetcode-cn.com/problems/letter-case-permutation/
 *
 *
 *
 */
public class LetterCasePermutation784 {


    @Test
    public void test784(){
//        System.out.println(letterCasePermutation("a1b2"));
//        System.out.println(letterCasePermutation("3z4"));
//        System.out.println(letterCasePermutation("12345"));
//        System.out.println(letterCasePermutation("C"));
        System.out.println(letterCasePermutation("mQe"));
        //["mqe","mqE","mQe","mQE","Mqe","MqE","MQe","MQE"]

    }


    public List<String> letterCasePermutation(String s) {

        ans = new ArrayList<>();
        backtrack(s.toLowerCase().toCharArray(),s.length(),0);
        return ans;
    }

    List<String> ans;

    public void backtrack(char[] cs,int len,int index){

        ans.add(new String(cs));

        for(int i=index;i<len;i++){
            if(cs[i]< 'a')continue;
            cs[i] -= 32;
            backtrack(cs,len,i+1);
            cs[i] += 32;
        }

    }


}
