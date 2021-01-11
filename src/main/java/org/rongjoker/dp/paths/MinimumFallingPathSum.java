package org.rongjoker.dp.paths;


import org.junit.Test;

/**
 * @date 01/11/2021
 * 931. 下降路径最小和 https://leetcode-cn.com/problems/minimum-falling-path-sum/
 *给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 *
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class MinimumFallingPathSum {

    @Test
    public void test913(){


        int[][] grid =new int[3][3];

        grid[0] = new int[]{1,2,3};
        grid[1] = new int[]{4,5,6};
        grid[2] = new int[]{7,8,9};

        System.out.println(minFallingPathSum(grid));

    }

    public int minFallingPathSum(int[][] A) {

        int n = A.length,m = A[0].length;

        int[][] dp = new int[2][m];

        for (int i = n-1; i >=0; i--) {
            int current = i%2;
            int previous = (i+1)%2;

            for (int j = 0; j < m; j++) {
                dp[current][j] = Math.min(Math.min(j-1>=0?dp[previous][j-1]:Integer.MAX_VALUE,dp[previous][j]),j+1<m?dp[previous][j+1]:Integer.MAX_VALUE) +A[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            if (dp[0][i] < min) {
                min = dp[0][i];
            }

        }

        return min;

    }


}
