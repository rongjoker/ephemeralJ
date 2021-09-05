package org.rongjoker.dp.decide;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * @date 09/05/2021
 *
 * 1987. 不同的好子序列数目
 *
 * 给你一个二进制字符串 binary 。 binary 的一个 子序列 如果是 非空 的且没有 前导 0 （除非数字是 "0" 本身），那么它就是一个 好 的子序列。
 *
 * 请你找到 binary 不同好子序列 的数目。
 *
 * 比方说，如果 binary = "001" ，那么所有 好 子序列为 ["0", "0", "1"] ，所以 不同 的好子序列为 "0" 和 "1" 。 注意，子序列 "00" ，"01" 和 "001" 不是好的，因为它们有前导 0 。
 * 请你返回 binary 中 不同好子序列 的数目。由于答案可能很大，请将它对 109 + 7 取余 后返回。
 *
 * 一个 子序列 指的是从原数组中删除若干个（可以一个也不删除）元素后，不改变剩余元素顺序得到的序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-unique-good-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfUniqueGoodSubsequences1987 {

    @Test
    public void test1987() {

        //        System.out.println(numberOfUniqueGoodSubsequences("10"));
        //        System.out.println(numberOfUniqueGoodSubsequences("01"));
        System.out.println(numberOfUniqueGoodSubsequences("001"));
        System.out.println(numberOfUniqueGoodSubsequences("11"));
        System.out.println(
            numberOfUniqueGoodSubsequences("1100100010101010100100000111110001111001000010000010010111011"));

    }


    /**
     * 类似940，难在怎么去掉前导0
     */
    public int numberOfUniqueGoodSubsequences(String binary) {
        long[] dp = new long[binary.length() + 1];
        //判断字符串里是否有0的存在
        boolean zeroExist = false;
        //hashmap存放最后一次0或1出现的位置
        Map<Character, Integer> map = new HashMap();
        for (int i = 1; i <= binary.length(); i++) {
            char ch = binary.charAt(i - 1);
            if (!map.containsKey(ch)) {
                //如果0，1第一次出现在字符串里 dp[i] = dp[i - 1] * 2 (+ 1);
                //当第一次出现0时，zeroExist为true，为了避免后面统计出0前导的情况，这里不做+1处理，在最后输出的时候通过判断0是否出现zeroExist，统一加1；第一次出现1时，做+1；
                dp[i] = (2 * dp[i - 1]) % 1000000007;
                if (ch == '0') {
                    zeroExist = true;
                } else {
                    dp[i] = (dp[i] + 1) % 1000000007;
                }
            } else {
                //重复出现的0，1，这里出现的都是0非前导的情况
                //dp[i] = dp[i - 1] * 2 - dp[字符i最后出现位置 - 1];
                dp[i] = (2 * dp[i - 1] - dp[map.get(ch) - 1]) % 1000000007;
                //使用long保存结果，这里避免出现减导致的负数
                if (dp[i] < 0) {
                    dp[i] += 1000000007;
                }
            }
            map.put(ch, i);
        }
        if (zeroExist) {
            return (int) (dp[binary.length()] + 1) % 1000000007;
        }
        return (int) dp[binary.length()];
    }


}
