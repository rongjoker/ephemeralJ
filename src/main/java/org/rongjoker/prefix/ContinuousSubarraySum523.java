package org.rongjoker.prefix;

import org.junit.Test;

/**
 * @date 06/02/2021
 * 523. 连续的子数组和 https://leetcode-cn.com/problems/continuous-subarray-sum/
 * 养成习惯，看题目的数字范围，可以进行剪枝和过滤
 */
public class ContinuousSubarraySum523 {


    @Test
    public void test523() {
//        System.out.println(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));
        System.out.println(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
        System.out.println(checkSubarraySum(new int[]{23,2,4,6,7}, 6));
        System.out.println(checkSubarraySum(new int[]{2,3,6}, 6));
    }


    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len < 2) return false;
        int[] prex = new int[len + 1];
        int max = -1;
        int start = -1;
        for (int i = 0; i < len; ++i) {
            if (i < len - 1 && nums[i] == 0 && nums[i + 1] == 0) return true;//连续为0，符合条件，提前过滤
            prex[i + 1] = prex[i] + nums[i];
            max = Math.max(prex[i + 1],max);
            if(start==-1 && max>=k)start=i;
        }
        if(max<k)return false;
        for (int i = start; i < len + 1; ++i) {//从足够大的地方开始
                for (int j = 0; j < i - 1; ++j) {
                    int temp = prex[i] - prex[j];
                    if (temp < k) break;//全是正数，故可以提前退出loop
                    if (temp % k == 0) return true;
                }

        }
        return false;

    }


}
