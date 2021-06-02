package org.rongjoker.stack;

import org.junit.Test;

/**
 * @date 06/02/2021
 * 316. 去除重复字母 https://leetcode-cn.com/problems/remove-duplicate-letters/
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 利用单调栈来解答，要保持字符串至少包含某个char一个
 *
 */
public class RemoveDuplicateLetters316 {

    @Test
    public void test316() {
        System.out.println(removeDuplicateLetters("bcabc"));
        System.out.println(removeDuplicateLetters("cbacdcbc"));
        System.out.println(removeDuplicateLetters("a"));
        System.out.println(removeDuplicateLetters("bbcaac"));
        System.out.println(removeDuplicateLetters("abacb"));
    }


    public String removeDuplicateLetters(String s) {
        StringBuilder sbf = new StringBuilder();
        int[] word = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) word[c - 'a']++;
        int[] count = new int[26];
        for (char c : cs) {
            if (count[c - 'a'] == 0) {//去重复
                while (sbf.length() > 0 && sbf.charAt(sbf.length() - 1) > c && word[sbf.charAt(sbf.length() - 1) - 'a'] > 0) {
                    count[sbf.charAt(sbf.length() - 1) - 'a']--;
                    sbf.setLength(sbf.length() - 1);
                }
                sbf.append(c);
                count[c - 'a']++;
            }
            word[c - 'a']--;

        }
        return sbf.toString();

    }
}
