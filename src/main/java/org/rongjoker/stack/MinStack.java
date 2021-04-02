package org.rongjoker.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 04/02/2021
 * 155. 最小栈 https://leetcode-cn.com/problems/min-stack/
 * 关键是删除的时候同时要更新最小值
 * 保存的数据为数组{当前值，当前最小值 - 当前值}
 *
 */
public class MinStack {

    //["MinStack","push","push","getMin","getMin","push","getMin","getMin","top","getMin","pop","push","push","getMin","push","pop","top","getMin","pop"]
    //[[],[-10],[14],[],[],[-20],[],[],[],[],[],[10],[-7],[],[-7],[],[],[],[]]

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-10);
        minStack.push(14);
        System.out.println(minStack.getMin());
        System.out.println(minStack.getMin());
        minStack.push(-20);
        System.out.println(minStack.getMin());
        System.out.println(minStack.getMin());
        System.out.println(minStack.top());

        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(10);
        minStack.push(-7);

        System.out.println(minStack.getMin());
        minStack.push(-7);
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();





//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin());;   // 返回 -3.
//        minStack.pop();
//        System.out.println(minStack.top());     // 返回 0.
//        System.out.println(minStack.getMin());;   // 返回 -2.

    }


    List<int[]> stack;
    int min;



    /** initialize your data structure here. */
    public MinStack() {
        stack= new ArrayList<>();

    }

    public void push(int val) {
        if(stack.size()==0)min = val;
        else min = Math.min(min,val);

        stack.add(new int[]{val,min - val});

    }

    public void pop() {
        stack.remove(stack.size()-1);

        //这里是关键的一步
        if(stack.size()>0)
            min = getMin();


    }

    public int top() {
        return stack.get(stack.size()-1)[0];
    }

    public int getMin() {
        int[] index = stack.get(stack.size()-1) ;
        return index[1] + index[0] ;
    }
}
