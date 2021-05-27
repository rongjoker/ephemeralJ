package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.*;

/**
 * @date 05/27/2021
 *
 *
 * 去重-全排列
 *
 */
public class Permutation38 {

    @Test
    public void test38() {
        System.out.println(Arrays.toString(permutation("aac")));
    }

        private final List<String> list = new ArrayList<>();

        public String[] permutation(String s) {
            recursive(s.toCharArray(), 0);
            return list.toArray(new String[0]);
        }

        private void recursive(char[] chars, int index) {
            if (index + 1 == chars.length) {
                list.add(new String(chars));
                return;
            }
            for (int i = index; i < chars.length; i++) {
                if (i == index) {
                    recursive(chars, index + 1);
                    continue;
                }

                // 从当前位置往前查找是否存在相同字符
                int k = i - 1;
                for (; k >= index; --k) {
                    if (chars[k] == chars[i]) break;
                }

                // 出现了重复字符
                if (k + 1 != index) {
                    continue;
                }

                swap(chars, i, index);
                recursive(chars, index + 1);
                swap(chars, i, index);
            }
        }

        private void swap(char[] chars, int i, int j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

