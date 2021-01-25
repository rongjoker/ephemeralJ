package org.rongjoker.ds;

import org.junit.Test;

/**
 * @date 01/25/2021
 * <p>
 * 200. 岛屿数量 https://leetcode-cn.com/problems/number-of-islands/
 */
public class NumberOfIslands200dfs {


    @Test
    public void test200() {

        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

        System.out.println(numIslands(grid));


    }


    public int numIslands(char[][] grid) {

        int max = 0;

        int nr = grid.length;
        int nc = grid[0].length;


        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] != '0') {
                    ++max;
                    dfs(grid, i, j, nr, nc);
                }

            }
        }


        return max;

    }

    /**
     * 深度优先,引入每个点
     *
     * @param grid
     * @param r
     * @param c
     */
    void dfs(char[][] grid, int r, int c, int row, int column) {

        if (r < 0 || c < 0 || r >= row || c >= column || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c, row, column);
        dfs(grid, r + 1, c, row, column);
        dfs(grid, r, c - 1, row, column);
        dfs(grid, r, c + 1, row, column);
    }


}
