package org.rongjoker.queue;


import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @date 04/23/2021
 *
 * 滑动窗口的最大值 https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * 虽然是滑动窗口，但是提高效率主要靠单调队列
 * 左侧保留最大值，从左到右单调递减，遇到违反单调性，则直接删掉前面的数据，保持单调性，这样队列头就是最大值，如果队列头的下标位于滑动窗口第一位，用完就可以删掉
 * deque.peekFirst()比get更快
 * 双向队列，记得用removeLast而不是pop
 *
 *
 */
public class MaxSlidingWindow239 {

    @Test
    public void test239(){

        int[] x = maxSlidingWindow(new int[]{1,3,1,2,0,5},3);

        System.out.println(Arrays.toString(x));

    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int lens = nums.length;
        if (lens==0)return new int[]{};

        int[] ans = new int[lens - k +1];
        Deque<Integer> deque = new LinkedList<>();
        int index;
        for (int i = 0; i < lens; i++) {

            while(!deque.isEmpty() && nums[deque.getLast()]  < nums[i]){
                deque.removeLast();
            }
            deque.addLast(i);

            if (i>=k-1){
                index = i-k+1;
                ans[index] = nums[deque.peekFirst()];
                if(deque.peekFirst() == index)deque.removeFirst();
            }

        }
        return ans;

    }
}
