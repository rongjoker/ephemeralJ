package org.rongjoker.sw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @date 06/18/2021
 *  1239. 串联字符串的最大长度 https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 *  给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 *
 * 请返回所有可行解 s 中最长长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class MaxLength1239 {

    public int maxLength(List<String> arr) {
        Map<Character,Integer> cache = new HashMap<>();
        int ans = 0;
        int temp = 0;
        for (int i = 0; i < arr.size(); i++) {
            int available = -1;
            char[] chars = arr.get(i).toCharArray();
            for (char aChar : chars) {
            }

        }

        return ans;

    }
}
