package org.rongjoker.contest.biweekly52;

import org.junit.Test;

/**
 *
 * 5212. 向下取整数对和 https://leetcode-cn.com/problems/sum-of-floored-pairs/
 * 桶的妙用
 */
public class Test4 {

    @Test
    public void test4(){

        System.out.println(sumOfFlooredPairs(new int[]{2,5,9}));
        System.out.println(sumOfFlooredPairs(new int[]{7,7,7,7,7,7,7}));

    }


    public int sumOfFlooredPairs(int[] nums) {

        int mod = (int)(Math.pow(10, 9) + 7);

        long ans = 0L;
        int max = 0;
        for (int num : nums)
            max = Math.max(max,num);

        int[] sum = new int[max*2];
        int[] bucket = new int[max+1];

        for (int num : nums)
            bucket[num]++;
        for (int i = 1; i < max*2; i++){
            if(i <=max)
            sum[i] += sum[i-1] + bucket[i];
            else sum[i] += sum[i-1];
        }



        for (int i = 1; i <= max; i++) {
            if(bucket[i]>0){//跳跃无效的桶数据
                for (int j = 1; j * i <= max; j++) {//除数为j
                    ans +=  ((long) (sum[i * (j + 1) -1] - sum[(i*j) - 1]) * j * bucket[i])%mod ;
                    ans %=mod;
                }
            }

            ans %=mod;
        }


        return (int) ans;

    }


    
}
