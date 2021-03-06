package org.rongjoker.contest.week246;

import org.junit.Test;

import java.util.Arrays;

public class Test4 {

    @Test
    public void test4(){

        System.out.println(Arrays.toString(minDifference(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}})));
        System.out.println(Arrays.toString(minDifference(new int[]{4,5,2,2,7,10}, new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}})));

    }


    public int[] minDifference(int[] nums, int[][] queries) {
        int len = nums.length,len2 = queries.length;
        int[][] dp= new int[len+1][101];
        int max = 0;
        for(int i=0;i<len;i++){
            if (max >= 0) System.arraycopy(dp[i], 1, dp[i + 1], 1, max);
            max = Math.max(max,nums[i]);
            dp[i+1][nums[i]] = dp[i][nums[i]] + 1;
        }
        int[] ans = new int[len2];
        for(int i=0;i<len2;++i){
            int pre = 0,cur,min=Integer.MAX_VALUE;
            for(int j=1;j<=100;++j){
                cur = dp[queries[i][1]+1][j] - dp[queries[i][0]][j];
                if(cur>0){
                    if(pre>0){
                        min = Math.min(j - pre,min);
                        if(min==1)break;
                    }
                    pre = j;
                }


            }
            ans[i] = min==Integer.MAX_VALUE?-1:min;
        }
        return ans;


    }

}
