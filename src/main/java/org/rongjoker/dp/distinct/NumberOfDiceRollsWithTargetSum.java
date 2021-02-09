package org.rongjoker.dp.distinct;

import org.junit.Test;

/**
 * @date 01/12/2021
 * 1155. 掷骰子的N种方法  https://leetcode-cn.com/problems/number-of-dice-rolls-with-target-sum/
 * 原理类似分组背包
 * @date 01/13/2021
 */
public class NumberOfDiceRollsWithTargetSum {


    @Test
    public void test1155(){
        int d = 1, f = 6, target = 3;
        System.out.println(numRollsToTarget(d,f,target));
    }


    public int numRollsToTarget(int d, int f, int target) {

        if(d*f<target)return 0;

        int mod = (int)(Math.pow(10, 9) + 7),index=Math.min(f,target);
        int[] dp_previous =new int[target+1];
        int[] dp_current =new int[target+1];

        for (int i = 1; i <= index; i++) {
            dp_previous[i]=1;
        }

        if(d==1)return dp_previous[target];

        for (int i = 1; i < d; i++) {//从第一个骰子依次累加
            dp_current =new int[target+1];//每一轮重置

            for (int j = 0; j <= target; j++) {//当目标是0\1\2\3-target等情况下最大值
                for (int k = 1; k <= f; k++) {//多种数字轮流测试
                    if(j-k>=0){
                        dp_current[j]  += dp_previous[j-k];//会有多种情况满足反复累加，比如4；有1+3；2+2；3+1；相当于previous[3]+previous[2]+previous[1]累加
                        if(dp_current[j]>mod)dp_current[j]%=mod;//相加就可能过大，所以在此取余
                    }
                }

            }

            dp_previous = dp_current;

        }

        return dp_current[target];

    }
}
