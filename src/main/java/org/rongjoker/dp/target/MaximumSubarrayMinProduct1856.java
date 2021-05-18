package org.rongjoker.dp.target;


import org.junit.Test;

/**
 * @date 05/18/2021
 * 1856. 子数组最小乘积的最大值 https://leetcode-cn.com/problems/maximum-subarray-min-product/
 * dp + 双指针 先利用dp求出区间最小值，然后双指针依次进行模拟运算即可
 */
public class MaximumSubarrayMinProduct1856 {

    @Test
    public void test1856(){
        System.out.println(maxSumMinProduct(new int[]{3,1,5,6,4,2}));
        System.out.println(maxSumMinProduct(new int[]{2,3,3,1,2}));
    }


    public int maxSumMinProduct(int[] nums) {
        int mod = (int)(Math.pow(10, 9) + 7);
        int sum = 0,len = nums.length;
        int min = 0;
        for(int num:nums){
            sum += num;
            min = Math.min(num,min);
        }

        int left = 0,right = len - 1;
        long ans = min * sum;
        int[][] dp = new int[len][len];
        for(int i =0;i<len;++i)
            dp[i][i] = nums[i];

        for(int i =0;i<len;++i){
            for(int j =i+1;j<len;++j){
                dp[i][j] = Math.min(dp[i][j - 1],nums[j]);
            }
        }
        while(left<right){
            if (nums[left]<nums[right]){
                sum -= nums[left++];
            }else{
                sum -= nums[right--];

            }
            ans = Math.max(sum*dp[left][right],ans);

        }

        return (int)(ans%mod);

    }
}
