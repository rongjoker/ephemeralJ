package org.rongjoker.dp.target;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 03/16/2021
 * @date 03/22/2021
 * 887. 鸡蛋掉落 https://leetcode-cn.com/problems/super-egg-drop/
 * <p>
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * <p>
 * 经典题目
 * 1：只有1个鸡蛋 N层，则n次
 * 2：只有1层，则1次
 * 3：只有0曾，则0次
 * 本质还是重叠子问题，比如已经尝试了99层，那么对第100层，就是再多一次
 * 假如有2个鸡蛋、6层楼；有6个选择扔第一个鸡蛋，即6种情况
 * 状态转移方程就是1+min(5种情况)
 * 默认方案复杂度太高，会超时
 * 用二分法来优化复杂度
 */
public class SuperEggDrop887 {


    @Test
    public void test887() {

        System.out.println(superEggDrop(6, 5000));

    }

    int[][] dp;

    //默认的方法，递归+记忆化搜索,会超时
    public int fetch(int kk, int nn) {
        if (kk == 0) return 0;
        if (kk == 1) return nn;

        if (nn <= 1) return nn;

        int temp = dp[kk][nn];
        if (temp != -1)
            return temp;

        for (int i = 1; i <= nn; ++i) {//这个循环，复杂度非常高
            if (temp == -1)
                temp = 1+Math.max(fetch(kk - 1, i - 1), fetch(kk, nn - i));
            else
                temp = Math.min(temp, 1+Math.max(fetch(kk - 1, i - 1), fetch(kk, nn - i)));

        }

        dp[kk][nn] = temp;

        return temp;

    }


    public int superEggDrop(int k, int n) {
        dp = new int[k + 1][n + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return fetchBinary(k, n);
    }


    //二分优化
    public int fetchBinary(int kk, int nn) {
        if (kk == 0) return 0;
        if (kk == 1) return nn;

        if (nn <= 1) return nn;

        if (dp[kk][nn] != -1)
            return dp[kk][nn];

        int left=1,right=nn+1;

        while (left<right){
            int middle = left+(right-left)/2;
            if(fetchBinary(kk-1,middle-1)>=fetchBinary(kk,nn-middle))
                right=middle;
            else left=middle+1;
        }

        dp[kk][nn] = 1 + Math.max(fetchBinary(kk-1,left-1),fetchBinary(kk,nn-left));

        return dp[kk][nn];

    }


}
