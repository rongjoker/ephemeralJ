package org.rongjoker.str;

import org.junit.Test;

import java.util.*;

/**
 * @date 03/28/2021
 * 20. 有效的括号 https://leetcode-cn.com/problems/valid-parentheses/
 * stack的妙用
 * hash的containsKey比直接更快
 *
 */
public class ValidParentheses20 {

    @Test
    public void test20(){

        System.out.println(isValid("){"));

    }

    public boolean isValid(String s) {
        int len = s.length();
        if(len%2==1)return false;
        Deque<Character> stack = new LinkedList<>();
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        for(int i=0;i<len;++i){
            if(!map.containsKey(s.charAt(i))){//左括号
                stack.push(s.charAt(i));
            }else if(stack.isEmpty() || stack.pop()!=map.get(s.charAt(i)))return false;//右括号
        }
        return stack.isEmpty();


    }


}
