package org.rongjoker.contest.biweekly59;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {
        System.out.println(minTimeToType("abc"));
        System.out.println(minTimeToType("bza"));
        System.out.println(minTimeToType("zjpc"));




    }

    public int minTimeToType(String word) {
        char[] chars = word.toCharArray();
        int len = chars.length,ans = len;
        char start = 'a';
        for(char c:chars){
            if(c>=start){
                ans +=Math.min(c-start,start-c + 26);
            }else ans +=Math.min(start-c,c-start + 26);

            start = c;
        }
        return ans;


    }

}
