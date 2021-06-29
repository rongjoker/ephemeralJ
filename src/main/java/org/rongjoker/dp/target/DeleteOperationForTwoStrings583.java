package org.rongjoker.dp.target;

import org.junit.Test;

/**
 *
 * @date 04/08/2021
 * 583. 两个字符串的删除操作 https://leetcode-cn.com/problems/delete-operation-for-two-strings/
 *
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 */
public class DeleteOperationForTwoStrings583 {

    @Test
    public void test583(){

        String word1 = "sea", word2 = "eat";
        System.out.println(minDistance(word1,word2));

    }

    public int minDistance(String word1, String word2) {

        int max1 = word1.length(),max2 = word2.length();
        if(max1==0) return max2;
        if(max2==0) return max1;
        int[][] dp = new int[max1+1][max2+1];

        //注意这里要进1制，别忘了等号，word1[0]需要执行的次数(删除)
        for (int i = 0; i <= max2; i++) {
            dp[0][i] = i;
        }

        //注意这里要进1制，别忘了等号
        for (int i = 0; i <= max1; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < max1; i++) {
            for (int j = 0; j < max2; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    dp[i+1][j+1] = dp[i][j];
                else{//删除word1的一个字符或者删除word2的一个字符
                    dp[i+1][j+1] = Math.min(Math.min(dp[i+1][j],dp[i][j+1])+1,dp[i][j]+2);//同时删2个
                }

            }
        }

        return dp[max1][max2];




    }
}
