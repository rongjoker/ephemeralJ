package org.rongjoker.dp.tree;

import org.junit.Test;

/**
 * @date 05/20/2021
 * 96. 不同的二叉搜索树 https://leetcode-cn.com/problems/unique-binary-search-trees/
 * 递归+备忘录
 */
public class UniqueBinarySearchTrees96 {

    @Test
    public void test96() {
        System.out.println(numTrees(18));
    }

    int[][] dp;

    public int numTrees(int n) {
        dp = new int[n+1][n+1];
        return count(1, n);
    }

    public int count(int start, int max) {
        if (start <= 0 || max <= start) return 1;
        if(dp[start][max]>0)return dp[start][max];
        int ans = 0;
        for (int i = start; i <= max; i++) {
            ans += count(start, i - 1) * count(i + 1, max);
        }
        dp[start][max] = ans;
        return ans;

    }

}
