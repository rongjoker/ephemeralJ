package org.rongjoker.contest.week235;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 04/14/2021
 * 蛮力算法，超时
 */
public class MinAbsoluteSumDiff {

    @Test
    public void test3(){

        int[] nums1 = {1,10,4,4,2,7}, nums2 = {9,3,5,1,7,4};

        System.out.println(minAbsoluteSumDiff(nums1,nums2));


    }

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

        int n = nums1.length;

        cache = new HashMap<>();
        dp = new int[n][2];

        for(int i=0;i<n;++i){
            if(nums1[i]==nums2[i]){
                dp[i][0] = dp[i][1] = 0;
            }else{
                dp[i][0] = dp[i][1] =  calculate(nums1[i],nums2[i]);
                if(nums1[i]!=nums2[i]){
                    for(int j=0;j<n;++j){
                        if(nums1[j]==nums2[i]){
                            dp[i][1] = 0;
                            break;
                        }else{
                            dp[i][1] =  Math.min(dp[i][1],calculate(nums1[j],nums2[i]));
                        }

                    }
                }
            }



        }

        int mod = (int)(Math.pow(10, 9) + 7);
        long sum = 0;

        for(int i=0;i<n;++i){
            sum+=dp[i][0];
        }

        long sum2 = sum;

        for(int i=0;i<n;++i){
            sum2=Math.min(sum2,sum-dp[i][0]+dp[i][1]);
        }

        return (int)(sum2 % mod);


    }

    int[] key;int a;
    public int calculate(int left,int right){
        key = new int[]{Math.min(left,right),Math.max(left,right)};
        if(!cache.containsKey(key)){
            a = Math.abs(left-right);
            cache.put(key,a);
            return a;
        }
        return cache.get(key);

    }



    Map<int[],Integer> cache;
    int[][] dp;
}
