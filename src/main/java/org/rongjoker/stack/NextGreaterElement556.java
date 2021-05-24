package org.rongjoker.stack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 05/24/2021
 * 556. 下一个更大元素 III https://leetcode-cn.com/problems/next-greater-element-iii/
 * 利用单调栈来求解
 *
 *
 */
public class NextGreaterElement556 {


    @Test
    public void test556(){
        System.out.println(nextGreaterElement(2147483476));
    }


    public int nextGreaterElement(int n) {

        String s = Integer.toString(n);
        int len = s.length();
        if (len ==1)return -1;

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char temp = s.charAt(i);
            while (!stack.isEmpty() && stack.peekLast()< temp)
                stack.pollLast();
            stack.addLast(temp);

        }

        System.out.println(stack.size());



        return  -1;

    }


}
