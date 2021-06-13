package org.rongjoker.contest.biweekly54;

public class Test3 {


    public int largestMagicSquare(int[][] grid) {


        int h = grid.length,w = grid[0].length;

        int ans = Math.min(h,w);

        int[][] sum_w = new int[h][w+1];//有h行
        int[][] sum_h = new int[w][h+1];//有w列

        for(int i=0;i<h;++i){
            for (int j = 0; j < w; ++j) {
                sum_w[i][j+1] = sum_w[i][j] + grid[i][j];
                sum_h[j][i+1] = sum_w[j][i] + grid[i][j];
            }
        }

        while (ans>1){
            loop:for (int i = 0; i <= h - ans; i++) {
                for (int j = 0; j <= w - ans; j++) {



                }

            }



        }

        return ans;








    }
}
