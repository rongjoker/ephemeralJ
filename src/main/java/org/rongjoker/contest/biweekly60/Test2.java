package org.rongjoker.contest.biweekly60;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Test2 {


    @Test
    public void test2(){

//        System.out.println(Arrays.deepToString(findFarmland(new int[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 1}})));
//        System.out.println(Arrays.deepToString(findFarmland(new int[][]{{1,1},{1,1}})));
        System.out.println(Arrays.deepToString(findFarmland(new int[][]{{1,1},{0,0}})));
//        System.out.println(Arrays.deepToString(findFarmland(new int[][]{{0}})));



    }

    int[] cur;
    int width;
    int height;

    public int[][] findFarmland(int[][] land) {
        List<int[]> ans = new ArrayList<>();
        width = land.length;height=land[0].length;
        boolean[][] visited = new boolean[width][height];
        int[][] plus = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(visited[i][j])continue;
                cur = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};
                dfs(land,plus,visited,i,j);
                if(cur[0]!=Integer.MAX_VALUE)ans.add(cur);

            }
        }

        int len = ans.size();
        if(len==0)return new int[][]{};
        else {
            int[][] nums = new int[len][4];
            for (int i = 0; i < len; i++) nums[i] = ans.get(i);
            return nums;
        }

    }


    public void  dfs(int[][] land,int[][] plus,boolean[][] visited,int i,int j){
        if(i<0 || i>=width || j<0 || j>=height|| visited[i][j])return;

        visited[i][j] = true;

        if(land[i][j]==0)return;

        if(i<=cur[0] && j<=cur[1]){
            cur[0]=i;
            cur[1]=j;
        }

        if(i>=cur[2] && j>=cur[3]){
            cur[2]=i;
            cur[3]=j;
        }

        for (int[] ints : plus) {
            dfs(land,plus,visited,i+ints[0],j+ints[1]);
        }

    }


}
