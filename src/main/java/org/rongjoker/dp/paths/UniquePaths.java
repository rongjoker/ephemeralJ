package org.rongjoker.dp.paths;

import org.junit.Test;

/**
 * @date 01/10/2021
 * 62. 不同路径 https://leetcode-cn.com/problems/unique-paths/
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class UniquePaths {

    @Test
    public void test62(){

        int m = 3, n = 2;

        System.out.println(uniquePathsOptimize(m,n));



    }


    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public int uniquePathsOptimize(int m, int n) {
        int[][] dp = new int[2][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        dp[1][0] = 1;

        for (int i = 1; i < m; i++) {
            int current = i%2;
            int previous = (i+1)%2;
            for (int j = 1; j < n; j++) {
                dp[current][j] = dp[previous][j] + dp[current][j-1];
            }
        }

        return dp[(m-1)%2][n-1];
    }

}
