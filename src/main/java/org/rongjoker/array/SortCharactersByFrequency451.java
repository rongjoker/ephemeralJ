package org.rongjoker.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 05/13/2021
 * 451. 根据字符出现频率排序 https://leetcode-cn.com/problems/sort-characters-by-frequency/
 * 桶排序
 *
 */
public class SortCharactersByFrequency451 {


    @Test
    public void test451(){
        System.out.println(frequencySort("Aabb"));
    }

    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int len = s.length();
        char temp;
        for(int i=0;i<len;++i){
            temp = s.charAt(i);
            map.put(temp,map.getOrDefault(temp,0)+1);
        }
        List<Character>[] bucket = new List[len+1];
        map.forEach((k,v)->{
            if(bucket[v]==null)
                bucket[v] = new ArrayList<>();
            bucket[v].add(k);
        });
        StringBuilder sbr = new StringBuilder();
        for(int i = len;i>0;--i){
            if(null!=bucket[i]){
                for(Character t: bucket[i]){
                    sbr.append(String.valueOf(t).repeat(i));
                }
            }

        }
        return sbr.toString();
    }
}
