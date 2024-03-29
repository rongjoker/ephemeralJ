package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 09/01/2021
 *
 * 剑指 Offer 46. 把数字翻译成字符串
 *
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 *
 */
public class TranslateNum {


    @Test
    public void test46(){
        System.out.println(translateNum(12258));


    }

    public int translateNum(int num) {
        if(num<10)return 1;

        String s = String.valueOf(num);
        char[] chars = s.toCharArray();
        int len = chars.length;

        int[] dp = new int[len+1];
        dp[0] = 1;dp[1] = 1;
        for (int i = 1; i < len; i++) {
            if(chars[i-1]=='0' || chars[i-1]>'2' || (chars[i-1]=='2' && chars[i]>'5'))dp[i+1] = dp[i];
            else dp[i+1] = dp[i] + dp[i-1] ;
        }
        return dp[len];

    }

}
