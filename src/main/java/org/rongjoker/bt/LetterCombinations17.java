package org.rongjoker.bt;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 03/07/2021
 * 17. 电话号码的字母组合 https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 典型的回溯算法应用题目,不需要考虑顺序
 */
public class LetterCombinations17 {


    @Test
    public void test17() {

        System.out.println(letterCombinations("23"));

    }


    Map<Character, String> codes = new HashMap<>();


    List<String> permute = new ArrayList<>();


    public List<String> letterCombinations(String digits) {

        codes.put('2', "abc");
        codes.put('3', "def");
        codes.put('4', "ghi");
        codes.put('5', "jkl");
        codes.put('6', "mno");
        codes.put('7', "pqrs");
        codes.put('8', "tuv");
        codes.put('9', "wxyz");


        if (digits.length() == 0)
            return permute;

        backtracking(digits, 0, digits.length(), new StringBuilder());


        return permute;

    }


    public void backtracking(String digits, int start, int len,  StringBuilder path) {

        if (start == len) {
            permute.add(path.toString());
            return;
        }

        String code = codes.get(digits.charAt(start));

        for (int j = 0; j < code.length(); j++) {

            path.append(code.charAt(j));

            backtracking(digits, start + 1, len, path);

            path.deleteCharAt(path.length() - 1);


        }

    }


}
