package org.rongjoker.contest.week236;

import java.util.*;

/**
 * @date 04/13/2021
 *
 * @date 05/19/2021
 *
 * 1825. 求出 MK 平均值 https://leetcode-cn.com/problems/finding-mk-average/
 *
 *
 */
public class MKAverage {

    public static void main(String[] args) {
        MKAverage mkAverage = new MKAverage(3, 1);
        mkAverage.addElement(3);
        mkAverage.addElement(1);
        System.out.println(mkAverage.calculateMKAverage());
        mkAverage.addElement(10);
        System.out.println(mkAverage.calculateMKAverage());
        mkAverage.addElement(5);
        mkAverage.addElement(5);
        mkAverage.addElement(5);
        System.out.println(mkAverage.calculateMKAverage());


    }

    int  space, del, mother;
    long sum = 0;

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);//大顶堆

    PriorityQueue<Integer> queue = new PriorityQueue<>();//中间的数据


    public MKAverage(int m, int k) {
        del = k;
        mother = m - k * 2;
        space = m;
        maxs = new int[del];mins = new int[del];
    }

    public void addElement(int num) {

        Integer temp = null;

        if(maxHeap.size()<del || minHeap.size()<del){
            if(maxHeap.size()<del)maxHeap.add(num);
            if(minHeap.size()<del)minHeap.add(num);
        }else {

            if(num < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.add(num);
            }else if(num > minHeap.peek()){
                minHeap.poll();
                minHeap.add(num);
            }else {
                if(queue.size()<mother)
                    queue.add(num);
                else queue.add(num);

            }


        }



        if( minHeap.size() + maxHeap.size() +queue.size() < space){//直接添加
            if(maxHeap.size()<del)maxHeap.add(num);
            else {
                if(num < maxHeap.peek()){
                    maxHeap.poll();
                    maxHeap.add(num);
                }

            }

            if(minHeap.size()<del)minHeap.add(num);
            else {
                if(num > minHeap.peek()){
                    minHeap.poll();
                    minHeap.add(num);
                }

            }


        }


        sum+=num;




    }

    int [] maxs ,mins;

    public int calculateMKAverage() {
        long tempS = sum;
        if (queue.size() < space) return -1;
        for (int i = 0; i < del; i++) {
            maxs[i] = maxHeap.poll();
            mins[i] = minHeap.poll();
            tempS -= maxs[i];
            tempS -= mins[i];
        }

        for (int i = del - 1 ; i >=0; --i) {
            maxHeap.offer(maxs[i]);
            minHeap.offer(mins[i]);
        }


        return (int) (tempS / mother);

    }
}
