package org.rongjoker.ds;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 06/16/2021
 * 1849. 将字符串拆分为递减的连续值 https://leetcode-cn.com/problems/splitting-a-string-into-descending-consecutive-values/
 *
 * DFS解决，效率较低
 *
 *
 */
public class SplitString1849 {



    @Test
    public void test1849(){
//        System.out.println(splitString("1234"));
//        System.out.println(splitString("050043"));
//        System.out.println(splitString("9080701"));
        System.out.println(splitString("64424509442147483647"));
        System.out.println(splitString("10690001068"));
    }



    public boolean splitString(String s) {
        track(s, s.length(), 0);
        return flag;
    }

    Deque<Long> queue = new ArrayDeque<>();
    int cur = 0;
    boolean flag = false;


    public void track(String s, int len, int start) {
        if (cur == len) {
            if(queue.size()>1)flag = true;

            return;
        }

        for (int i = start; i < len; ++i) {
            long in = 0;
            try {
                in = Long.parseLong(s.substring(start, i + 1));
            } catch (NumberFormatException e) {
                break;
            }
            if (queue.isEmpty() || queue.peekLast() == in + 1) {
                queue.addLast(in);
                cur += (i - start + 1);
                track(s, len, i + 1);
                cur -= (i - start + 1);
                queue.pollLast();
            }

        }

    }
}
