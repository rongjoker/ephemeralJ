package org.rongjoker.backtrack;

import org.junit.Test;

/**
 *
 */
public class MaxCompatibilitySum1947 {

    @Test
    public void test1947() {

        //[[1,1,1},{0,0,1},{0,0,1},{0,1,0]]
        //[[1,0,1},{0,1,1},{0,1,0},{1,1,0]]

        System.out.println(maxCompatibilitySum(new int[][]{{
                        1, 1, 1}, {0, 0, 1}, {0, 0, 1}, {0, 1, 0
                }},
                new int[][]{{
                        1, 0, 1}, {0, 1, 1}, {0, 1, 0}, {1, 1, 0
                }}));//9

//        System.out.println(maxCompatibilitySum(new int[][]{{
//                0,1,0,1,1,1},{1,0,0,1,0,1},{1,0,1,1,0,0
//                }},
//                new int[][]{{
//                        1,0,0,0,0,1},{0,1,0,0,1,1},{0,1,0,0,1,1
//                }}));
//
//
//
//        System.out.println(maxCompatibilitySum(new int[][]{{
//                0,0},{0,0},{0,0
//        }},
//                new int[][]{{
//                        1,1},{1,1},{1,1
//                }}));
//
//        System.out.println(maxCompatibilitySum(new int[][]{{1,1,0},{1,0,1},{0,0,1}},
//                new int[][]{{
//                        1,0,0},{0,0,1},{1,1,0
//                }}));

    }


    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length, n = students[0].length;
        visited = new boolean[m];
        int[][] dp = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                int temp = 0;
                for (int k = 0; k < n; k++) {
                    if (students[i][k] == mentors[j][k]) {
                        temp++;
                    }
                }
                dp[i][j] = temp;
            }
        }

        track(dp, 0, 0, m, 0);

        return ans;
    }

    int ans = 0;
    boolean[] visited;


    public void track(int[][] dp, int l, int r, int m, int cur) {
        if (l == m) {
            ans = Math.max(cur, ans);
            return;
        }

        for (int i = l; i < m; ++i) {
            for (int j = r; j < m; ++j) {
                if (visited[j]) continue;
                cur += dp[i][j];
                visited[j] = true;
                track(dp, i + 1, 0, m, cur);
                cur -= dp[i][j];
                visited[j] = false;
            }
        }
    }


}
