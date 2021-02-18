package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 02/09/2021
 * 1049. 最后一块石头的重量 II  https://leetcode-cn.com/problems/last-stone-weight-ii/
 *  每一回合，从中选出任意两块石头,最后，最多只会剩下一块石头。返回此石头最小的可能重量
 *  类似416,不过这个更简单，不要求装满,而是尽量多装,是典型的01背包
 *  把一个数组分成2个数组，其中1个越接近数组的和的1/2，说明2个数组的差越小
 *  属于 Minimum (Maximum) Path to Reach a Target
 *
 */
public class LastStoneWeight1049 {


    @Test
    public void test1049(){

        int[] nums = {2,7,4,1,8,1};
        System.out.println(lastStoneWeightII(nums));



    }




    public int lastStoneWeightII(int[] stones) {


        int len = stones.length,sum =0;

        if(len==1)return stones[0];

        for (int i = 0; i <len; i++) {
                sum+=stones[i];
        }

        int half = sum/2;

        int[] dp = new int[half+1];

        for (int i = 0; i < len; i++) {

            for (int j = half; j >=stones[i]; j--) {
                dp[j] = Math.max(dp[j],stones[i] + dp[j- stones[i]]);
            }

            if(dp[half]==half)return sum- half*2;

        }

        return sum-dp[half]*2;

    }


}
