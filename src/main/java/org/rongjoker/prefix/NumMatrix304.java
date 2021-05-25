package org.rongjoker.prefix;

/**
 * @date 04/22/2021
 * <p>
 * 304. 二维区域和检索 - 矩阵不可变 https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 * <p>
 * 用二维前缀和
 */
public class NumMatrix304 {

    int[][] prex;//二维

    public NumMatrix304(int[][] matrix) {
        int height = matrix.length, width = matrix[0].length;
        prex = new int[height + 1][width + 1];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                prex[i + 1][j + 1] = prex[i][j + 1] + prex[i + 1][j] - prex[i][j] + matrix[i][j];//多加了一次重叠的区域，剪掉
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prex[row2 + 1][col2 + 1] - prex[row1][col2 + 1] - prex[row2 + 1][col1] + prex[row1][col1];//多减了一次重叠的区域，加回来
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};

        NumMatrix304 numMatrix = new NumMatrix304(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));

    }
}
