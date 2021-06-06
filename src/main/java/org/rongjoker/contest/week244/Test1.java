package org.rongjoker.contest.week244;

import org.junit.Test;

/**
 *
 * https://www.cnblogs.com/lolybj/p/9588059.html
 * 矩阵转换
 *
 *
 */
public class Test1 {

    @Test
    public void test1(){

        System.out.println(findRotation(new int[][]{{0,0},{0,1}},new int[][]{{0,0},{1,0}}));

    }


    public boolean findRotation(int[][] mat, int[][] target) {

        boolean flag = true;

        //0
        int n = mat.length;
        l1:for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j]!=target[i][j]){
                    flag = false;
                    break l1;
                }
            }
        }
        if(flag)return true;
        flag = true;
        l1:for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[j][n-i-1]!=target[i][j]){
                    flag = false;
                    break l1;
                }
            }
        }
        if(flag)return true;
        flag = true;
        l1:for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[n-i-1][n-j-1]!=target[i][j]){
                    flag = false;
                    break l1;
                }
            }
        }
        if(flag)return true;
        flag = true;
        l1:for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[n-j-1][i]!=target[i][j]){
                    flag = false;
                    break l1;
                }
            }
        }

        return flag;

    }
}
