package org.rongjoker.dp.target;

import org.junit.Test;

/**
 *
 * @date 04/08/2021
 * 72. 编辑距离 https://leetcode-cn.com/problems/edit-distance/
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 经典的dp题目，可以用递归或者迭代来求解
 * 状态转移方程较为直观，但是实现较为麻烦
 *
 */
public class EditDistance72 {

    @Test
    public void test72(){

        String word1 = "plasma", word2 = "altruism";
        System.out.println(minDistanceDp(word1,word2));

    }

    /**
     * 迭代的做法，也就是最标准的dp做法
     * @param word1
     * @param word2
     * @return
     */
    public int minDistanceDp(String word1, String word2) {
        int max1 = word1.length(),max2 = word2.length();
        if(max1==0) return max2;
        if(max2==0) return max1;
        dp = new int[max1+1][max2+1];

        //注意这里要进1制，别忘了等号
        for (int i = 0; i <= max2; i++) {
            dp[0][i] = i;
        }

        //注意这里要进1制，别忘了等号
        for (int i = 0; i <= max1; i++) {
            dp[i][0] = i;
        }


        for (int i = 0; i < max1; i++) {
            for (int j = 0; j < max2; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    dp[i+1][j+1] = dp[i][j];
                else{
                    dp[i+1][j+1] = 1 + Math.min(Math.min(dp[i+1][j],dp[i][j+1]),dp[i][j]);
                    //删除、插入、替换三种情况，因为n*n，所以插入后顺序向前的情况也会被考虑到。类似递归
                }
            }
        }

        return dp[max1][max2];
    }



    /**
     * 递归的解法，比较直观
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int max1 = word1.length(),max2 = word2.length();
        if(max1==0) return max2;
        if(max2==0) return max1;
        dp = new int[max1+1][max2+1];
        for (int i = 0; i <= max1; i++) {
            for (int j = 0; j <= max2; j++) {
                dp[i][j] = -1;

            }
        }
        return distance(word1,word2,max1,max2,0,0);
    }

    int[][] dp;


    public int distance(String word1, String word2,int max1,int max2,int index1,int index2){
        if(dp[index1][index2]!=-1) return dp[index1][index2];
        int step;
        if(index1==max1) step =  max2 - index2;//递归基:插入字段
        else if(index2==max2) step = max1 - index1;//递归基:删除多余的字段
        else if(word1.charAt(index1)== word2.charAt(index2)){
            step =  distance(word1,word2,max1,max2,index1+1,index2+1);
        }else
            step =  Math.min(Math.min(1+ distance(word1,word2,max1,max2,index1+1,index2),//删除word1的index1
                    1+ distance(word1,word2,max1,max2,index1,index2+1)//插入word1的index1
            ),1+ distance(word1,word2,max1,max2,index1+1,index2+1));//替换掉word1的index1
        dp[index1][index2] = step;
        return step;
    }





}
