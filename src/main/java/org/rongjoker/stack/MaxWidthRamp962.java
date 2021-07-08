package org.rongjoker.stack;

import org.junit.Test;

import java.util.*;

/**
 * @date 07/08/2021
 * 962. 最大宽度坡 https://leetcode-cn.com/problems/maximum-width-ramp/
 */
public class MaxWidthRamp962 {


    @Test
    public void test962() {
        System.out.println(maxWidthRamp3(new int[]{6, 0, 8, 2, 1, 5}));
        System.out.println(maxWidthRamp3(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}));
    }


    /**
     * 优化栈
     *
     * @param nums
     * @return
     */
    public int maxWidthRamp3(int[] nums) {
        int len = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < len; i++) if (stack.isEmpty() || nums[stack.peekLast()] > nums[i]) stack.addLast(i);

        for (int i = len - 1; i >= 0; --i) {
            int cur = nums[i];
            while (!stack.isEmpty() && nums[stack.peekLast()] <= cur) ans = Math.max(ans, i - stack.pollLast());
            if (stack.isEmpty()) break;
        }
        return ans;
    }


    /**
     * 用单调栈+栈内查询来解决
     *
     * @param nums
     * @return
     */
    public int maxWidthRamp2(int[] nums) {
        int len = nums.length;
        List<Integer> stack = new ArrayList<>();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            int last = stack.size();
            for (int j = last - 1; j >= 0; --j) {
                if (nums[stack.get(j)] <= cur) {
                    ans = Math.max(ans, i - stack.get(j));
                } else break;
            }
            if (stack.isEmpty() || nums[stack.get(stack.size() - 1)] > cur) stack.add(i);

        }
        return ans;
    }


    //hash超时
    public int maxWidthRamp(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            for (int k : count.keySet()) {
                if (k <= cur) {
                    ans = Math.max(ans, i - count.get(k));

                }
            }
            if (!count.containsKey(cur)) count.put(cur, i);
        }
        return ans;
    }


}
