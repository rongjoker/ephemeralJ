package org.rongjoker.contest.week247;

import org.junit.Test;

import java.util.*;

public class Test3 {

    @Test
    public void test3() {

        System.out.println(commonChars(new String[]{"bella","label","roller"}));

    }

    public List<String> commonChars(String[] words) {
        Map<Character,Integer> dict = count(words[0]);
        int len = words.length;
        for(int i=1;i<len;++i){
            Map<Character,Integer> temp = count(words[i]);
            Set<Character> ks= dict.keySet();
//            for(char c:ks){
//                if(!temp.containsKey(c))dict.remove(c);
//                else dict.put(c,Math.min(dict.get(c),temp.get(c)));
//            }

            for (Iterator<Map.Entry<Character, Integer>> it = dict.entrySet().iterator(); it.hasNext();){
                Map.Entry<Character, Integer> next = it.next();
//                if(!temp.containsKey(next.getKey()))dict.remove(next.getKey());
                if(!temp.containsKey(next.getKey()))it.remove();
                else dict.put(next.getKey(),Math.min(next.getValue(),temp.get(next.getKey())));
            }

        }

        List<String> ans = new ArrayList<>();

        Set<Character> ks= dict.keySet();
        for(char c:ks){
            int t = dict.get(c);
            for(int i = 0;i <t;++i )ans.add(String.valueOf(c));
        }

        return ans;


    }

    public Map<Character,Integer> count(String w){
        Map<Character,Integer> hash = new HashMap<>();
        char[] cs = w.toCharArray();
        for(char c:cs){
            hash.put(c,hash.getOrDefault(c,0)+1);
        }
        return hash;

    }



}
