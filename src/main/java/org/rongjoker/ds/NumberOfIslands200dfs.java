package org.rongjoker.ds;

import org.junit.Test;

/**
 * @date 01/25/2021
 * <p>
 * 200. 岛屿数量 https://leetcode-cn.com/problems/number-of-islands/
 * dfs的方式解决
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
     *      * 找第一个点，然后第一个点去找第二个点，一直找到最后一个点，然后倒回去找最后一个点的上一个点是否还有连接点，有就继续找，一直找到起点,
     *      * 即一个点一直找到尽头，再返回上一层继续找到尽头
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
