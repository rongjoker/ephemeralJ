package org.rongjoker.prefix;

import org.junit.Test;

/**
 * @date 07/01/2021
 * 1915. 最美子字符串的数目 https://leetcode-cn.com/problems/number-of-wonderful-substrings/
 * 如果某个字符串中 至多一个 字母出现 奇数 次，则称其为 最美 字符串。
 *
 * 例如，"ccjjc" 和 "abab" 都是最美字符串，但 "ab" 不是。
 * 给你一个字符串 word ，该字符串由前十个小写英文字母组成（'a' 到 'j'）。请你返回 word 中 最美非空子字符串 的数目。如果同样的子字符串在 word 中出现多次，那么应当对 每次出现 分别计数。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 *
 *
 */
public class WonderfulSubstrings1915 {


    @Test
    public void test1915(){
        System.out.println(wonderfulSubstrings("aba"));
        System.out.println(wonderfulSubstrings("aabb"));
        System.out.println(wonderfulSubstrings("he"));
    }


    /**
     * 状态压缩
     * @param word
     * @return
     */
    public long wonderfulSubstringsZip(String word) {
        int len = word.length();
        if(len==1)return 1;
        long ans = 0;
        int mask = 0;

        long[] freq = new long[1 << 10];
        freq[0] = 1;//相当于偶数初始化有1个

        char[] chars = word.toCharArray();
        for (int i = 0; i < len; i++) {
            mask ^= (1<<(chars[i]-'a'));//之前如果是奇数则现在变偶数
            ans += freq[mask];//之前的奇偶性完全一样的数量，即偶数（奇数-奇数=偶数；偶数-偶数=偶数）,偶数为0
            for (int j = 0; j < 10; j++) {
                ans += freq[mask ^ (1 << j)];//之前奇偶性差距一个的数量，允许差距1个
            }
            freq[mask]++;

        }
        return ans;
    }


    /**
     * 前缀数量和，会超时
     * @param word
     * @return
     */
    public long wonderfulSubstrings(String word) {
        int len = word.length();
        if(len==1)return 1;
        long ans = 0;
        long[][] prefix = new long[len+1][10];
        char[] chars = word.toCharArray();
        for (int i = 0; i < len; i++) {
            System.arraycopy(prefix[i], 0, prefix[i + 1], 0, 10);
            prefix[i+1][chars[i]-'a']++;
            loop:for(int k=0;k<=i;++k){
                int count = 0;
                for (int j = 0; j < 10; j++) {
                    if((prefix[i+1][j] -prefix[k][j])%2 ==1)count++;
                    if(count>1)continue loop;
                }
                ans++;
            }
        }
        return ans;
    }





}
