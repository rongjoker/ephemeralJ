package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.*;

/**
 * @date 06/18/2021
 * 1239. 串联字符串的最大长度 https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * <p>
 * 请返回所有可行解 s 中最长长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxLength1239 {


    @Test
    public void test1239() {


//        System.out.println(maxLength(Arrays.asList("un", "iq", "ue")));
//        System.out.println(maxLength(Arrays.asList("cha", "r", "act", "ers")));
//        System.out.println(maxLength(Arrays.asList("abcdefghijklmnopqrstuvwxyz")));
//        System.out.println(maxLength(Arrays.asList("yy","bkhwmpbiisbldzknpm")));


    }

    /**
     * 回溯法，较慢
     * @param arr
     * @return
     */
    public int maxLength(List<String> arr) {
        List<char[]> ready = new ArrayList<>();
        for (String s : arr) {
            char[] chars = s.toCharArray();
            int[] dict = new int[26];
            boolean repeat = false;
            for (char aChar : chars) {
                if (dict[aChar - 'a'] > 0) {
                    repeat = true;
                    break;
                } else dict[aChar - 'a'] = 1;
            }
            if(!repeat)ready.add(chars);
        }

        track(ready, 0, ready.size(), 0);

        return ans;

    }

    int ans = 0;

    Deque<char[]> queue = new ArrayDeque<>();
    Set<Character> path = new HashSet<>();


    public void track(List<char[]> nums, int start, int len, int cur) {
        if (start == len) {
            ans = Math.max(ans, cur);
            return;
        }

        for (int i = start; i < len; i++) {
            char[] cs = nums.get(i);
            if (queue.isEmpty() || !isContains(cs)) {
                queue.addLast(cs);
                add(cs);
                track(nums, i + 1, len, cur + cs.length);
                queue.removeLast();
                remove(cs);
            } else track(nums, i + 1, len, cur);
        }
    }

    boolean isContains(char[] cs) {
        for (char c : cs) {
            if (path.contains(c)) return true;
        }
        return false;
    }

    void add(char[] cs) {
        for (char c : cs) path.add(c);
    }

    void remove(char[] cs) {
        for (char c : cs) path.remove(c);
    }


}
