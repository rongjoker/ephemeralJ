package org.rongjoker.contest.week244;

import org.junit.Test;

public class Test3 {

    @Test
    public void test3(){
        System.out.println(minFlips("111000"));
        System.out.println(minFlips("1110"));
        System.out.println(minFlips("010"));
    }


    public int minFlips(String s) {
        char[] chars = s.toCharArray();
        int len_a = chars.length;
        StringBuilder[] target = new StringBuilder[2];
        target[0] = new StringBuilder();
        target[1] = new StringBuilder();

        StringBuilder stringBuilder = new StringBuilder(s);
        for (int i = 0; i < len_a; i++) {
            target[i%2].append('1');
            target[(i+1)%2].append('0');

        }


        if(s.equals(target[0].toString()) || s.equals(target[1].toString()))return 0;

        char[] change = new char[2];
        change[0] = '1';
        change[1] = '0';

        for (int i = 0; i < len_a-1; i++) {
            if(chars[i]==chars[i+1]){
                stringBuilder.deleteCharAt(i);
                stringBuilder.append(chars[i]);
            }else break;
        }

        int ans = 0;

        String toString = stringBuilder.toString();
        chars = toString.toCharArray();
        for (int i = 1; i < len_a; i++) {
            if(chars[i]==chars[i-1]){
                chars[i] = change[chars[i] - '0'];
                ++ans;

            }
        }


        return ans;




    }
}
