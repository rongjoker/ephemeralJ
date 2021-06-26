package org.rongjoker.contest.week246;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test3_2 {

    @Test
    public void test3() {

    }


    public int countSubIslands(int[][] grid1, int[][] grid2) {

        int[][] plus = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int m = grid1.length,n = grid1[0].length;
        boolean[][] visited_2 = new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            loop:for (int j = 0; j < n; j++) {

                if(!visited_2[i][j]&& grid2[i][j]==1){
                    List<int[]> island = new ArrayList<>();
                    int count = dfs(visited_2,grid2,plus,island,m,n,i,j);
                    if(count>0){
                        for (int[] ints : island) {
                            if(grid1[ints[0]][ints[1]]==0){
                                continue loop;
                            }
                        }
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    public int dfs(boolean[][] visited,int[][] grid,int[][] plus,List<int[]> island,int m,int n,int i,int j){
        if(i>=m ||j>=n || i<0 ||j<0 || visited[i][j])return 0;
        visited[i][j] = true;
        int count = 0;
        if(grid[i][j] ==1){
            island.add(new int[]{i,j});
            count = 1;
            for (int[] ints : plus) {
                count += dfs(visited, grid, plus, island, m, n, i + ints[0], j + ints[1]);
            }
        }

        return count;
    }





}
