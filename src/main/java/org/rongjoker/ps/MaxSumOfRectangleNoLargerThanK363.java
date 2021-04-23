package org.rongjoker.ps;

/**
 *  @date 04/22/2021
 *  363. 矩形区域不超过 K 的最大数值和 https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
 *
 *  二维前缀和暴力求解
 *  可以用二分优化
 *
 *
 */
public class MaxSumOfRectangleNoLargerThanK363 {

    int[][] pres;

    public int maxSumSubmatrix(int[][] matrix, int k) {

        int height = matrix.length,width = matrix[0].length;
        pres = new int[height+1][width+1];
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                pres[i+1][j+1] = pres[i][j+1] + pres[i+1][j] - pres[i][j] + matrix[i][j];
            }
        }
        int max = Integer.MIN_VALUE;
        int temp;
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                for(int m=0;m<=i;++m){
                    for(int n=0;n<=j;++n){
                        temp = pres[i+1][j+1] - pres[i+1][n] - pres[m][j+1] + pres[m][n];
                        if(temp <=k)
                            max = Math.max(max,temp);
                    }
                }
            }
        }
        return max;
    }
}
