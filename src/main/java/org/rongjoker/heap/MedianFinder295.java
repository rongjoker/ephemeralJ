package org.rongjoker.heap;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @date 02/18/2021
 * 295. 数据流的中位数 https://leetcode-cn.com/problems/find-median-from-data-stream/
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 本质上是top k的升级版本，所谓的双堆问题
 *
 *
 */
public class MedianFinder295 {

    @Test
    public void test295() {

        addNum(1);
        addNum(2);
//        System.out.println(findMedian());
        addNum(3);
        System.out.println(findMedian());
//        addNum(1);
//        addNum(1);
//        addNum(1);
//        addNum(1);
//        System.out.println(findMedian());


    }



    PriorityQueue<Integer> min = new PriorityQueue<>();//小顶堆，最上面的就是中位数
    PriorityQueue<Integer> max = new PriorityQueue<>((i1, i2) -> i2 - i1);//大顶堆，最上面的也是中位数




    public void addNum(int num) {

        if(min.size()<max.size()){//添加到小顶堆
            if(num< max.peek() ){//大顶堆存放的是较小的那一半的数据，所以凡是小与大顶堆最大值的数据都应该放到大顶堆
                min.offer(max.poll());
                max.offer(num);
            }else
                min.offer(num);

        }else {//添加到大顶堆
            if(min.size()>0 &&  num> min.peek() ){//与上面的逻辑相反
                max.offer(min.poll());
                min.offer(num);
            }else
                max.offer(num);
        }


    }

    public double findMedian() {

        if(min.size()<max.size())
            return max.peek();

        return (double )(min.peek() +max.peek())/2;

    }


}
