package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * date 01/11/2021
 * 746. 使用最小花费爬楼梯 https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 * <p>
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 * <p>
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class MinCostClimbingStairs {

    @Test
    public void test746() {

        int[] array = {10, 15};

        System.out.println(minCostClimbingStairsOptimize(array));

    }

    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;

        int[] dp = new int[n+1];
        dp[1] = cost[0];

        for (int i = 2; i <=n; i++) {
            dp[i] = Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-1]);
        }

        return Math.min(dp[n],dp[n-1]);

    }


    /**
     * 常规优化 双99%
     * @param cost
     * @return
     */
    public int minCostClimbingStairsOptimize(int[] cost) {

        int n = cost.length;

        int previous = 0,current = cost[0],temp=0;

        for (int i = 2; i <=n; i++) {
            temp = current;
            current = Math.min(current+cost[i-1],previous+cost[i-1]);
            previous = temp;
        }

        return Math.min(current,previous);

    }




}
