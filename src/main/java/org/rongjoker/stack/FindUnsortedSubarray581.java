package org.rongjoker.stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @date 08/03/2021
 * 581. 最短无序连续子数组 https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 *
 * 单调栈2次遍历
 *
 *
 */
public class FindUnsortedSubarray581 {

    @Test
    public void test581() {
        System.out.println(findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
        System.out.println(findUnsortedSubarray(new int[]{1,2,3,4}));
        System.out.println(findUnsortedSubarray(new int[]{5, 4, 3, 2, 1,0}));//[5,4,3,2,1]

    }


    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; ++i) {
            while (!queue.isEmpty() && nums[queue.peekLast()] > nums[i]) queue.pollLast();
            queue.addLast(i);
        }
        int countF = 0;
        for (int i = 0; i < len; i++) {
            if (!queue.isEmpty() && i == queue.peekFirst()) {
                countF++;
                queue.pollFirst();
            } else break;
        }

        if (countF == len) return 0;

        queue = new LinkedList<>();

        for (int i = len - 1; i >= 0; --i) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) queue.pollLast();
            queue.addLast(i);
        }

        for (int i = len - 1; i >= 0; i--) {
            if (!queue.isEmpty() && i == queue.peekFirst()) {
                countF++;
                queue.pollFirst();
            } else break;
        }

        return len - countF;
    }
}
