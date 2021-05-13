package org.rongjoker.dp.target;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 05/13/2021
 * 1269. 停在原地的方案数 https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
 * 动态规划,类似目标和,可以利用滚动数组做优化
 *
 */
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps1267 {

    @Test
    public void test1267(){

        System.out.println(numWays(23, 9));
        System.out.println(numWaysOptimize(23, 9));
        System.out.println(numWaysOptimizeArray(23, 9));


    }

    /**
     * 无内存优化版本，遇到内存恶意限制会oom
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWays(int steps, int arrLen) {

        int mode = (int)(Math.pow(10,9)+7);

        int[][] dp =new  int[steps+1][arrLen+1];
        dp[1][0] = 1;
        dp[1][1] = 1;

        long temp;
        for (int i = 2; i <= steps; i++) {
            for (int j = 0; j <= Math.min(arrLen,i); j++) {
                temp = 0L;
                temp += dp[i-1][j];
                if (j - 1 >=0) temp+= dp[i-1][j-1];
                if (j + 1 <arrLen) temp+= dp[i-1][j+1];
                dp[i][j] = (int) (temp%mode);
            }
        }

        return dp[steps][0];

    }


    /**
     * 优化版本，使用hashmap进行优化
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWaysOptimize(int steps, int arrLen) {

        int mode = (int)(Math.pow(10,9)+7);

        Map<Integer,Long> current = new HashMap<>();
        Map<Integer,Long> previous = new HashMap<>();

        previous.put(0,1L);
        previous.put(1,1L);

        long temp;
        for (int i = 2; i <= steps; i++) {
            current = new HashMap<>();
            for (int j = 0; j <= Math.min(arrLen,i); j++) {
                temp = previous.getOrDefault(j,0L);
                if (j - 1 >=0) temp+= previous.getOrDefault(j-1,0L);
                if (j + 1 <arrLen) temp+= previous.getOrDefault(j+1,0L);
                current.put(j,temp%mode);
            }
            previous = current;
        }

        return current.get(0).intValue();

    }

    /**
     * 用滚动数组做优化
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWaysOptimizeArray(int steps, int arrLen) {

        int mode = (int)(Math.pow(10,9)+7);

        long[][] dp =new long[2][arrLen+1];
        dp[1][0] = 1;
        dp[1][1] = 1;

        long temp;
        int cur,prev;
        for (int i = 2; i <= steps; i++) {
            cur = i%2;
            prev = (i+1)%2;
            for (int j = 0; j <= Math.min(arrLen,i); j++) {
                temp = dp[prev][j];
                if (j - 1 >=0) temp+= dp[prev][j-1];
                if (j + 1 <arrLen) temp+= dp[prev][j+1];
                dp[cur][j] = (int) (temp%mode);
            }
        }

        return (int) dp[steps%2][0];
    }


}
