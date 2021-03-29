package org.rongjoker.stack;

import java.util.Stack;

/**
 * @date 03/29/2021
 * 232. 用栈实现队列 https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * 用其中一个栈做输入，另一个做输出，输出的用完，则把输入的数据全部放入输出中
 * 输入始终在输入栈，输出始终在输出栈
 *
 *
 */
public class MyQueue {

    public static void main(String[] args) {

        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        System.out.println(myQueue.pop());
        myQueue.push(5);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());

    }



    Stack<Integer> s1 = new Stack<>();//入栈
    Stack<Integer> s2 = new Stack<>();//输出


    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);

    }

    int temp;

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(s2.isEmpty())change();
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(s2.isEmpty())change();
        return s2.peek();
    }

    private void change(){
        while (!s1.isEmpty())
            s2.push(s1.pop());

    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();

    }
}
