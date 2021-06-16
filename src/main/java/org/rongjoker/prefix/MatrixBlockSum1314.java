package org.rongjoker.prefix;


import org.junit.Test;

import java.util.Arrays;

/**
 * 1314. 矩阵区域和 https://leetcode-cn.com/problems/matrix-block-sum/
 *
 * 二维前缀和
 */
public class MatrixBlockSum1314 {

    @Test
    public void test1314(){

        System.out.println(Arrays.deepToString(matrixBlockSum(new int[][]{{67, 64, 78}, {99, 98, 38}, {82, 46, 46}, {6, 52, 55}, {55, 99, 45}}, 3)));

    }


    public int[][] matrixBlockSum(int[][] matrix, int k) {

        int height = matrix.length, width = matrix[0].length;
        int[][] prex = new int[height + 1][width + 1];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                prex[i + 1][j + 1] = prex[i][j + 1] + prex[i + 1][j] - prex[i][j] + matrix[i][j];//多加了一次重叠的区域，剪掉
            }
        }

        int[][] ans = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r1 = Math.max(0, i - k), c1 = Math.max(0, j - k);
                int r2 = Math.min( height-1, i + k), c2 = Math.min(width-1, j + k);
                ans[i][j] = prex[r2 + 1][c2 + 1] - prex[r1][c2 + 1] - prex[r2 + 1][c1] + prex[r1][c1];;//多减了一次重叠的区域，加回来
            }
        }

        return ans;


    }
}
