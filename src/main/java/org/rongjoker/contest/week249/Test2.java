package org.rongjoker.contest.week249;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Test2 {

    @Test
    public void test2(){
        System.out.println(countPalindromicSubsequence("aabca"));
        System.out.println(countPalindromicSubsequence("adc"));
        System.out.println(countPalindromicSubsequence("bbcbaba"));

    }

    public int countPalindromicSubsequence(String s) {
        char[] cs = s.toCharArray();
        int len = cs.length;
        int ans = 0;
        int[][] dict= new int[26][2];
        for (int i = 0; i < 26; i++) {
            dict[i][0] = -1;
            dict[i][1] = -1;
        }
        for (int i = 0; i < len; i++) {
            int index = cs[i] - 'a';
            dict[index][1] = i;
            if(dict[index][0]==-1)dict[index][0] = i;
        }
        for (int[] point : dict) {
            if(point[1]>point[0]){
                Set<Character> count = new HashSet<>();
                for(int i=point[0]+1;i<point[1];i++){
                    count.add(cs[i]);
                }
                ans+=count.size();
            }

        }

        return ans;
    }




}
