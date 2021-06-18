package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 *  @date 06/18/2021
 *  * 887. 鸡蛋掉落 https://leetcode-cn.com/problems/super-egg-drop/
 *
 *  给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 *
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 *
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * 重新刷鸡蛋掉落问题
 *
 *
 *
 */
public class SuperEggDrop887 {


    @Test
    public void test887(){
        System.out.println(superEggDrop(1,2));//2
        System.out.println(superEggDrop(2,6));//3
        System.out.println(superEggDrop(3,14));//4

    }


    public int superEggDrop(int k, int n) {

        dp = new int[k+1][n+1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;

            }
        }

        return drop(n,k);

    }


    int[][] dp;

    //这种会超时
    public int drop(int f,int egg){
        if(egg==1)return f;
        if(f==0) return 0;

        if(dp[f][egg]!=Integer.MAX_VALUE)return dp[f][egg];

        int ans = Integer.MAX_VALUE;
        for(int x=1;x<=f;x++){
            ans = Math.min(ans,Math.max(drop(x-1,egg-1),drop(f-x,egg)) + 1);
        }

        dp[f][egg] = ans;
        return ans;
        //在第x层，有2种可能，碎了，那么鸡蛋少一个，并且答案肯定在x层下面，即x-1
        //鸡蛋没碎，则答案在x层上面，即f-x，鸡蛋数量不变

    }






}
