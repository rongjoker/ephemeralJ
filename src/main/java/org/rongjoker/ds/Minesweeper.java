package org.rongjoker.ds;

import org.junit.Test;

/**
 * 529. 扫雷游戏 https://leetcode-cn.com/problems/minesweeper/
 * 需要递归出四周八个的数字，而不是直接遍历
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
 *
 */
public class Minesweeper {


    @Test
    public void test529() {

        int[] click = {3, 0};

        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};

        updateBoard(board, click);

        System.out.println(board[1][2]);


    }


    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        //单独处理点击***
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }

    public void dfs(char[][] board, int i, int j) {
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
        if(board[i][j] == 'E') board[i][j] = 'B';
        else return;    //如果不是E那就是数字，说明已经访问过了，直接返回
        int M = 0;
        //计算周围***的数量
        for(int k = 0; k < 8; ++k){
            int ni = i + dirs[k][0], nj = j + dirs[k][1];
            if(ni < 0 || nj < 0 || ni >= board.length || nj >= board[0].length) continue;
            if(board[ni][nj] == 'M') ++M;
        }
        //有***就更新字符，然后返回，因为有***就不能继续翻E了
        if(M > 0) {
            board[i][j] = (char)(M + '0');
            return;
        }
        //周围没有***，往八个方向翻E
        for(int k = 0; k < 8; ++k){
            dfs(board, i + dirs[k][0], j + dirs[k][1]);
        }
    }
}
