package org.rongjoker.bt;

import org.junit.Test;

import java.util.*;

/**
 * @date 03/25/2021
 * 51. N 皇后 https://leetcode-cn.com/problems/n-queens/
 * 回溯算法最经典+最难的题目
 * 关键是剪枝
 * 比如进入第三行，而队列里不够2（说明前2行没满皇后）则跳出
 * 做完这个题目，体验到解题的开心
 */
public class Nqueens51 {




    @Test
    public void test51() {

        System.out.println(solveNQueens(4));

    }


    List<List<String>> list = new ArrayList<>();

    Deque<String> deque = new ArrayDeque<>();

    int[] straight;//直线

    char[] cs;

    int[][] record_left,record_right;//左斜线右斜线


    public List<List<String>> solveNQueens(int n) {

        if (n == 2 || n == 3)
            return list;

        cs = new char[n];

        for (int i = 0; i < n; i++) {
            cs[i] = '.';
        }

        record_left = new int[n+1][n+1];
        record_right = new int[n+1][n+1];
        straight = new int[n+1];

        queen(n, 0);
        return list;

    }

    int left,right;


    public void queen(int n, int index) {

        for (int start = index; start < n; ++start) {

            if(deque.size()<start)break;//关键的一步

            for (int i = 0; i < n; i++) {
                if (record_left[start][i]==0 && record_right[start][i]==0 && straight[i]==0) {

                    cs[i] = 'Q';
                    deque.addLast(String.valueOf(cs));
                    cs[i] = '.';
                    if (start == n - 1) {
                        list.add(new ArrayList<>(deque));
                    }

                    left=right=i;
                    straight[i]=-1;
                    for (int j = start+1; j < n; j++) {
                        left--;right++;
                        if(left>=0)
                            record_left[j][left] = -1;

                        if(right<n)
                            record_right[j][right] = -1;
                    }


                    queen(n, start+1);

                    deque.removeLast();
                    //回溯
                    left=right=i;
                    straight[i]=0;
                    for (int j = start+1; j < n; j++) {

                        left--;right++;
                        if(left>=0)
                            record_left[j][left] = 0;

                        if(right<n)
                            record_right[j][right] = 0;
                    }

                }
            }
        }


    }


}
