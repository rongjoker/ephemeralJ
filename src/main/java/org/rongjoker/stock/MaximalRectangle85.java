package org.rongjoker.stock;

import org.junit.Test;

/**
 * @date 03/14/2021
 * 85. 最大矩形 https://leetcode-cn.com/problems/maximal-rectangle/
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 类似221. 最大正方形
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

        int m = matrix.length, n = matrix[0].length, max = 0;

        int [][][] dp = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n; j++) {

                if(matrix[i][j]=='1'){
                    if(i>0){
                        dp[i][j][0]=dp[i-1][j][0]+1;
                    }else dp[i][j][0]=1;

                    if(j>0){
                        dp[i][j][1]=dp[i][j-1][0]+1;
                    }else dp[i][j][1]=1;

                    if(i>0 && j>0 && matrix[i-1][j-1]>1){

                    }



                    dp[i][j][0]=1;
                    dp[i][j][1]=1;

                    max = Math.max(dp[i][j][0] * dp[i][j][1],max);




                }





            }



        }



        return max;

    }


}
