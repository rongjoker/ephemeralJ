package org.rongjoker.dp.target;


import org.junit.Test;

/**
 * @date 03/08/2021
 * 221. 最大正方形 https://leetcode-cn.com/problems/maximal-square/
 * <p>
 * dp应用题，统计正方形，一个点能否组成更大的正方形，由它与上下左的三个点的形态决定，3个点有任意一个点不是1，则无法组成
 * 左和上靠边的点，则最多只能组成1个
 * 以上为递推关系式
 * 只有周围都大于0才有计算的意义,变相的进行剪枝
 * 和1277本质是同一个题目，递推变成边长,可以优化空间
 */
public class MaximalSquare221 {

    @Test
    public void test221(){

        char[][] matrix = {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'},{'1','0','0','1','0'}};

        System.out.println(maximalSquare(matrix));


    }


    public int maximalSquare(char[][] matrix) {

        int m = matrix.length, n = matrix[0].length, max = 0;

        int [][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if(matrix[i][j] == '1'){
                    dp[i][j] = 1;

                    if (i > 0 && j > 0 && matrix[i-1][j] == '1' && matrix[i-1][j-1] == '1' && matrix[i][j-1] == '1') {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }

                    max = Math.max(max,dp[i][j]);
                }

            }
        }

        return max*max;

    }




}
