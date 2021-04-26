package org.rongjoker.sw;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @date 04/24/2021
 * @date 04/25/2021
 * @date 04/26/2021
 * 76. 最小覆盖子串 https://leetcode-cn.com/problems/minimum-window-substring/
 * 比较困难的一个隐形滑动窗口题目
 * 需要苛刻的方式记录维持滑动窗口的条件
 *
 *
 */
public class MinimumWindowSubstring76 {

    @Test
    public void test76(){

//        System.out.println("zx".substring(0,2));

//        System.out.println(minWindow("ADOBECODEBANC","ABC"));
        System.out.println(minWindow("a","aa"));
    }

    //t 是短的，s包含t
    //返回 s 中涵盖 t 所有字符的最小子串
    public String minWindow(String s, String t) {

        Set<Character> unique = new HashSet<>();
        Map<Character,Integer> needs = new HashMap<>();
        Map<Character,Integer> available = new HashMap<>();
        int lent = t.length(),lens = s.length();
        if(lent>lens)return "";
        for(int i=0;i<lent;++i){
            unique.add(t.charAt(i));
            needs.put(t.charAt(i),needs.getOrDefault(t.charAt(i),0)+1);
            available.put(t.charAt(i),0);
        }
        int left=0;

        int current_left = -1;
        int current_space = Integer.MAX_VALUE;

        for (int right = 0; right < lens; right++) {

            if(available.containsKey(s.charAt(right))){
                available.put(s.charAt(right),available.get(s.charAt(right))+1);
                if(available.get(s.charAt(right))>=needs.get(s.charAt(right))){
                    unique.remove(s.charAt(right));
                }
            }

            while (unique.size()==0){
                if(right - left < current_space){
                    current_left = left;
                    current_space = right - left;
                }
                if(needs.containsKey(s.charAt(left))){
                    available.put(s.charAt(left),available.get(s.charAt(left))-1);
                    if (available.get(s.charAt(left)) < needs.get(s.charAt(left))){
                        unique.add(s.charAt(left));
                    }
                }

                ++left;

            }

        }

        if(current_left==-1)return "";
        else return s.substring(current_left,current_left+current_space+1);


    }
}
