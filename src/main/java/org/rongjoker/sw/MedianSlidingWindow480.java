package org.rongjoker.sw;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @date 06/11/2021
 * 480. 滑动窗口中位数 https://leetcode-cn.com/problems/sliding-window-median/
 *
 * 239.滑动窗口的最大值 https://leetcode-cn.com/problems/sliding-window-maximum/
 * 进阶题目
 * 注意重写比较器会出现负负得正的越界情况
 *
 */
public class MedianSlidingWindow480 {

    @Test
    public void test480() {
//        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{0, 1, 2, 3, 4}, 3)));
//        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
//        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,4,2,3}, 4)));
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,2,3,4}, 4)));
//        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{2147483647,2147483647}, 2)));
//        double[] slidingWindow = medianSlidingWindow(new int[]{-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648}, 3);
//
//        double[] r = new double[]{-2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, -2147483648.00000};
//        for (int i = 0; i < slidingWindow.length; i++) {
//            System.out.println(i + 1);
//            System.out.println((slidingWindow[i] == r[i]) + ":" + slidingWindow[i] + ":" + r[i]);
//        }


    }

    //[-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648]
    //3

    public double[] medianSlidingWindow(int[] nums, int k) {


        int len = nums.length;

        double[] ans = new double[len - k + 1];

        if (k == 1) {
            for (int i = 0; i < len; i++) ans[i] = nums[i];
            return ans;

        }


        boolean odd = k % 2 == 1;

//        double[] r = new double[]{-2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, -2147483648.00000};

        //中位数在两个堆的堆顶
        PriorityQueue<Integer> min_heap = new PriorityQueue<>((Comparator.comparingInt(o -> nums[o])));//默认小顶堆，最小的在最上面,放大的那一半数据
        PriorityQueue<Integer> max_heap = new PriorityQueue<>(((o1, o2) ->  {
            if(nums[o2] > nums[o1])return 1;
            else if(nums[o2] < nums[o1])return -1;
            else return 0;
        }));//大顶堆，最大的在最上面(最大的要小于小顶堆里最小的),放小的那一半数据

        for (int i = 0; i < len; i++) {

            if (i > k - 1) {
                if (!min_heap.remove(i - k)) max_heap.remove(i - k);
            }

            if (min_heap.size() < max_heap.size()) {
                if (nums[i] < nums[max_heap.peek()]) {
                    min_heap.offer(max_heap.poll());
                    max_heap.offer(i);
                } else min_heap.offer(i);
            } else {
                if (min_heap.size() > 0 && nums[i] > nums[min_heap.peek()]) {
                    max_heap.offer(min_heap.poll());
                    min_heap.offer(i);
                } else max_heap.offer(i);
            }



            if (i >= k - 1) {
                if (odd) ans[i - k + 1] = nums[max_heap.peek()];
                else {
                    ans[i - k + 1] = ((long) nums[max_heap.peek()] + (long) nums[min_heap.peek()]) / 2.0;
                    System.out.println("nums[max_heap.peek()]:" + nums[max_heap.peek()]);
                    System.out.println("nums[min_heap.peek()]:" + nums[min_heap.peek()]);
                }

//                if(ans[i - k + 1] != r[i - k + 1]) {
//                    System.out.println(max_heap.peek());
//                    System.out.println(r[i - k + 1]);
//                    System.out.println(r[i - k + 1]);
//                }



            }


        }


        return ans;

    }
}
