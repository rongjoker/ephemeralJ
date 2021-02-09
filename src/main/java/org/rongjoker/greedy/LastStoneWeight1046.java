package org.rongjoker.greedy;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @date 02/09/2021
 * 1046. 最后一块石头的重量 https://leetcode-cn.com/problems/last-stone-weight/
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎；最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0
 * 本质是排序，勉强算贪心，可以利用大顶堆来处理
 *
 */
public class LastStoneWeight1046 {


    @Test
    public void test1046(){
        int[] nums = {2,7,4,1,8,1};
        System.out.println(lastStoneWeight(nums));




    }

    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i : stones)
            pq.offer(i);
        while (pq.size() >= 2) {

            int x = pq.poll();
            int y = pq.poll();
            if (x > y)
                pq.offer(x - y);
        }
        return pq.size() == 1 ? pq.peek() : 0;

    }





}
