package org.rongjoker.stack;

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
    public void test40(){
        int[] nums = {0,1,1,2,4,4,1,3,3,2};
        System.out.println(Arrays.toString(getLeastNumbersOptimize(nums, 6)));

    }

    /**
     * 堆的默认用法，这种相当于暴力适用堆,速度达到o(arr.length)
     * @param arr
     * @param k
     * @return
     */
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


    /**
     * 优化版本，速度可以达到o(k)
     * 每次加入后都需要重新排序
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbersOptimize(int[] arr, int k) {

        if(k==0)
            return new int[0];

        int len = arr.length;
        if(len<=k)return arr;

        int[] array = new int[k];

        //与常规方法相反，需要使用大顶堆，这样可以不停的弹出当前最大的数字，最终剩余4个最小的即结果
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);//大顶堆,将堆的大小保持在k

        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }

        for (int i = k; i < len; i++) {
            if(arr[i]<pq.peek()){//比最大的小，就可以把最大的扔掉，放入;比最大的还大，直接扔掉，不进行讨论
                pq.poll();
                pq.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            array[i] = pq.poll();
        }

        return array;

    }

}
