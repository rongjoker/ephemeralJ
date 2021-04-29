package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * 403. 青蛙过河 https://leetcode-cn.com/problems/frog-jump/
 *
 */
public class FrogJump403 {


    @Test
    public void test403(){

        System.out.println(canCross(new int[]{0,1,3,5,6,8,12,17}));
    }


    public boolean canCross(int[] stones) {
        int len = stones.length;
        boolean[][] dp = new boolean[len][len];//目标点用某个步数能否到达(最多有n步)
        //转化为区间dp
        dp[0][0] = true;
        //提前剪枝
        for (int i = 1; i < len; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }

        int space;
        for (int i = 1; i < len; i++) {
            for (int j = i-1; j >=0; --j) {//从之前的石头上跳过
                space = stones[i] - stones[j];
                if(space > j+1)break;//从j块石头跳过来，最高速度不会超过j+1
                dp[i][space] = dp[j][space-1] || dp[j][space] || dp[j][space+1];
                //k-1 ,k ,k+1,反推上一步的速度，这一步是space的速度，上一步范围也确定了
                //剪枝，最后一个点的范围其实很窄,不用循环完检查dp[len-1],直接在此判断即可
                if (i == len - 1 && dp[i][space]) {
                    return true;
                }

            }


        }


        return false;


    }


}
