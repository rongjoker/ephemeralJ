package org.rongjoker.dp.target;


import org.junit.Test;

/**
 * @date 06/09/2021
 * <p>
 * 879. 盈利计划  https://leetcode-cn.com/problems/profitable-schemes/
 */
public class ProfitableSchemes879 {

    @Test
    public void test879() {

        System.out.println(profitableSchemes(5, 3, new int[]{2, 2}, new int[]{2, 3}));
        System.out.println(profitableSchemes(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));
        System.out.println(profitableSchemes(64, 0, new int[]{80, 40}, new int[]{88, 88}));
        System.out.println(profitableSchemes(95, 53, new int[]{82, 7, 18, 34, 1, 3, 83, 56, 50, 34, 39, 38, 76, 92, 71, 2, 6, 74, 1, 82, 22, 73, 88, 98, 6, 71, 6, 26, 100, 75, 57, 88, 43, 16, 22, 89, 7, 9, 78, 97, 22, 87, 34, 81, 74, 56, 49, 94, 87, 71, 59, 6, 20, 66, 64, 37, 2, 42, 30, 87, 73, 16, 39, 87, 28, 9, 95, 78, 43, 59, 87, 78, 2, 93, 7, 22, 21, 59, 68, 67, 65, 63, 78, 20, 82, 35, 86}
                , new int[]{45, 57, 38, 64, 52, 92, 31, 57, 31, 52, 3, 12, 93, 8, 11, 60, 55, 92, 42, 27, 40, 10, 77, 53, 8, 34, 87, 39, 8, 35, 28, 70, 32, 97, 88, 54, 82, 54, 54, 10, 78, 23, 82, 52, 10, 49, 8, 36, 9, 52, 81, 26, 5, 2, 30, 39, 89, 62, 39, 100, 67, 33, 86, 22, 49, 15, 94, 59, 47, 41, 45, 17, 99, 87, 77, 48, 22, 77, 82, 85, 97, 66, 3, 38, 49, 60, 66}));
//9883351

    }
//
//
//    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//        int M = 1000000007;
//        int len = 0;
//        for (int p : profit) len += p;
//
//        pro = new long[len + 1];//利润的方案数量
//        pro[0] = 1;
//
//        int max = group.length;
//
//        track(n, 0, max, group, profit);
//
//        long ans = 0;
//
//        for (int i = minProfit; i <= len; i++) {
//            ans = (ans + pro[i]) % M;
//        }
//        return (int) ans;
//
//    }
//
//    int currentPro = 0;
//    int currentPeo = 0;
//
//    long[] pro;//利润的方案数量
//
//
//    public void track(int n, int start, int max, int[] group, int[] profit) {
//
//        for (int i = start; i < max; ++i) {
//            if (currentPeo + group[i] > n) continue;
//            currentPeo += group[i];
//            pro[currentPro + profit[i]] = (pro[currentPro + profit[i]] + pro[currentPro]) % 1000000007;
//            currentPro += profit[i];
//            track(n, i + 1, max, group, profit);
//            //
//            currentPeo -= group[i];
//            currentPro -= profit[i];
//        }
//
//    }


    /**
     * dp 有bug
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int M = 1000000007;
        int max = group.length;

        long[][][] dp = new long[max + 1][n+1][minProfit + 1];
        for (int i = 0; i <= n; i++) dp[0][i][0] = 1;


        for (int i = 0; i < max; i++) {
            int tp = profit[i];
            int tg = group[i];
            for (int k = 0; k <= n; ++k) {//背包容量
                for (int j = 0; j <=minProfit; j++) {//价值
                    dp[i+1][k][j] = dp[i][k][j];
                    if(k>=tg){
                        dp[i+1][k][j]  += dp[i][k-tg][Math.max(j-tp,0)];
                        dp[i+1][k][j]%= M;
                    }

                }


            }
        }

        return (int) dp[max][n][minProfit];


    }


}
