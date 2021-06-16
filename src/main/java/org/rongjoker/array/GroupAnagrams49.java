package org.rongjoker.array;

import org.junit.Test;

import java.util.*;

/**
 *
 * @date 06/16/2021
 * 49. 字母异位词分组 https://leetcode-cn.com/problems/group-anagrams/
 */
public class GroupAnagrams49 {


    @Test
    public void test49(){
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hash = new HashMap<>();
        for(String s:strs){
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            String key = new String(cs);
            if(!hash.containsKey(key)){
                List<String> ls = new ArrayList<>();
                ls.add(s);
                hash.put(key,ls);
            }else hash.get(key).add(s);

        }

        List<List<String>> ans = new ArrayList<>();
        hash.forEach((k,v)-> ans.add(v)
        );

        return ans;



    }
}
