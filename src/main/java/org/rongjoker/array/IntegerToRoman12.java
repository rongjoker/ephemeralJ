package org.rongjoker.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 05/14/2021
 * 12. 整数转罗马数字 https://leetcode-cn.com/problems/integer-to-roman/
 */
public class IntegerToRoman12 {

    @Test
    public void test12() {
        System.out.println(intToRoman(1943));
        System.out.println(intToRomanOptimize(1943));
    }

    /**
     * 适合数据很大的解法
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> dict = new HashMap<>();
        dict.put(1000, "M");
        dict.put(900, "CM");
        dict.put(500, "D");
        dict.put(400, "CD");
        dict.put(100, "C");
        dict.put(90, "XC");
        dict.put(50, "L");
        dict.put(40, "XL");
        dict.put(10, "X");
        dict.put(9, "IX");
        dict.put(5, "V");
        dict.put(4, "IV");
        dict.put(1, "I");

        int[] ret = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        int len = ret.length;
        int start = -1;
        int left = 0, right = len - 1, middle;
        while (left < right) {
            middle = left + ((right - left) >> 1);
            if (ret[middle] == num) {
                start = middle;
                break;
            } else if (ret[middle] > num) {
                right = middle - 1;
            } else left = middle + 1;
        }
        if (start == -1) start = left;

        int count, temp;
        for (int i = start; i >= 0; i--) {
            temp = ret[i];
            if (num >= temp) {
                count = num / temp;
                num %= temp;
                for (int j = 0; j < count; ++j) {
                    sb.append(dict.get(temp));
                }
                if (0==num)break;
            }
        }
        return sb.toString();
    }


    /**
     * 不需要hash，两个数组，更快
     * @param num
     * @return
     */
    public String intToRomanOptimize(int num) {
        StringBuilder sb = new StringBuilder();
        int[] ret = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] dict = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int len = ret.length;
        int count, temp;
        for (int i = 0; i < len; i++) {
            temp = ret[i];
            if (num >= temp) {
                count = num / temp;
                num %= temp;
                sb.append(dict[i].repeat(count));
                if (0==num)break;
            }
        }
        return sb.toString();
    }
}
