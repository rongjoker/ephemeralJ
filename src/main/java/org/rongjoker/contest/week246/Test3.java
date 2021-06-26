package org.rongjoker.contest.week246;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test3 {

    @Test
    public void test3() {

    }


    public int countSubIslands(int[][] grid1, int[][] grid2) {

        int[][] plus = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int m = grid1.length,n = grid1[0].length;
        List<Set<Integer>> gs1 = new ArrayList<>();
        List<Set<Integer>> gs2 = new ArrayList<>();

        boolean[][] visited_1 = new boolean[m][n];
        boolean[][] visited_2 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited_1[i][j] && grid1[i][j]==1){
                    Set<Integer> island = new HashSet<>();
                    int count = dfs(visited_1,grid1,plus,island,m,n,i,j);
                    if(count>0){
                        gs1.add(island);
                    }
                }

                if(!visited_2[i][j]&& grid2[i][j]==1){
                    Set<Integer> island = new HashSet<>();
                    int count = dfs(visited_2,grid2,plus,island,m,n,i,j);
                    if(count>0){
                        gs2.add(island);
                    }
                }
            }
        }

        int ans = 0;

        loop:for (Set<Integer> g2 : gs2) {
            for (Set<Integer> g1 : gs1) {
                int c1 = 0;
                for (int index1 : g2) {
                    if(!g1.contains(index1))break;
                    c1++;
                }
                if(c1==g2.size()){
                    ans++;
                    continue loop;
                }

            }



        }

        return ans;
    }

    public int dfs(boolean[][] visited,int[][] grid,int[][] plus,Set<Integer> island,int m,int n,int i,int j){
        if(i>=m ||j>=n || i<0 ||j<0 || visited[i][j])return 0;
        visited[i][j] = true;
        int count = 0;
        if(grid[i][j] ==1){
            island.add(i*1000 + j);
            count = 1;
            for (int[] ints : plus) {
                count += dfs(visited, grid, plus, island, m, n, i + ints[0], j + ints[1]);
            }
        }

        return count;
    }





}
