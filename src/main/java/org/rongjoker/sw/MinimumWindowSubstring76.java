package org.rongjoker.sw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        while(!needs.contains(s.charAt(left)))left++;
        if (left> lens - len) return "";
        needs.remove(s.charAt(left));
        requires.put(s.charAt(left),1);

        int right=left+1;
        while(right<lens && !needs.isEmpty()){
            if(requires.containsKey(s.charAt(right))){
                requires.put(s.charAt(right),requires.getOrDefault(s.charAt(right),0)+1);
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
