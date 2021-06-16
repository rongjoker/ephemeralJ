package org.rongjoker.ds;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 06/16/2021
 * 849. 将字符串拆分为递减的连续值 https://leetcode-cn.com/problems/splitting-a-string-into-descending-consecutive-values/
 *
 *
 */
public class SplitString1849 {


    public boolean splitString(String s) {

        int len = s.length();

        if(len==cur) return true;
        else return false;


    }

    Deque<Integer> queue = new ArrayDeque<>();
    int cur = 0;


    public void track(String s,int len,int start){
        if(cur==len)return;




    }
}
