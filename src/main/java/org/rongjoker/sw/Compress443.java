package org.rongjoker.sw;

import org.junit.Test;

/**
 * @date 08/21/2021
 * 443. 压缩字符串 https://leetcode-cn.com/problems/string-compression/
 */
public class Compress443 {


    @Test
    public void test443(){
        System.out.println(compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
        System.out.println(compress(new char[]{'a'}));

    }

    public int compress(char[] chars) {
        int len = chars.length;
        if (len <= 1) {
            return len;
        }
        int ans = 0;
        char x = chars[0];
        int temp = 1;
        int start = 0;
        for (int i = 1; i < len; ++i) {
            if (chars[i] == x) {
                temp++;
            } else {
                int t = count(chars, x, start, temp);
                start += t;
                ans += t;
                x = chars[i];
                temp = 1;
            }
        }

        return ans + count(chars, x, start, temp);

    }

    int count(char[] cs, char x, int start, int temp) {
        cs[start++] = x;
        int c = 1;
        if (temp > 1) {
            String s = String.valueOf(temp);
            char[] tt = s.toCharArray();
            for (char t : tt) {
                cs[start++] = t;
                c++;
            }
        }
        return c;
    }

}
