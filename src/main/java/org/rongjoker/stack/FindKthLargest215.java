package org.rongjoker.stack;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @date 02/18/2021
 * <p>
 * 215. 数组中的第K个最大元素 https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 套路题目，最大的k用小顶堆，最小的k用大顶堆
 * 不停的扔掉最小的数据，保留下来的就是最大的top K
 *
 */
public class FindKthLargest215 {

    @Test
    public void test215() {
        int[] nums = {3,2,1,5,6,4};
        System.out.println(findKthLargest(nums, 2));


    }


    public int findKthLargest(int[] nums, int k) {

        int len = nums.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }

        for (int i = k; i < len; i++) {
            if(nums[i]> queue.peek()){
                queue.poll();
                queue.offer(nums[i]);
            }

        }

        return queue.peek();

    }


}
