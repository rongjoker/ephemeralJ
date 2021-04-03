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
public class SearchMatrix240 {

    @Test
    public void test240(){

        int[][] matrix = {{1,4,7,11,15}, {2,5,8,12,19}, {3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(searchMatrix(matrix,30));

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int left = 0,right=n-1,middle,temp;
        for (int i = 0; i < m; i++) {
            while(right>=left){//第一，利用>=可以在循环内部判断完所有情况,
                middle =left + (( right-left)>>1);//第一，这种取中间值最稳妥
                temp = matrix[i][middle];
                if(temp == target) return true;
                else if(temp>target)right = middle-1;
                else left = middle+1;
            }
            left = 0;right=n-1;
        }

        return false;

    }
}
