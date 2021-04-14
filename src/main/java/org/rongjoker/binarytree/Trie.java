package org.rongjoker.binarytree;

/**
 * @date 04/14/2021
 * 208. 实现 Trie (前缀树) https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * 'z'-'a' = 25
 *
 * 本质是一种树，不过这种树比较特殊，有26个子节点，同时附带一个是否结束的标识，如果有则说明能搜索到结果，否则仅仅是前缀
 *
 */
public class Trie {

    public static void main(String[] args) {
        System.out.println('z'-'a');
    }

    private final Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;//截止时候添加结束标识，表示不光是前缀，而且存在这个数据
    }

    //是否结束的标识，如果有则说明能搜索到结果，否则仅仅是前缀
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
