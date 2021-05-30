package org.rongjoker.contest.biweekly53;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {
//        System.out.println(countGoodSubstrings("aababcabc"));
//        System.out.println(countGoodSubstrings("xyzzaz"));
        System.out.println(countGoodSubstrings("yvywmekqcqawuql"));
    }


    public int countGoodSubstrings(String s) {
        int len = s.length();
        if (len < 3) return 0;
        int ans = 0;


        int left = 0, right = 0;
        while (right < len) {
            while (right - left < 2) {


                if (right < len - 1) {

                    if (s.charAt(right) == s.charAt(right + 1)) {
                        right++;
                        left = right;
                    } else if (right > 0 && s.charAt(right + 1) == s.charAt(right - 1)) {
                        right++;
                        left = right - 1;
                    } else right++;

                } else right++;

            }

            if (right < len && right - left == 2) {
                System.out.println(s.substring(left,left+3));
                ans++;
                left++;

            }

        }
        return ans;


    }
}
