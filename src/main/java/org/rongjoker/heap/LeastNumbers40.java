package org.rongjoker.heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @date 02/18/2021
 *
 * 剑指 Offer 40. 最小的k个数 https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * 最小堆类型的题目
 * 也可以用快速排序来解决，速度应该更快 @todo
 *
 */
public class LeastNumbers40 {

    @Test
    public void test1046(){
        int[] nums = {2,7,4,1,8,1};
        System.out.println(Arrays.toString(getLeastNumbers(nums, 3)));

    }

    public int[] getLeastNumbers(int[] arr, int k) {

        int len = arr.length;
        if(len<=k)return arr;

        int[] array = new int[k];

//        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);//大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();//小顶堆
        for (int i : arr)
            pq.offer(i);

        for (int i = 0; i < k; i++) {
            array[i] = pq.poll();
        }

        return array;

    }
}
