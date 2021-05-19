package org.rongjoker.stack;


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 05/18/2021
 * 1856. 子数组最小乘积的最大值 https://leetcode-cn.com/problems/maximum-subarray-min-product/
 * dp + 两次循环 先利用dp求出区间最小值，然后依次进行模拟运算即可，此种方法有暴力的成本，会超时
 * 利用单调栈来做，类似84题，实际上更难，虽然这个是medium，84是hard
 * 前缀和要用long避免溢出
 */
public class MaximumSubarrayMinProduct1856 {

    @Test
    public void test1856() {
        System.out.println(maxSumMinProductDeque(new int[]{1,2,3,2}));
//        System.out.println(maxSumMinProduct(new int[]{1,2,3,2}));
//        System.out.println("---");
//        System.out.println(maxSumMinProductDeque(new int[]{3, 1, 5, 6, 4, 2}));
//        System.out.println(maxSumMinProduct(new int[]{3, 1, 5, 6, 4, 2}));
//        System.out.println("---");
//        System.out.println(maxSumMinProductDeque(new int[]{2, 3, 3, 1, 2}));
//        System.out.println(maxSumMinProduct(new int[]{2, 3, 3, 1, 2}));
//        System.out.println("---");
//        System.out.println(maxSumMinProductDeque(new int[]{2, 5, 4, 2, 4, 5, 3, 1, 2, 4}));
//        System.out.println(maxSumMinProduct(new int[]{2, 5, 4, 2, 4, 5, 3, 1, 2, 4}));
//        System.out.println("---");
//        System.out.println(maxSumMinProduct(new int[]{1, 1, 3, 2, 2, 2, 1, 5, 1, 5}));
//        System.out.println(maxSumMinProductDeque(new int[]{1, 1, 3, 2, 2, 2, 1, 5, 1, 5}));

    }


    /**
     * 这个方法太暴力,超时，注意数组会非常大，前缀和要用long
     *
     * @param nums
     * @return
     */
    public int maxSumMinProduct(int[] nums) {
        int len = nums.length;
        long ans = 0L, sum;
        int min;
        for (int i = 0; i < len; ++i) {
            sum = nums[i];
            ans = Math.max(sum * sum, ans);
            min = nums[i];
            for (int j = i + 1; j < len; ++j) {
                sum += nums[j];
                min = Math.min(min, nums[j]);
                ans = Math.max(sum * min, ans);
            }
        }

        return (int) (ans % 1000000007);

    }


    /**
     * 单调栈来解决这个问题，注意细节
     * @param nums
     * @return
     */
    public int maxSumMinProductDeque(int[] nums) {
        int len = nums.length;
        long[] prex = new long[len + 2];
        long[] highs = new long[len + 2];
        highs[0] = 0;
        highs[len + 1] = 0;
        prex[1] = 0;
        for (int i = 0; i < len; i++) {
            highs[i + 1] = nums[i];
            prex[i + 2] = nums[i] + prex[i + 1];
        }

        long ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        for (int i = 0; i < len + 2; ++i) {//注意，这里是+2，不是+1
            while (!stack.isEmpty() && highs[stack.peekLast()] > highs[i]) {
                System.out.println();
                ans = Math.max(highs[stack.pollLast()] * (prex[i] - prex[stack.peekLast() + 1]), ans);

            }
            stack.addLast(i);

        }

        return (int) (ans % 1000000007);

    }

}



