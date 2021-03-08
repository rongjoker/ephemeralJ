package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 03/08/2021
 * 1277. 统计全为 1 的正方形子矩阵 https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/
 * <p>
 * dp应用题，统计正方形，一个点能否组成更大的正方形，由它与上下左的三个点的形态决定，3个点有任意一个点不是1，则无法组成
 * 左和上靠边的点，则最多只能组成1个
 * 以上为递推关系式
 * 只有周围都大于0才有计算的意义,变相的进行剪枝
 */
public class CountSquares1277 {


    @Test
    public void test1277() {

        int[][] matrix = {{1,0,1}, {1,1,0}, {1,1,0}};

        System.out.println(countSquares(matrix));

    }

    public int countSquares(int[][] matrix) {

        if (matrix.length == 0)
            return 0;

        int m = matrix.length, n = matrix[0].length, sum = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //这里添加判断优化一下性能，只有周围都大于0才有计算的意义,变相的进行剪枝
                if (i > 0 && j > 0 && matrix[i][j] == 1 && matrix[i-1][j] != 0 && matrix[i-1][j-1] != 0 && matrix[i][j-1] != 0) {
                    matrix[i][j] = Math.min(Math.min(matrix[i - 1][j], matrix[i][j - 1]), matrix[i - 1][j - 1]) + 1;
                }
                sum += matrix[i][j];

            }
        }

        return sum;

    }
}
