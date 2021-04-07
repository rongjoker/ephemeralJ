package org.rongjoker.bt;

import org.junit.Test;

/**
 *  @date 04/07/2021
 *  79. 单词搜索 https://leetcode-cn.com/problems/word-search/
 *  回溯算法的热门应用题
 *
 */
public class WordSearch79 {

    @Test
    public void test79(){

        char[][] board ={{'a','a','b','a','a','b'},{'a','a','b','b','b','a'},{'a','a','a','a','b','a'},{'b','a','b','b','a','b'},{'a','b','b','a','b','a'},{'b','a','a','a','a','b'}};
        System.out.println(exist(board,"bbbaabbbbbab"));
    }

    public boolean exist(char[][] board, String word) {
        int len=word.length();
        if(board.length * board[0].length < len) return false;
        int[][] path = new int[board.length +1][board[0].length+1];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(back_tracking(board,path,word,0,len-1,i,j)){
                   return true;
                }
            }
        }

        return false;


    }

    int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};

    public boolean back_tracking(char[][] board,int[][] path, String word,int index,int max,int row,int column){

        if(row<0||row>=board.length || column<0||column>=board[0].length)return false;
        if(path[row][column]==1)return false;
        if(board[row][column]== word.charAt(index)){
            path[row][column] = 1;

            if(index==max)return true;

            boolean flag1=false;

            for (int[] ints : direction) {
                if(back_tracking(board,path,word,index+1,max,row+ints[0],column+ints[1])){flag1=true;break;}
            }

            path[row][column] = 0;//回溯

            return flag1;

        }else return false;
    }
}
