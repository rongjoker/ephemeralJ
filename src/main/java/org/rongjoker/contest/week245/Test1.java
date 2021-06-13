package org.rongjoker.contest.week245;

import org.junit.Test;

/**
 *
 * https://www.cnblogs.com/lolybj/p/9588059.html
 * 矩阵转换
 *
 *
 */
public class Test1 {

    @Test
    public void test1(){



    }

    public boolean makeEqual(String[] words) {

        int[] dict = new int[26];
        int len = words.length;
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (char c : chars) {
                dict[c-'a']++;
            }
        }

        for (int d : dict) {
            if(d%len!=0)return false;
        }

        return true;


    }


}
