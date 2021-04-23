package org.rongjoker.queue;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @date 04/20/2021
 * 862. 和至少为 K 的最短子数组 https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/
 * 前缀和+单调队列
 *
 */
public class ShortestSubarray862 {


    @Test
    public void test862(){

        System.out.println(shortestSubarray(
        new int[]{2,-1,2},3));


    }


    public int shortestSubarray(int[] A, int K) {
        int len = A.length;
        long[] prefixs = new long[len+1];//前缀和
        for (int i = 0; i < len; ++i)
            prefixs[i+1] = prefixs[i] + (long) A[i];

        int ans = len+1;
        Deque<Integer> deque = new LinkedList<>();//双端队列+单调性

        for (int y = 0; y < prefixs.length; ++y) {
            //如果现在队列里存在更大的前缀，说明当前值是负数，那么后续满足条件的，肯定在负数这个点的后面,保存前面大的数据就没有用，维持单调即可
            // 比如4,-1,2,1查找和大于等于6，则前缀和为0，4，3，5，6；在6满足条件，前面的4完全没必要
            while (!deque.isEmpty() && prefixs[y] <= prefixs[deque.peekLast()])
                deque.removeLast();
            //左边的满足条件，记录位移后即可删掉，右侧再移动，如果遇到与左边的相减满足条件，肯定宽度比当前的要大，故可以删除，因为是单调递增，所以左右不满足，左右之间的肯定也不满足，继续向右移动即可
            while (!deque.isEmpty() && prefixs[y] >= prefixs[deque.peekFirst()] + K)
                ans = Math.min(ans, y - deque.removeFirst());

            deque.addLast(y);
        }

        return ans < len+1 ? ans : -1;
    }


}
