package org.rongjoker.bt;

import org.junit.Test;

import java.util.*;

/**
 * @date 03/25/2021
 * 51. N 皇后 https://leetcode-cn.com/problems/n-queens/
 * 回溯算法最经典+最难的题目
 */
public class Nqueens51 {

    List<List<String>> list = new ArrayList<>();

    Deque<String> deque = new ArrayDeque<>();
    int[] slope;
    int[] straight;


    @Test
    public void test51() {

        System.out.println(solveNQueens(4));

    }

    char[] cs;

    int[][] record;


    public List<List<String>> solveNQueens(int n) {

        if (n == 2 || n == 3)
            return list;

        cs = new char[n];

        for (int i = 0; i < n; i++) {
            cs[i] = '.';
        }

        record = new int[n+1][n+1];
        slope = new int[n+1];
        straight = new int[n+1];

        queen(n, 0);
        return list;

    }


    public void queen(int n, int index) {

        for (int start = index; start < n; ++start) {
            System.out.println("start:"+start+";index:"+index);
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (slope[i]==0 && straight[i]==0) {
                    flag = true;
                    cs[i] = 'Q';
                    deque.addLast(String.valueOf(cs));
                    cs[i] = '.';
                    if (start == n - 1) {
                        list.add(new ArrayList<>(deque));
                    }

                    slope[i]=-1;
                    for (int k = n; k >0; --k) {
                        if(slope[k-1]==-1){
                            slope[k-1]=0;
                            slope[k]=-1;
                        }
                    }
                    straight[i]=-1;

                    queen(n, start+1);

                    deque.removeLast();
                    //回溯
                    straight[i]=0;
                    for (int k = 0; k <n; ++k) {
                        if(slope[k+1]==-1){
                            slope[k+1]=0;
                            slope[k]=-1;
                        }
                    }
                    slope[i]=0;

                }
            }
            if(!flag)break;
        }


    }


}
