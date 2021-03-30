package org.rongjoker.dp.coin;

import org.junit.Test;

/**
 *
 * date 01/11/2021
 * 983. 最低票价 https://leetcode-cn.com/problems/minimum-cost-for-tickets/
 *
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 *
 * 火车票有三种不同的销售方式：
 *
 * 一张为期一天的通行证售价为 costs[0] 美元；
 * 一张为期七天的通行证售价为 costs[1] 美元；
 * 一张为期三十天的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 *
 * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class MinimumCostForTickets {


    @Test
    public void test983(){

        int[] days = {1,2,3,4,5,6,7,8,9,10,30,31},costs = {2,7,15};
        System.out.println(mincostTickets(days,costs));


    }

    /**
     * 其实就是完全背包套了一层皮
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days[days.length-1];
        int[] dp = new int[n+1];

        for (int day : days) {
            dp[day] = 1;
        }

        int c1=0,c7=0,c30=0;
        for (int i = days[0]-1; i < n; i++) {//从第个有效天开始
            if(dp[i+1]==0){//不是假期，费用延续前一天
                dp[i+1] = dp[i];
            }else {
                c1=dp[i] +costs[0];
                c7 = (i-6>=0?dp[i-6]:dp[0] )+costs[1];
                c30 = (i-30>=0?dp[i-29]:dp[0]) +costs[2];
                dp[i+1] = Math.min(c1,Math.min(c7,c30));
            }
        }
        return dp[n];

    }





}
