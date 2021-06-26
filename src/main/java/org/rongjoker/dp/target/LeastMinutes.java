package org.rongjoker.dp.target;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * @date 06/18/2021
 * 微软的三道题，难度不高，比较诡异
 *
 *
 *
 */
public class LeastMinutes {


    /**
     * 看起来像动态规划，实际上是数学题，而且比较诡异，透露着一个道理：磨刀不误砍柴工
     * @param n
     * @return
     */
    public int leastMinutes(int n) {
        if (n == 1) return 1;
        for (int i = 1; i <= n; ++i) {
            int pow = (int) Math.pow(2, i - 1);
            if (pow >= n) return i;
        }

        return n;

    }


    public int halfQuestions(int[] questions) {
        int len = questions.length;
        int size = len / 2;
        int[] cache = new int[1001];
        for (int question : questions) cache[question]++;
        Arrays.sort(cache);
        int ans = 0;
        for (int i = 1000; i >= 0; i--) {
            size -= cache[i];
            ans++;
            if (size <= 0) {
                return ans;
            }

        }
        return ans;
    }


    @Test
    public void testLargestArea(){

        //["111","222","333"]
        System.out.println(largestArea(new String[]{"11111100000","21243101111","21224101221","11111101111"}));
        System.out.println(largestArea(new String[]{"110","231","221"}));
        System.out.println(largestArea(new String[]{"111","222","333"}));

    }


    boolean[][] visited;
    int w, h;

    public int largestArea(String[] grid) {
        w = grid.length;
        h = grid[0].length();
        int ans = -1000;
        visited = new boolean[w][h];
        for (int i = 1; i < w - 1; ++i) {
            for (int j = 1; j < h - 1; ++j) {
                if (visited[i][j] || grid[i].charAt(j) == '0') continue;
                int temp = 1;
                visited[i][j] = true;
                temp += dfs(grid, i - 1, j, grid[i].charAt(j));
                temp += dfs(grid, i + 1, j, grid[i].charAt(j));
                temp += dfs(grid, i, j - 1, grid[i].charAt(j));
                temp += dfs(grid, i, j + 1, grid[i].charAt(j));
                ans = Math.max(ans, temp);
                //if(temp>=3) System.out.println("position:"+grid[i].charAt(j));
            }
        }

        return Math.max(ans,0);

    }


    public int dfs(String[] grid, int i, int j, char target) {
        if (i < 0 || i > w - 1 || j < 0 || j > h - 1 ) return 0;


        if(visited[i][j])return 0;

        if (grid[i].charAt(j) == target) {
            if (i <= 0 || i >= w - 1 || j <= 0 || j >= h - 1 ) return -1000;
            visited[i][j] = true;
            int temp = 1;
            temp += dfs(grid, i - 1, j, target);
            temp += dfs(grid, i + 1, j, target);
            temp += dfs(grid, i, j - 1, target);
            temp += dfs(grid, i, j + 1, target);
            return temp;
        }else if(grid[i].charAt(j) == '0') return -1000;

        return 0;


    }


}
