package org.rongjoker.ds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date 07/01/2021
 * 面试题 16.19. 水域大小 https://leetcode-cn.com/problems/pond-sizes-lcci/
 */
public class PondSizes {


    @Test
    public void test() {

        System.out.println(Arrays.toString(pondSizes(new int[][]{{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}})));

    }

    public int[] pondSizes(int[][] land) {
        List<Integer> ans = new ArrayList<>();
        int w = land.length, h = land[0].length;
        boolean[][] visited = new boolean[w][h];

        int[][] dd = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {-1, -1}, {-1, 1}, {1, -1}};
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                if (visited[i][j]) continue;
                int ret = dfs(i, j, land, visited, dd);
                if (ret > 0) {
                    ans.add(ret);
                }
            }
        }

        int[] rr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) rr[i] = ans.get(i);

        Arrays.sort(rr);
        return rr;
    }

    public int dfs(int i, int j, int[][] land, boolean[][] visited, int[][] dd) {
        int w = land.length, h = land[0].length;
        if (i < 0 || i >= w || j < 0 || j >= h || visited[i][j]) return 0;
        visited[i][j] = true;
        int ans = 0;

        if (land[i][j] == 0) {
            ans++;
            for (int[] d : dd) {
                ans += dfs(i + d[0], j + d[1], land, visited, dd);
            }
        }

        return ans;
    }


}
