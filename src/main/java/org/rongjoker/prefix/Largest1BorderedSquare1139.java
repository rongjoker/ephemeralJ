package org.rongjoker.prefix;

import org.junit.Test;

/**
 * @date 06/25/2021
 * 1139. 最大的以 1 为边界的正方形 https://leetcode-cn.com/problems/largest-1-bordered-square/
 *
 *
 */
public class Largest1BorderedSquare1139 {


    @Test
    public void test1139(){

    }


    public int largest1BorderedSquare(int[][] grid) {
        int w = grid.length,h = grid[0].length;
        int[][] prefix = new int[w+1][h+1];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                prefix[i+1][j+1] = prefix[i+1][j] + prefix[i][j+1] - prefix[i][j] + grid[i][j];
            }
        }

        int max = 0;


        return max;






    }



}
