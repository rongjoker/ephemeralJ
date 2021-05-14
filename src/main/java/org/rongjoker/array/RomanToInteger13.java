package org.rongjoker.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 05/14/2021
 * 13. 罗马数字转整数 https://leetcode-cn.com/problems/roman-to-integer/
 */
public class RomanToInteger13 {

    @Test
    public void test13(){
        System.out.println(romanToInt("LVIII"));
    }


    public int romanToInt(String s) {

        Map<Character, Integer> dict = new HashMap<>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        int len = s.length();
        int ans = 0;

        for (int i = 0; i < len; i++) {
            if(i <len-1 && dict.get(s.charAt(i)) < dict.get(s.charAt(i+1))){
                ans -= dict.get(s.charAt(i));
            }else ans += dict.get(s.charAt(i));

        }

        return ans;


    }
}
