package org.rongjoker.longest;

import org.junit.Test;

/**
 *
 * @date 01/09/2021
 * 1143. 最长公共子序列  https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 很像01背包
 * 比最长回文子序列类似，属于区间dp
 *
 *
 */
public class LongestCommonSubSequence1143 {

    @Test
    public void test1143(){

        String text1 = "bl", text2 = "yby";

        System.out.println(longestCommonSubsequence(text1,text2));

    }

    public int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i < m; i++) {

            char c1 = text1.charAt(i);

            for (int j = 0; j < n; j++) {
                char c2 = text2.charAt(j);

                if(c2 == c1){
                    dp[i+1][j+1] = dp[i][j] +1;
                }else dp[i+1][j+1] = Math.max(dp[i+1][j],dp[i][j+1]);

            }

        }


        return dp[m][n];

    }



    public int longestCommonSubsequenceOptimize(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i < m; i++) {

            char c1 = text1.charAt(i);

            for (int j = 0; j < n; j++) {
                char c2 = text2.charAt(j);

                if(c2 == c1){
                    dp[j+1][i+1] = dp[j][i] +1;
                }else dp[j+1][i+1] = dp[j+1][i];

            }

        }


        return dp[n][m];

    }



}
