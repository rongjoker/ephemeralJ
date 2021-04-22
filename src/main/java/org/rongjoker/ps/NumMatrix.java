package org.rongjoker.ps;

/**
 *  @date 04/22/2021
 *
 *  304. 二维区域和检索 - 矩阵不可变 https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 *
 *  可以用303的方法，求每一行的前缀和，然后多个row累加得结果
 *  也可以用二维前缀和
 *
 */
public class NumMatrix {

    int[][] prex;//多层一维，本质还是一维

    public NumMatrix(int[][] matrix) {
        int height = matrix.length,width = matrix[0].length;
        prex = new int[height][ width +1];
        int pre;
        for (int i = 0; i < height; i++) {
            pre = 0;
            for (int j = 0; j < width; j++) {
                pre += matrix[i][j];
                prex[i][ j +1] = pre;
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += (prex[i][col2+1] - prex[i][col1]);
        }

        return sum;

    }

    public static void main(String[] args) {
        int[][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};

        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2,1,4,3));
        System.out.println(numMatrix.sumRegion(1,1,2,2));
        System.out.println(numMatrix.sumRegion(1,2,2,4));

    }
}
