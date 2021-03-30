package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 03/30/2021
 * 74. 搜索二维矩阵 https://leetcode-cn.com/problems/search-a-2d-matrix/
 *  3月30日每日一题
 *  在矩阵里二分查找需要注意的点
 *
 *
 */
public class SearchMatrix74 {

    @Test
    public void test74(){

        int[][] matrix = {{1, 3, 5,7}, {10, 11, 16,20}, {23, 30, 34,60}};
        System.out.println(searchMatrix(matrix,60));

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length,len=m*n;
        int left = 0,right=len-1,middle,temp;
        while(right>=left){//第一，利用>=可以在循环内部判断完所有情况,
            middle =left + (( right-left)>>1);//第一，这种取中间值最稳妥
            temp = matrix[middle/n][middle%n];//第一，计算行列的时候，不需要middle/n -1,这是除法的优势,除数天然为0(比如4/5=0)
            if(temp == target) return true;
            else if(temp>target)right = middle-1;//第一，left/right赋值要middle-1或者middle+1,而不是right=middle直接赋值
            else left = middle+1;
        }
        return false;

    }
}
