package org.rongjoker.stack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 05/26/2021
 * 1190. 反转每对括号间的子串 https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * 栈的使用 很巧妙，完全不需要反转str
 * 更高效的解法是不使用栈来反转
 */
public class ReverseParentheses1190 {

    @Test
    public void test1190() {
        System.out.println(reverseParentheses2("(abcd)"));
        System.out.println(reverseParentheses2("(u(love)i)"));
        System.out.println(reverseParentheses2("(ed(et(oc))el)"));
        System.out.println(reverseParentheses2("a(bcdefghijkl(mno)p)q"));

    }


    public String reverseParentheses(String s) {
        int len = s.length();
        if (len <= 1) return s;
        char[] chars = s.toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c == ')') {//弹出
                while (stack.peekLast() != '(') {
                    stringBuilder.append(stack.pollLast());
                }
                stack.pollLast();
                for (int i = 0; i < stringBuilder.length(); i++) {//取出再放回去即可
                    stack.addLast(stringBuilder.charAt(i));
                }
                stringBuilder = new StringBuilder();
            } else stack.addLast(c);
        }

        while (!stack.isEmpty())
            stringBuilder.append(stack.pollFirst());

        return stringBuilder.toString();

    }

    /**
     * 更高效的解法
     * @param s
     * @return
     */
    public String reverseParentheses2(String s) {
        int len = s.length();
        if (len <= 1) return s;
        char[] chars = s.toCharArray();

        Deque<String> stack = new ArrayDeque<>();
        StringBuilder stringBuilder = new StringBuilder();

        //以(u(love)i)为例子
        for (char c : chars) {
            if (c == ')') {//当前字符串结束love
                stringBuilder.reverse();
                stringBuilder.insert(0,stack.pollLast());
                //把前面的u拿出来,拼成uevol，含义是love已经结束，可以反转，现在要找到更大的反转范围，即上一个左括号开始的内容,一直到下一个右括号结束，找到新的反转范围u-evol-i再次反转
            }else if (c == '('){//将前面的字符串加入栈u
                stack.addLast(stringBuilder.toString());
                stringBuilder.setLength(0);

            } else stringBuilder.append(c);
        }
        while (!stack.isEmpty())
            stringBuilder.append(stack.pollLast());

        return stringBuilder.toString();

    }
}
