package org.rongjoker.ds;


import org.junit.Test;

/**
 * @date 06/16/2021
 * 947. 移除最多的同行或同列石头 https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/
 * <p>
 * dfs
 */
public class RemoveStones947 {


    @Test
    public void test947() {

        System.out.println(removeStones2(new int[][]{{3, 3}, {4, 4}, {1, 4}, {1, 5}, {2, 3}, {4, 3}, {2, 4}}));

    }


    /**
     * 优化的dfs，速度依然很慢
     * @param stones
     * @return
     */
    public int removeStones2(int[][] stones) {
        int len = stones.length;
        int ans = 0;
        boolean[] visited = new boolean[len];

        for (int i = 0; i < len; ++i) {
            if (visited[i]) continue;//访问过
            dfs2(i, stones, visited);
            ans++;
        }
        return len - ans;

    }


    public void dfs2(int index, int[][] stones, boolean[] visited) {
        for (int i = 0; i < stones.length; ++i) {
            if (visited[i]) continue;
            if (i == index) continue;
            if (stones[index][0] == stones[i][0] || stones[index][1] == stones[i][1]) {
                visited[i] = true;
                dfs2(i, stones, visited);
            }
        }
    }


    /**
     * 笨方法，dfs超时
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {


        int w = 0, h = 0;

        int ans = 0;
        for (int i = 0; i < stones.length; ++i) {
            w = Math.max(w, stones[i][0]);
            h = Math.max(h, stones[i][1]);
        }
        boolean[][][] visited = new boolean[w + 1][h + 1][2];

        for (int i = 0; i < stones.length; ++i) {
            visited[stones[i][0]][stones[i][1]][0] = true;//有石头
        }

        for (int i = 0; i <= w; ++i) {
            for (int j = 0; j <= h; ++j) {
                if (visited[i][j][1]) continue;//访问过
                if (!visited[i][j][0]) visited[i][j][1] = true;
                else {
                    dfs(visited, i, j, w, h);
                    ans++;
                }
            }
        }
        return stones.length - ans;

    }

    public void dfs(boolean[][][] visited, int i, int j, int w, int h) {
        visited[i][j][1] = true;
        for (int start = 0; start <= w; ++start) {
            if (visited[start][j][1]) continue;
            if (start == i) continue;
            if (visited[start][j][0]) {
                dfs(visited, start, j, w, h);
            } else {
                visited[start][j][1] = true;
            }
        }

        for (int start = 0; start <= h; ++start) {
            if (visited[i][start][1]) continue;
            if (start == j) continue;
            if (visited[i][start][0]) {
                dfs(visited, i, start, w, h);
            } else {
                visited[i][start][1] = true;
            }
        }


    }
}
