package org.rongjoker.ds;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @date 03/24/2021
 * <p>
 * 131. 分割回文串 https://leetcode-cn.com/problems/palindrome-partitioning/
 * dfs题目 返回所有的可能,实质上是dfs分支的回溯算法
 */
public class PalindromePartitioning131 {

    @Test
    public void test131() {

        System.out.println(partition("aa"));

    }

    List<List<String>> list = new ArrayList<>();
    Deque<String> deque = new ArrayDeque<>();


    public List<List<String>> partition(String s) {
        part(s,0,s.length());

        return list;

    }


    public void part(String s,int start,int len){

        for (int i = start; i < len; i++) {
            if(isPalindrome(s,start,i)){
                deque.addLast(s.substring(start,i+1));
                part(s,i+1,len);

                if(i==len-1){
                    list.add(new ArrayList<>(deque));
                }

                deque.removeLast();

            }
        }

    }


    public boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            ++start;
            --end;
        }

        return true;
    }
}
