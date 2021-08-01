package org.rongjoker.array;

import org.junit.Test;

/**
 *
 *
 *
 *
 */
public class TitleToNumber171 {

    @Test
    public void test171(){
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("ZY"));
        System.out.println(titleToNumber("FXSHRXW"));




    }


    public int titleToNumber(String columnTitle) {
        char[] cs = columnTitle.toCharArray();
        int ans = 0;
        int len = cs.length;

        for (int i = 0; i < len; i++) {
            int cur = cs[i] - 'A';
            cur++;
            int pow = (int)Math.pow(26, len - i - 1);
            cur *=pow;
            ans+= cur;
        }

        return ans;

    }
}
