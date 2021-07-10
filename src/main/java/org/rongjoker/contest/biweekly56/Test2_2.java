package org.rongjoker.contest.biweekly56;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Test2_2 {

    //[['+','.','+','+','+','+','+'},{'+','.','+','.','.','.','+'},{'+','.','+','.','+','.','+'},{'+','.','.','.','.','.','+'},{'+','+','+','+','.','+','.']]
    //[0,1]

    //[['+','.','+','+','+','+','+'},{'+','.','+','.','.','.','+'},{'+','.','+','.','+','.','+'},{'+','.','.','.','+','.','+'},{'+','+','+','+','+','+','.']]
    //[0,1]

    @Test
    public void test2(){

        System.out.println(nearestExit(new char[][]{{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}},new int[]{1,2}));
        System.out.println(nearestExit(new char[][]{
                {'+','.','+','+','+','+','+'},{'+','.','+','.','.','.','+'},{'+','.','+','.','+','.','+'},{'+','.','.','.','+','.','+'},{'+','+','+','+','+','+','.'}},new int[]{0,1}));

//        System.out.println(nearestExit(new char[][]{{}},new int[]{0,1}));

    }


    public int nearestExit(char[][] maze, int[] entrance) {

        int m= maze.length,n = maze[0].length;

        int[][] plus = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        Set<int[]> cur = new HashSet<>();
        Set<String> total = new HashSet<>();
        cur.add(entrance);
        total.add(entrance[0]+"_"+entrance[1]);

        int ans = 0;
        while (!cur.isEmpty()){

            Set<int[]> next = new HashSet<>();
            for (int[] index : cur) {
                int left = index[0],right = index[1];
                if(left==m || right==n || right<0 ||left<0  || maze[left][right] =='+')continue;
                if((left==m-1 || right==n-1 || left==0 || right==0) && maze[left][right] =='.' && !(left==entrance[0] && right==entrance[1])){
                    return ans;
                }
                for (int[] p : plus) {

                    int[] temp = new int[]{index[0] +p[0],index[1]+p[1]};
                    String cc = temp[0]+"_"+temp[1];
                    if(!total.contains(cc)) {
                        next.add(temp);
                        total.add(cc);
                    }

                }
            }
            cur = next;
            ans++;

        }




        return -1;

    }





}
