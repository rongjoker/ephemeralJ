package org.rongjoker.array;

import org.junit.Test;

/**
 *  @date 08/09/2021
 *
 *  334. 递增的三元子序列 https://leetcode-cn.com/problems/increasing-triplet-subsequence/
 *  非常巧妙
 *  a代表1个最小的数字
 *  b代表2个最小数字
 *  虽然a会替换，跑到b的前面，但是不影响b的数量
 *
 *
 */
public class IncreasingTriplet334 {

    @Test
    public void test334(){
        System.out.println(increasingTriplet(new int[]{1,2,1,3}));
        System.out.println(increasingTriplet2(new int[]{1,2,1,3}));

    }

    public boolean increasingTriplet2(int[] nums) {
        int len = nums.length;
        if(len<3)return false;
        int a = Integer.MAX_VALUE,b = Integer.MAX_VALUE;

        for (int t : nums) {
            if(t<=a)a = t;
            else if(t<=b)b = t;
            else return true;

        }

        return false;

    }


    /**
     * 最长上升子序列，超时
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if(len<3)return false;
        int max = 1;
        int[] dp = new int[len];
        dp[0] =1;
        for(int i=1;i<len;i++){
            dp[i] = 1;
            for(int j =0;j<i;++j){
                if(nums[i]>nums[j]) dp[i] = Math.max(dp[j]+1,dp[i]);
            }
            max = Math.max(dp[i],max);
            if(max>=3)return true;
        }


        return false;

    }
}
