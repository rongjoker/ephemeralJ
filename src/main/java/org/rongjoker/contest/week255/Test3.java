package org.rongjoker.contest.week255;


import org.junit.Test;

/**
 *
 * 5852. 最小化目标值与所选元素的差
 *
 * 给你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。
 *
 * 从矩阵的 每一行 中选择一个整数，你的目标是 最小化 所有选中元素之 和 与目标值 target 的 绝对差 。
 *
 * 返回 最小的绝对差 。
 *
 * a 和 b 两数字的 绝对差 是 a - b 的绝对值。
 *
 * 数据范围较少，可以直接dp
 *
 * 1 <= mat[i][j] <= 70
 *
 * 1 <= target <= 800
 *
 */
public class Test3 {

    @Test
    public void test3(){
        System.out.println(minimizeTheDifference(new int[][]{{1,2,3},{4,5,6},{7,8,9}},13));
        System.out.println(minimizeTheDifference(new int[][]{{1},{2},{3}},100));
        System.out.println(minimizeTheDifference(new int[][]{{1,2,9,8,7}},6));
    }


    public int minimizeTheDifference(int[][] mat, int target) {
        int len = mat.length;

        int max_t = 70*len;//最大值可能为4900
        boolean[][] dp = new boolean[len][max_t+1];
        for(int n: mat[0])dp[0][n]=true;

        for(int i=1;i<len;++i){
            for(int n: mat[i]){
                for(int j=n+1;j<=max_t;++j){
                    if(!dp[i-1][j-n])continue;
                    else dp[i][j] = true;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= max_t; i++) {
            if(dp[len-1][i])ans = Math.min(ans,Math.abs(i-target));
        }
        return ans;
    }


}
