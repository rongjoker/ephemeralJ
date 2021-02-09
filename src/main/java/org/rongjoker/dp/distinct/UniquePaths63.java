package org.rongjoker.dp.distinct;

import org.junit.Test;

/**
 * @date 02/9/2021
 * 63. 不同路径II https://leetcode-cn.com/problems/unique-paths-ii/
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 */
public class UniquePaths63 {

    @Test
    public void test63() {

        int[][] obstacleGrid = {{0,0,0}, {0,1,0}, {0,0,0}};

        System.out.println(uniquePathsWithObstacles(obstacleGrid));


    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0]==1)return 0;

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[2][n];

        for (int i = 0; i < n; i++) {
            if(obstacleGrid[0][i]==0)
                dp[0][i] = 1;
            else break;//这句很关键，如果前有障碍，后面走不通的
        }

        for (int i = 1; i < m; i++) {
            int current = i % 2;
            int previous = (i + 1) % 2;
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[current][j] = 0;
                else{
                    if(j >0)
                        dp[current][j] = dp[previous][j] + dp[current][j - 1];
                    else dp[current][j] = dp[previous][j];
                }

            }
        }

        return dp[(m - 1) % 2][n - 1];
    }

}
