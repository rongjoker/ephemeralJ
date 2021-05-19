package org.rongjoker.contest.week236;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @date 04/13/2021
 */
public class MKAverage {

    List<Integer> list = new ArrayList<>();

    int  space, del, mother;
    long sum = 0;

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);//大顶堆


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


    public MKAverage(int m, int k) {
        del = k;
        mother = -k * 2;
        space = m + mother;
    }

    long sumMin = 0,sumMax = 0,sumA = 0;

    public void addElement(int num) {
        list.add(num);
        sum+=num;
        ++mother;

        //小顶堆放最大的三个数字
        if (minHeap.size() < del) {
            minHeap.add(num);
            sumMin += num;
        } else {
            if (num > minHeap.peek()) {
                sumMin -= minHeap.poll();
                sumMin += num;
                minHeap.add(num);
            }
        }

        //大顶堆放最小的三个数字
        if (maxHeap.size() < del) {
            sumMax += num;
            maxHeap.add(num);
        } else {
            if (num < maxHeap.peek()) {
                sumMax -=maxHeap.poll();
                sumMin += num;
                maxHeap.add(num);
            }

        }


        sumA =  sum - sumMax - sumMin;
        System.out.println("sum:"+sumA);


    }

    public int calculateMKAverage() {
        if (mother < space) return -1;

        return (int) (sumA / mother);


    }
}
