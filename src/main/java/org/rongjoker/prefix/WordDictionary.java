package org.rongjoker.prefix;

import java.util.ArrayList;
import java.util.List;

public class WordDictionary {

    Trail root;

    public WordDictionary() {
        root = new Trail();

    }

    public void addWord(String word) {
        char[] chars = word.toCharArray();
        Trail cur = root;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char aChar = chars[i];
            if (cur.trails[aChar - 'a'] == null) {
                cur.trails[aChar - 'a'] = new Trail();
            }
            cur = cur.trails[aChar - 'a'];
            if (i==length-1)cur.node = true;
        }

    }

    public boolean search(String word) {
        char[] chars = word.toCharArray();
        List<Trail> cur = new ArrayList<>();
        cur.add(root);
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            List<Trail> next = new ArrayList<>();
            char aChar = chars[i];
            for (Trail trail : cur) {
                if (trail == null) {
                    continue;
                }
                Trail[] trails = trail.trails;
                if ('.' == aChar) {
                    for (Trail temp : trails) {
                        if (temp == null) {
                            continue;
                        }
                        next.add(temp);
                        if (i == length - 1 && temp.node) {
                            return true;
                        }
                    }
                } else {
                    if (trail.trails[aChar - 'a'] != null) {
                        Trail temp = trail.trails[aChar - 'a'];
                        next.add(temp);
                        if (i == length - 1 && temp.node) {
                            return true;
                        }
                    }
                }
            }

            cur = next;
        }

        return false;
    }


    static class Trail{
        Trail[] trails = new Trail[26];
        boolean node;

    }


    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));; // return False
        System.out.println(wordDictionary.search("bad"));; // return True
        System.out.println(wordDictionary.search(".ad"));; // return True
        System.out.println(wordDictionary.search("b.."));; // return True
    }



}
