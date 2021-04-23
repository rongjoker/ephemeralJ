package org.rongjoker.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @date 04/20/2021
 * 862. 和至少为 K 的最短子数组 https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/
 *
 *
 */
public class ShortestSubarray862 {


    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N+1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (long) A[i];

        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N+1; // N+1 is impossible
        Deque<Integer> monoq = new LinkedList<>(); //opt(y) candidates, as indices of P

        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            //如果现在队列里存在更大的前缀，说明当前值是负数，那么后续满足条件的，肯定在负数这个点的后面,保存前面大的数据就没有用，维持单调即可
            // 比如4,-1,2,1查找和大于等于6，则前缀和为0，4，3，5，6；在6满足条件，前面的4完全没必要
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])
                monoq.removeLast();
            //左边的满足条件，记录位移后即可删掉，右侧再移动，如果遇到与左边的相减满足条件，肯定宽度比当前的要大，故可以删除，因为是单调递增，所以左右不满足，左右之间的肯定也不满足，继续向右移动即可
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)
                ans = Math.min(ans, y - monoq.removeFirst());

            monoq.addLast(y);
        }

        return ans < N+1 ? ans : -1;
    }


}
