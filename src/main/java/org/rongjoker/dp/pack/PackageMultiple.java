package org.rongjoker.dp.pack;

import org.junit.Test;

/**
 * 多重背包，转换为01背包+完全背包来求解
 *
 * @date 01/13/2021
 */
public class PackageMultiple {

    int V = 13;

    int[] dp = new int[V + 1];

    @Test
    public void testPackage() {
        //重量w[i]表示第i件物品的重量
        int w[] = new int[]{2};
        //价值v[i]表示第i件物品价值
        int v[] = new int[]{3};


        int c[] = new int[]{6};

        //背包重量
        int n = w.length;

//        for (int i = 1; i <= n; i++) {
//            maxValue01(w[i - 1], v[i - 1]);
//            maxValueComplete(w[i-1],v[i-1]);
//        }

        for (int i = 1; i <= n; i++) {
            maxValueMultiple(w[i-1],v[i-1],c[i-1]);
        }


        System.out.println(dp[V]);





    }

    /**
     * 01背包核心代码
     * 优化成一维数组
     * <p>
     * 01背包的特殊性（每个物品只有1个），可以从右边向左边去计算,只需要一维数组即可；从左到右则会覆盖一些数据
     * 转移方程为当前的（而不是前一个）和当左位移后+v取最大值
     *
     * @param w
     * @param v
     * @return
     */
    public void maxValue01(int w, int v) {
        for (int j = V; j >= w; j--) {//极限优化，省去小于当前数字的那些循环,从后向前
            dp[j] = Math.max(dp[j], dp[j - w] + v);
        }
    }


    /**
     * 完全背包
     * 用01背包的思路解完全背包
     * 比如有物品1无数个，物品2无数个，背包容量7
     * 可以把物品1，物品1，物品1，物品1...看作01背包中的每个不同的物品，每一个都是从自己的上次转移过来的
     * 转移方程为当前的（而不是前一个）和当右位移后+v取最大值
     *
     * @param w 物品重量
     * @param v 物品价值
     * @return
     */
    public void maxValueComplete(int w, int v) {

        for (int j = w; j <= V; j++) {
            dp[j] = Math.max(dp[j], dp[j - w] + v);
        }
    }

    /**
     * 多重背包
     * 利用二进制把每个背包转换为多个01背包
     * 复杂度V*sum(logc)
     *
     * @param
     * @param w 重量
     * @param v 价值
     * @param c 数量
     * @return
     */
    public void maxValueMultiple(int w, int v, int c) {
        if (c * w > V) {//数量足够装满背包，相当于完全背包
            this.maxValueComplete(w, v);
        } else {
            int index = 1;
            for (; index <= c; index <<= 1) {//二进制分割，比如6，分割成1、2、3相当于每个都只有1个的01背包
                c-=index;//直接原地减，避免后续计算出错
                this.maxValue01(w * index, c * index);//相当于01背包
            }

            if(c>0)
                this.maxValue01(w * c, v*c);//相当于01背包
        }
    }





}
