package org.rongjoker.stack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 03/14/2021
 * 84. 柱状图中最大的矩形 https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 类似85. 最大矩形
 *
 *
 *
 */
public class LargestRectangleArea84 {


    @Test
    public void test84(){

        int[] heights = {2,1,2};
        System.out.println(largestRectangleArea(heights));


    }





    public int largestRectangleArea(int[] heights) {

        if(heights.length==1)
            return heights[0];

        int len = heights.length,max=0;

        int[] cs = new int[len+2];//添加哨兵
        cs[0]=-1;
        cs[len+1]=-1;
        for (int i = 0; i < len; i++) {
            cs[i+1]=heights[i];
        }
        len = cs.length;

        Deque<Integer> path = new ArrayDeque<>();
        path.add(0);

        for (int i = 1; i < len; i++) {
            while (cs[path.peekLast()]>cs[i]){
                //i点为右侧,取出最高点后，path.getLast()为左侧
                //(左右都比当前点要小，所以以当前点为高度的面积的宽度为右侧-左侧)
                max=Math.max(max,cs[path.pollLast()]*(i-path.peekLast()-1));

            }
            path.addLast(i);

        }

        return max;

    }



}
