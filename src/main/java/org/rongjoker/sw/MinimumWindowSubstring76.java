package org.rongjoker.sw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @date 04/24/2021
 * @date 04/25/2021
 * 76. 最小覆盖子串 https://leetcode-cn.com/problems/minimum-window-substring/
 * 比较困难的一个隐形滑动窗口题目
 *
 *
 */
public class MinimumWindowSubstring76 {

    public String minWindow(String s, String t) {

        Set<Character> needs = new HashSet<>();
        Map<Character,Integer> requires = new HashMap<>();
        int len = t.length(),lens = s.length();
        for(int i=0;i<len;++i){
            needs.add(t.charAt(i));
            requires.put(t.charAt(i),0);
        }
        int left=0;

        for (int right = 0; right < lens; right++) {

        }


        while(!needs.contains(s.charAt(left)))left++;
        if (left> lens - len) return "";
        needs.remove(s.charAt(left));
        requires.put(s.charAt(left),1);

        int right=left+1;
        while(right<lens && !needs.isEmpty()){
            if(requires.containsKey(s.charAt(right))){
                requires.put(s.charAt(right),requires.get(s.charAt(right))+1);
                needs.remove(s.charAt(right));
            }
            ++right;
        }

        if(!needs.isEmpty()) return "";
        int current_left = left;
        int current_right = right;
        int current_space = right - left;
        while(right<lens){
//            while
            left++;
            requires.put(s.charAt(left),requires.get(s.charAt(left))-1);
//            if(requires.get(s.charAt(left)))

        }



return  null;


    }
}
