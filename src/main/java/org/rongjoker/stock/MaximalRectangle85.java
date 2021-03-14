package org.rongjoker.stock;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 03/14/2021
 * 85. 最大矩形 https://leetcode-cn.com/problems/maximal-rectangle/
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 类似 84. 柱状图中最大的矩形
 * 可以看作有rows x cols个圆柱，也就是把84重复cols次
 *
 *
 *
 */
public class MaximalRectangle85 {


    @Test
    public void test85(){

        char[][] matrix = {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'},{'1','0','0','1','0'}};

        System.out.println(maximalRectangle(matrix));
    }


    public int maximalRectangle(char[][] matrix) {


        //控制边界条件
        int m = matrix.length;
        if(m==0)
            return 0;
        len = matrix[0].length;
        if(len==0)
            return 0;
        extend_len=len+2;

        cs = new int[extend_len];//添加哨兵
        cs[0]=-1;
        cs[len +1]=-1;

        for (int i = 0; i < m; i++) {
            largestRectangleArea(matrix,i);
        }

        return max;

    }


    int[] cs;
    int max=0, len,extend_len;
    Deque<Integer> path = new ArrayDeque<>();


    public void largestRectangleArea(char[][] matrix,int col) {

        for (int i = 0; i < len; i++) {
            if(matrix[col][i]=='0')
                cs[i+1]=0;
            else ++cs[i+1];
        }

        path.add(0);

        for (int i = 1; i < extend_len; i++) {
            while (cs[path.peekLast()]>cs[i]){
                //i点为右侧,取出最高点后，path.getLast()为左侧
                max=Math.max(max,cs[path.pollLast()]*(i-path.peekLast()-1));//(左右都比当前点要小，所以以当前点为高度的面积的宽度为右侧-左侧)
            }
            path.addLast(i);

        }


    }





}
