package org.rongjoker.dp.target;


import org.junit.Test;

/**
 * @date 02/09/2021
 * 279. 完全平方数 https://leetcode-cn.com/problems/perfect-squares/
 * 利用dp来做，每次比较的所有小于当前数字的平方根的情况，比如12，比较1、2、3（1、4、9）可得出4+8(4+8=4+4+4)的时候3次是最小的(9+3=9+1+1+1)
 * 也可以利用数学来做
 *
 * 任何正整数都可以拆分成不超过4个数的平方和 ---> 答案只可能是1,2,3,4
 * 如果一个数最少可以拆成4个数的平方和，则这个数还满足 n = (4^a)*(8b+7) ---> 因此可以先看这个数是否满足上述公式，如果不满足，答案就是1,2,3了
 * 如果这个数本来就是某个数的平方，那么答案就是1，否则答案就只剩2,3了
 * 如果答案是2，即n=a^2+b^2，那么我们可以枚举a，来验证，如果验证通过则答案是2
 * 只能是3
 *
 */
public class PerfectSquares279 {


    @Test
    public void test279() {

        System.out.println(numSquares(9998));
        System.out.println(numSquares2(9998));


    }


    public int numSquares2(int n) {

        int k = (int)Math.floor(Math.sqrt(n));

        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) dp[i]=i;
        for(int i=2;i<=k;i++){
            int temp = i*i;
            for(int j=temp;j<=n;++j){
                dp[j] = Math.min(dp[j],dp[j-temp]+1);
            }
        }

        return dp[n];

    }


    public int numSquares(int n) {

        int[] dp = new int[n + 1];

        int floor = (int) Math.floor(Math.sqrt(n));

        int[] sq = new int[floor + 1];


        for (int i = 1; i <= n; i++) {

            int q = (int) Math.floor(Math.sqrt(i));

            if (sq[q] == 0) {
                sq[q] = q * q;
            }

            dp[i] = dp[i - sq[q]] + 1;

            if(q>1){
                for (int j = 1; j < q; j++) {
                    dp[i] = Math.min(dp[i], dp[i - sq[j]] + 1);
                }
            }

        }


        return dp[n];
    }
}
