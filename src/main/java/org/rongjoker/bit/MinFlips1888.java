package org.rongjoker.bit;


import org.junit.Test;

/**
 * @date 06/12/2021
 * 1888. 使二进制字符串字符交替的最少反转次数 https://leetcode-cn.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/
 * 为奇数的时候利用滑动窗口继续向前处理
 */
public class MinFlips1888 {


    @Test
    public void test1888(){
        System.out.println(minFlips("111000"));
        System.out.println(minFlips("010"));
        System.out.println(minFlips("1110"));
    }



    public int minFlips(String s) {

        char[] chars = s.toCharArray();
        int len = chars.length;

        int[][] dp = new int[len * 2][2];

        char start0 = '0', start1 = '1';

        int ans0 = 0, ans1 = 0;

        for (int i = 0; i < len; i++) {
            if (chars[i] != start0) {
                dp[i][0] = 1;
                ans0++;
            }


            if (chars[i] != start1) {
                dp[i][1] = 1;
                ans1++;
            }

            start0 = start0 == '1' ? '0' : '1';
            start1 = start1 == '1' ? '0' : '1';
        }

        int ans = Math.min(ans0, ans1);

        if(ans==0)return 0;

        if (len % 2 != 0) {
            for (int i = len; i < len * 2; i++) {
                if (chars[i % len] != start0) {
                    dp[i][0] = 1;
                    ans0++;

                }


                if (chars[i % len] != start1) {
                    dp[i][1] = 1;
                    ans1++;
                }

                start0 = start0 == '1' ? '0' : '1';
                start1 = start1 == '1' ? '0' : '1';

                ans0 -= dp[i % len][0];
                ans1 -= dp[i % len][1];
                ans = Math.min(Math.min(ans0, ans1), ans);

            }

        }
        return ans;

    }


}
