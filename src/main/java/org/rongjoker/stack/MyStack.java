package org.rongjoker.stack;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date 03/28/2021
 * 225. 用队列实现栈 https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 两个队列，交替使用，每次主队列只保留1个数字
 * 取数据时候，将次队列数据只保留一个，其余放入主队列，然后拿出主队列第一个，并将主次队列交换（即次队列变成主队列）
 * 这个写法比较复杂，也有简单的办法，用一个队列即可，每次取数据就把n-1个数据全部拿出，重新入队
 *
 */
public class MyStack {

    @Test
    public void test225(){

        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());


    }

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    boolean flag=true;


    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        if(flag){
            if(queue1.size()>0){
                queue2.offer(queue1.poll());
            }
            queue1.offer(x);

        }else{
            if(queue2.size()>0){
                queue1.offer(queue2.poll());
            }
            queue2.offer(x);
        }


    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(flag){
            while(queue2.size()>1){
                queue1.offer(queue2.poll());
            }
            flag = false;
            return queue1.poll();
        }else{
            while(queue1.size()>1){
                queue2.offer(queue1.poll());
            }
            flag = true;
            return queue2.poll();
        }

    }

    /** Get the top element. */
    public int top() {
        if(flag){
            return queue1.peek();
        }else{
            return queue2.peek();
        }

    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.size() +queue2.size() ==0;

    }


}
