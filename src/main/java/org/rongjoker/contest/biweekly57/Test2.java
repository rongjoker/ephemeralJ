package org.rongjoker.contest.biweekly57;

import org.junit.Test;

import java.util.*;

public class Test2 {

    //[['+','.','+','+','+','+','+'},{'+','.','+','.','.','.','+'},{'+','.','+','.','+','.','+'},{'+','.','.','.','.','.','+'},{'+','+','+','+','.','+','.']]
    //[0,1]
    
    //[[33889,98676},{80071,89737},{44118,52565},{52992,84310},{78492,88209},{21695,67063},{84622,95452},{98048,98856},{98411,99433},{55333,56548},{65375,88566},{55011,62821},{48548,48656},{87396,94825},{55273,81868},{75629,91467]]
    //6

    @Test
    public void test2(){

//        System.out.println(smallestChair(new int[][]{{3,10},{1,5},{2,6}},0));
//        System.out.println(smallestChair(new int[][]{{1,10},{6,9},{2,6}},1));
        System.out.println(smallestChair(new int[][]{{33889,98676},{80071,89737},{44118,52565},{52992,84310},{78492,88209},{21695,67063},{84622,95452},{98048,98856},{98411,99433},{55333,56548},{65375,88566},{55011,62821},{48548,48656},{87396,94825},{55273,81868},{75629,91467}}
        ,6));

    }


    public int smallestChair(int[][] times, int targetFriend) {

        int tr = times[targetFriend][0];
        int count =0;
        Arrays.sort(times,(Comparator.comparingInt(o -> o[0])));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> remaining = new PriorityQueue<>();

        for(int[] t:times){

            int cur =-1;
            //这一步做错了，可能会有很多个一起过期，过期后的不需要按照过期时间排序
//            if(queue.isEmpty() || queue.peek()[0]>t[0]){
//                cur = count++;
//            }else cur = queue.poll()[1];
            while (!queue.isEmpty()&& queue.peek()[0]<=t[0]){
                remaining.offer(queue.poll()[1]);
            }
            if(!remaining.isEmpty())cur = remaining.poll();
            else cur = count++;

            if(t[0]==tr){
                return cur;
            }

            queue.add(new int[]{t[1],cur});
        }

        return count;

    }



}
