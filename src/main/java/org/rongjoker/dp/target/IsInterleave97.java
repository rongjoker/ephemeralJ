package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 09/01/2021
 * 97. 交错字符串
 *
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsInterleave97 {


    @Test
    public void test97() {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));

    }

    /**
     * 典型的dp题目，每一步取决于前一步 + 当前步任何一个满足
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        char[] ss1 = s1.toCharArray(), ss2 = s2.toCharArray(), ss3 = s3.toCharArray();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int point = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j] && ss1[i - 1] == ss3[point];
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1] && ss2[j - 1] == ss3[point];
                }

            }

        }

        return dp[len1][len2];

    }


}
