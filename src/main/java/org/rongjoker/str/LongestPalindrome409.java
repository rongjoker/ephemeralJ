package org.rongjoker.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 03/27/2021
 * 409. 最长回文串  https://leetcode-cn.com/problems/longest-palindrome/
 * 利用hash记录次数
 *
 */
public class LongestPalindrome409 {

    public int longestPalindrome(String s) {
        int len = s.length();
        if(len<2)return len;

        Map<Character,Integer> map = new HashMap<>();

        int a,total=0;
        char c;
        for(int i=0;i<len;++i){
            c = s.charAt(i);
            a = map.getOrDefault(c,0)+1;
            if(a%2==0)total+=2;
            map.put(c,a);

        }

        return total<len?total+1:total;

    }
}
