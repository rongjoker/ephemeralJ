package org.rongjoker.dp.pack01;

import org.junit.Test;

/**
 * 01背包，优化为1维数组
 * 默认的算法是从前（左）向后（右）递推，会不停的覆盖；改为从右向左递推即可，并在循环内部，极限优化，省去小于当前数字的那些循环
 * @date 01/05/2021
 */
public class ZeroOnePackageOptimize2 {

    @Test
    public void testPackageOptimize2() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{0,2, 4, 3};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{0,5, 6, 7};
        //总物品数
        int N = 3;
        //背包重量
        int V = 7;

        int result[] = maxValue(N, V, w, v);
        System.out.println(result[V]);


    }

    public static int[] maxValue(int N, int W, int w[], int v[]) {

        //数组初始化都是0，不必要初始化
        int[] dp = new int[W + 1];

        for (int i = 1; i < N + 1; i++) {

            for (int j = W; j >=w[i]; j--) {//极限优化，省去小于当前数字的那些循环
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }

        }


        return dp;
    }
}
