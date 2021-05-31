package org.rongjoker.stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @date 05/31/2021
 * 402. 移掉K位数字 https://leetcode-cn.com/problems/remove-k-digits/
 * <p>
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 */
public class RemoveKdigits402 {

    @Test
    public void test402() {
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("10", 1));
    }

    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (len <= k) return "0";

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char temp = num.charAt(i);
            while (!stack.isEmpty() && stack.peekLast() > temp && k > 0) {
                stack.pollLast();
                --k;
            }
            stack.offerLast(temp);
        }

        while (k > 0) {
            stack.pollLast();
            --k;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            if(stack.peekFirst() == '0')stack.pollFirst();
            else break;

        }
            while (!stack.isEmpty()) sb.append(stack.pollFirst());

        return sb.length()==0?"0":sb.toString();


    }
}
