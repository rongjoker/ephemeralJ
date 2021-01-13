package org.rongjoker.dp.pack;

import org.junit.Test;

/**
 * 分组背包，也是很多dp题目的母题，如1155. 掷骰子的N种方法
 *
 * 物品被划分为很多组，每个组里每种物品都有1个，每组只能选一个
 * 有点类似排列组合
 *
 * @date 01/13/2021
 */
public class PackageGroup {

    int V = 7;//最大容量

    int k = 11;//k组

    int[] dp = new int[V + 1];

    @Test
    public void testPackage() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{2, 3, 4};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{5, 6, 7};


        //背包重量
        int n = w.length;

        //状态转移方程：取 （k-1的V内最大值）和dp[index - w[k]]+v[k] 的较大值(逻辑类似完全背包的一部分，即在上一个的基础上基础轮一遍新的)
        for (int i = 0; i < k; i++) {//一次循环结束，即记录总共有i组的最大值
            for (int j = 1; j <= n; j++) {
                maxValue01(w[j-1],v[j-1]);
            }
        }

        System.out.println(dp[V]);

    }

    /**
     * 01背包核心代码
     * 优化成一维数组
     * <p>
     * 01背包的特殊性（每个物品只有1个），可以从右边向左边去计算,只需要一维数组即可；从左到右则会覆盖一些数据
     * 转移方程为当前的（而不是前一个）和当左位移后+v取最大值
     *
     * @param w
     * @param v
     * @return
     */
    public void maxValue01(int w, int v) {
        for (int j = V; j >= w; j--) {//极限优化，省去小于当前数字的那些循环,从后向前
            dp[j] = Math.max(dp[j], dp[j - w] + v);
        }
    }




}
