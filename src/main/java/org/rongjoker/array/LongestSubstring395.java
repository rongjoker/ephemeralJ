package org.rongjoker.array;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 *
 *
 */
public class LongestSubstring395 {

    @Test
    public void test395(){


        System.out.println(longestSubstring("aaabb",3));
        System.out.println(longestSubstring("ababbc",2));
    }



    public int longestSubstring(String s, int k) {
        int len = s.length();
        if(len<k)return 0;
        char[] chars = s.toCharArray();
        int[][] prefix = new int[len+1][26];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            System.arraycopy(prefix[i], 0, prefix[i + 1], 0, 26);
            prefix[i + 1][chars[i]-'a'] ++;
            loop1:for(int m=0;m<=(i-k +1);++m){
                for (int j = 0; j < 26; j++) {
                    int ava = prefix[i+1][j] - prefix[m][j];
                    if(ava<k && ava>0)continue loop1;
                }
                ans = Math.max(ans,i-m+1);
            }
        }
        return ans;


    }


    /**
     * 滑动窗口,较难理解
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring2(String s, int k) {
        int ans = 0;
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for (int p = 1; p <= 26; p++) {
            Arrays.fill(cnt, 0);
            // tot 代表 [j, i] 区间所有的字符种类数量；sum 代表满足「出现次数不少于 k」的字符种类数量
            for (int i = 0, j = 0, tot = 0, sum = 0; i < n; i++) {
                int u = cs[i] - 'a';
                cnt[u]++;
                // 如果添加到 cnt 之后为 1，说明字符总数 +1
                if (cnt[u] == 1) tot++;
                // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                if (cnt[u] == k) sum++;
                // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 p，那么我们要删除掉一些字母，即「左指针」右移
                while (tot > p) {
                    int t = cs[j++] - 'a';
                    cnt[t]--;
                    // 如果添加到 cnt 之后为 0，说明字符总数-1
                    if (cnt[t] == 0) tot--;
                    // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                    if (cnt[t] == k - 1) sum--;
                }
                // 当所有字符都符合要求，更新答案
                if (tot == sum) ans = Math.max(ans, i - j + 1);
            }
        }
        return ans;
    }

}
