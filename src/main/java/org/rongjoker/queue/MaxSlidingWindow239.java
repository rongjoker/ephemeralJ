package org.rongjoker.queue;


import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @date 04/23/2021
 *
 * 239.滑动窗口的最大值 https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * 虽然是滑动窗口，但是提高效率主要靠单调队列
 * 左侧保留最大值，从左到右单调递减，遇到违反单调性，则直接删掉前面的数据，保持单调性，这样队列头就是最大值，如果队列头的下标位于滑动窗口第一位，用完就可以删掉
 * deque.peekFirst()比get更快
 * 双向队列，记得用removeLast而不是pop
 *
 *
 *
 */
public class MaxSlidingWindow239 {

    @Test
    public void test239(){

        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{9}, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[]{9}, 1)));

    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;
        if(len==1 || k==1)return nums;
        int[] ans = new int[len-k+1];
        Deque<Integer> queue = new LinkedList<>();
        for(int i=0;i<k-1;++i){
            while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        for(int i=k-1;i<len;++i){

            while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
            if(i - queue.peekFirst() >k-1)queue.pollFirst();

            ans[i-k+1] = nums[queue.peekFirst()];

        }

        return ans;
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
