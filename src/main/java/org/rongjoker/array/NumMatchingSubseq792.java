package org.rongjoker.array;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * 792. 匹配子序列的单词数
 *
 * 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。
 *
 * 示例:
 * 输入:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * 输出: 3
 * 解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-matching-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class NumMatchingSubseq792 {

    @Test
    public void test792(){
        System.out.println(numMatchingSubseq("abcde",new String[]{"a", "bb", "acd", "ace"}));
        System.out.println(numMatchingSubseq2("abcde",new String[]{"a", "bb", "acd", "ace"}));

    }


    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;

        List<StringBuilder>[] dict = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            dict[i] = new ArrayList<>();
        }
        for (String word : words) {
            int index = word.charAt(0) - 'a';
            dict[index].add(new StringBuilder(word));
        }
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            List<StringBuilder> available = dict[index];
            dict[index] = new ArrayList<>();

            for (StringBuilder obj : available) {
                obj.deleteCharAt(0);
                if(obj.length()==0){
                    ans++;
                }else {
                    dict[obj.charAt(0)-'a'].add(obj);
                }
            }
        }

        return ans;

    }

    public int numMatchingSubseq2(String s, String[] words) {
        int ans = 0;

        List<Node>[] dict = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            dict[i] = new ArrayList<>();
        }
        for (String word : words) {
            int index = word.charAt(0) - 'a';
            dict[index].add(new Node(word,word.length(),0));
        }
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            List<Node> available = dict[index];
            dict[index] = new ArrayList<>();

            for (Node obj : available) {
                obj.index++;
                if(obj.index==obj.len){
                    ans++;
                }else {
                    dict[obj.str.charAt(obj.index)-'a'].add(obj);
                }
            }
        }

        return ans;

    }

    static class Node{
        Node(String s,int l,int i){
            str = s;
            index = i;
            len = l;

        }
        String str;
        int index;
        int len;

    }

}


