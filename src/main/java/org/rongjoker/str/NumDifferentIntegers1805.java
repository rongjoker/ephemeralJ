package org.rongjoker.str;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class NumDifferentIntegers1805 {


    @Test
    public void test1805() {
//        System.out.println(numDifferentIntegers("a123bc34d8ef34"));
//        System.out.println(numDifferentIntegers("leet1234code234"));
//        System.out.println(numDifferentIntegers("a1b01c001"));
//        System.out.println(numDifferentIntegers("167278959591294"));
//        System.out.println(numDifferentIntegers("035985750011523523129774573439111590559325"));
//        System.out.println(numDifferentIntegers("a123bc34d8ef34"));
        System.out.println(numDifferentIntegers("9"));
//        System.out.println(numDifferentIntegers("0a0"));
    }


    public int numDifferentIntegers(String word) {
        int len = word.length();
        Set<String> set = new HashSet<>();
        char ch;
        int left = -1;
        for (int i = 0; i < len; i++) {
            ch = word.charAt(i);
            if (ch <= '9') {//数字
                if (i == 0 || word.charAt(i - 1) > '9')
                    left = i;
            } else {//字母
                if (left == -1 || word.charAt(i - 1) > '9') continue;
                while (left < i - 1 && word.charAt(left) == '0') ++left;
                set.add(word.substring(left, i));
            }
        }
        if (word.charAt(len - 1) <= '9') {
            while (left < len - 1 && word.charAt(left) == '0') ++left;
            set.add(word.substring(left, len));
        }

        return set.size();

    }


}
