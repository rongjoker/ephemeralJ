package org.rongjoker.sw;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @date 01/15/2021
 *
 * 3. 无重复字符的最长子串 https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 用hashmap维护滑动窗口
 *
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

    @Test
    public void test3(){

        String s = "aab";

        System.out.println(lengthOfLongestSubstring(s));


    }


    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n<=1)return n;

        int longest = 0,left=0,right=1;Integer index;
        Map<Character,Integer> table = new HashMap<>();
        table.put(s.charAt(left),left);


        while (left<n&&right<n){

            char c = s.charAt(right);
            if((index=table.get(c))!=null){//有重复
                longest = Math.max(longest,right-left);
                for (int i = left; i <= index; i++) {
                    table.remove(s.charAt(i));//删除前缀
                }
                left = index+1;
            }
            table.put(c,right);
            right++;
        }

        return Math.max(longest,right-left);

    }



}
