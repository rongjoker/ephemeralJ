package org.rongjoker.contest.week253;

import org.junit.Test;

import java.util.PriorityQueue;

public class Test2 {



    @Test
    public void test2(){

        System.out.println(minStoneSum(new int[]{5,4,9},2));
        System.out.println(minStoneSum(new int[]{4,3,6,7},3));

    }


    public int minStoneSum(int[] piles, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        int sum = 0;
        for (int pile : piles) {
            sum+=pile;
            queue.add(pile);
        }

        for(int i=0;i<k;++i){
            Integer poll = queue.poll();
            int floor = poll/2;

            sum-=floor;
            queue.add(poll - floor);
        }

        return sum;

    }



}
