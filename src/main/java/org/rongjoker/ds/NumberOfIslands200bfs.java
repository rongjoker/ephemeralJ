package org.rongjoker.ds;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @date 01/25/2021
 * <p>
 * 200. 岛屿数量 https://leetcode-cn.com/problems/number-of-islands/
 * bfs的方法解决，复杂度略高于dfs
 */
public class NumberOfIslands200bfs {


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
                    bfs(grid, i, j, nr, nc);
                }

            }
        }

        return max;

    }

    LinkedList<Integer> points = new LinkedList<>();

    /**
     * 广度优先,先找第一层（即第一个节点），再找第二层（即第一层连接的点），再找第三层（即第二层连接的点)
     * 用list实现队列来做，入队为一个点的下标【行*（列-1）】
     * 广度优化，会跳过非1的节点，故不可只计算右边和下边
     *
     * @param grid
     * @param r
     * @param c
     */
    void bfs(char[][] grid, int r, int c, int row, int column) {


        grid[r][c] = '0';
        points.add(r * column + c);

        while (!points.isEmpty()) {
            Integer integer = points.pollFirst();
            r = integer / column;
            c = integer % column;

            if (r - 1 >= 0 && grid[r - 1][c] != '0') {
                grid[r - 1][c] = '0';
                points.add((r - 1) * column + c);
            }

            if (r + 1 < row && grid[r + 1][c] != '0') {
                grid[r + 1][c] = '0';
                points.add((r + 1) * column + c);
            }

            if (c - 1 >= 0 && grid[r][c - 1] != '0') {
                grid[r][c - 1] = '0';
                points.add(r * column + c - 1);
            }

            if (c + 1 < column && grid[r][c + 1] != '0') {
                grid[r][c + 1] = '0';
                points.add(r * column + c + 1);
            }

        }


    }


}
