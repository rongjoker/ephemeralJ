package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 *
 * @date 08/06/2021
 *  542. 01 矩阵 https://leetcode-cn.com/problems/01-matrix/
 *
 *
 */
public class UpdateMatrix542 {


    @Test
    public void test542() {

        System.out.println(Arrays.deepToString(updateMatrix(new int[][]{

                {0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0}
        })));


    }


    public int[][] updateMatrix(int[][] mat) {

        boolean[][] visited;
        int[][] ans;
        int[][] plus;
        int m;
        int n;
        m = mat.length;
        n = mat[0].length;
        ans = new int[m][n];
        visited = new boolean[m][n];
        plus = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (visited[i][j]) continue;

                if (mat[i][j]==0){
                    ans[i][j]=0;
                    visited[i][j] = true;

                    for (int[] p : plus) {

                        int ti = i + p[0],tj = j + p[1];

                        if (ti < 0 || ti >= m || tj < 0 || tj >= n || visited[ti][tj]) continue;

                        if (mat[ti][tj] == 1) {
                            queue.addLast(new int[]{ti,tj});
                            visited[ti][tj] = true;
                        }
                    }

                }

            }
        }

        int base =0;
        while (!queue.isEmpty()){

            Deque<int[]> next = new ArrayDeque<>();

            while (!queue.isEmpty()){

                int[] last = queue.pollLast();

                ans[last[0]][last[1]] = base+1;

                for (int[] p : plus) {

                    int ti = last[0] + p[0],tj = last[1]+ p[1];

                    if (ti < 0 || ti >= m || tj < 0 || tj >= n || visited[ti][tj]) continue;

                    if (mat[ti][tj] == 1) {
                        next.addLast(new int[]{ti,tj});
                        visited[ti][tj] = true;
                    }
                }
            }

            queue = next;
            base++;

        }

        return ans;
    }



}
