package org.rongjoker.dp.target;


import org.junit.Test;

/**
 * 198. 打家劫舍 https://leetcode-cn.com/problems/house-robber/
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class HouseRobber198 {


    @Test
    public void test198(){


        int[] array = {2,1,1,2};

        System.out.println(robOptimize(array));
    }

    public int rob(int[] nums) {

        if(nums.length==0)return 0;

        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);

        int[] dp = new int[nums.length+2];//dp数组第一第二都默认为0，这样循环原可以从下标0开始

        for (int i = 0; i < nums.length; i++) {
            dp[i+2] = Math.max(dp[i] +nums[i],dp[i+1]);
        }

        return dp[nums.length+1];
    }




    /**
     * 利用滚动数组优化空间
     * @param nums
     * @return
     */
    public int robOptimize(int[] nums) {

        if(nums.length==0)return 0;

        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);

        int dp1 = 0;
        int dp2 = 0;

        for (int i = 0; i < nums.length; i++) {
            int dp = Math.max(dp1 +nums[i],dp2);
            dp1 = dp2;dp2 = dp;
        }

        return dp2;
    }
}
