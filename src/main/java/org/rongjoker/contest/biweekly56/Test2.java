package org.rongjoker.contest.biweekly56;

import org.junit.Test;

public class Test2 {

    //[['+','.','+','+','+','+','+'},{'+','.','+','.','.','.','+'},{'+','.','+','.','+','.','+'},{'+','.','.','.','.','.','+'},{'+','+','+','+','.','+','.']]
    //[0,1]

    @Test
    public void test2(){

        System.out.println(nearestExit(new char[][]{{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}},new int[]{1,2}));
//        System.out.println(nearestExit(new char[][]{
//                {'+','.','+','+','+','+','+'},
//                {'+','.','+','.','.','.','+'},
//                {'+','.','+','.','+','.','+'},
//                {'+','.','.','.','.','.','+'},
//                {'+','+','+','+','.','+','.'}},new int[]{0,1}));

    }


    public int nearestExit(char[][] maze, int[] entrance) {

        int m= maze.length,n = maze[0].length;
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        int[][] plus = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        track(entrance,maze,plus,visited,m,n,entrance[0],entrance[1],0);

        return ans==Integer.MAX_VALUE?-1:ans;

    }

    int ans = Integer.MAX_VALUE;


    public void track(int[] entrance,char[][] maze,int[][] plus,int[][] visited,int m,int n,int left,int right,int step){
        if(left==m || right==n || right<0 ||left<0  || maze[left][right] =='+' || visited[left][right]<=step)return;
        visited[left][right] = step;
        if((left==m-1 || right==n-1 || left==0 || right==0) && maze[left][right] =='.' && !(left==entrance[0] && right==entrance[1])){
            ans =Math.min(ans,step);
            return;
        }

        for (int[] p : plus) {
            track(entrance,maze,plus,visited,m,n,left+p[0],right+p[1],step+1);
        }

    }


}
