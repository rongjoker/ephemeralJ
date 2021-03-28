package org.rongjoker.longest;

import org.junit.Test;

/**
 * @date 01/09/2021
 * @date 01/10/2021
 * 5. 最长回文子串 https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class LongestPalindromicSubstring5 {

    @Test
    public void test5(){

        String text1 = "babad";

        System.out.println(longestPalindrome(text1));

    }


    /**
     * 中心发散的方法
     * * @date 01/10/2021
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        int n = s.length();

        int start=0,max =0,temp =0;

        for (int l = 0; l < n; l++) {

            temp =  Math.max(getLength(s, n, l, l), getLength(s, n, l, l + 1));//奇偶性2种情况
            if(temp>max){
                max=temp;
            start = l - (max-1)/2;//确定start是比较难的
            }

        }

        return s.substring(start,start+max);

    }

    public int getLength(String s,int n,int l,int r){

        while (l>=0 &&r<n){
            if(s.charAt(l) == s.charAt(r)){
                l--;r++;
            }else break;
        }

        return (r - l)-1;//会向外各自多扩张一个长度,需要减去1 (实际上是+1-2)

    }







}
