package org.rongjoker.contest.week250;

import org.junit.Test;

/**

 *
 *
 */
public class Test1 {



    @Test
    public void test1(){
        System.out.println(canBeTypedWords("hello world","ad"));
        System.out.println(canBeTypedWords("leet code","lt"));
        System.out.println(canBeTypedWords("leet code","e"));

    }

    public int canBeTypedWords(String text, String brokenLetters) {

        String[] ss = text.split(" ");
        char[] chars = brokenLetters.toCharArray();
        int[] dict = new int[26];
        for(char c:chars){
            dict[c-'a']++;
        }
        int ans = 0;

        flag:for(String s:ss){
            char[] temps = s.toCharArray();
            for(char c:temps){
                if(dict[c-'a']>0)continue flag;
            }
            ans++;
        }
        return ans;
    }



}
