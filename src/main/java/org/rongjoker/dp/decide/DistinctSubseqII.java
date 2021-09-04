package org.rongjoker.dp.decide;

import java.util.Arrays;
import org.junit.Test;

/**
 * @date 09/01/2021
 * 940. 不同的子序列 II
 *
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 *
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 *
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 *
 * 输入：s = "abc"
 * 输出：7
 * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
 *
 * 输入：s = "aba"
 * 输出：6
 * 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
 *
 *
 * 输入：s = "aaa"
 * 输出：3
 * 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DistinctSubseqII {


    @Test
    public void test940() {
        //        System.out.println(distinctSubseqII("a"));
        //        System.out.println(distinctSubseqII("abc"));
        //        System.out.println(distinctSubseqII("aba"));
        //        System.out.println(distinctSubseqII("aaa"));
        System.out.println(distinctSubseqII("lee"));
        System.out.println(distinctSubseqII2("lee"));

    }

    public int distinctSubseqII(String S) {
        int MOD = 1_000_000_007;
        int N = S.length();
        int[] dp = new int[N + 1];
        dp[0] = 1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < N; ++i) {
            int x = S.charAt(i) - 'a';
            dp[i + 1] = dp[i] * 2 % MOD;
            if (last[x] >= 0) {
                dp[i + 1] -= dp[last[x]];
            }
            dp[i + 1] %= MOD;
            last[x] = i;
        }

        dp[N]--;
        if (dp[N] < 0) {
            dp[N] += MOD;
        }
        return dp[N];
    }


    /**
     * o(n^2)的朴素解法
     *
     *
     * // dp[i]表示前i个元素中以第i个元素结尾时, 且和前面的i - 1个状态都没有重复子序列时的个数(不含空串, 注意和方法二的区别)
     * // 所以最后的答案是所有加起来
     * // 难点及关键点: if (S[k] == S[i]) break; 为什么这里要break, 如果不break行不行.
     * // 回到dp数组的定义, 仔细思考一下, 因为不能和前面的重复, 这样做是为了避免重复计数.
     */
    public int distinctSubseqII2(String s) {

        int MOD = 1000000007;
        char[] cs = s.toCharArray();

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            // 考虑自己单独成段时,此处可优化
            dp[i] = 1;
            for (int k = i - 1; k >= 0; --k) {
                if (cs[k] == cs[i]) {
                    dp[i] = 0;
                    break;
                }
            }
            // 考虑拼接在前面段的后面时
            for (int k = i - 1; k >= 0; --k) {
                dp[i] = (dp[i] + dp[k]) % MOD;
                if (cs[k] == cs[i]) {
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = (ans + dp[i]) % MOD;
        }
        return ans;
    }


}
