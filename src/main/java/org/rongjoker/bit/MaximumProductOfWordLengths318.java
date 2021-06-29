package org.rongjoker.bit;

/**
 * @date 06/29/2021
 * 318. 最大单词长度乘积 https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
 *
 * 位运算优化字符串判断重复
 *
 */
public class MaximumProductOfWordLengths318 {


    public int mask(String word){
        int ans = 0;
        char[] cs=word.toCharArray();
        for(char c:cs){
            ans |=(1 << (c-'a'));//或运算回避了重复添加的问题
        }

        return ans;

    }


    public int maxProductBit(String[] words) {
        int len = words.length;
        int[] available = new int[len];
        for(int i=0;i<len;++i)available[i] = mask(words[i]);

        int ans = 0;

        for(int i=0;i<len-1;++i){
            for(int j=i+1;j<len;++j){
                if((available[i] & available[j]) >0) continue;//与运算判断重复
                ans = Math.max(words[i].length() * words[j].length(),ans);
            }
        }
        return ans;
    }


    /**
     * 蛮力算法
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int len = words.length;
        int[][] available = new int[len][26];
        for(int i=0;i<len;++i){
            char[] cs=words[i].toCharArray();
            for(char c:cs){
                available[i][c-'a']++;
            }
        }

        int ans = 0;

        for(int i=0;i<len-1;++i){
            char[] cs=words[i].toCharArray();
            loop:for(int j=i+1;j<len;++j){
                for(char c:cs){
                    if(available[j][c-'a']>0)continue loop;
                }
                ans = Math.max(words[i].length() * words[j].length(),ans);
            }
        }
        return ans;
    }
}
