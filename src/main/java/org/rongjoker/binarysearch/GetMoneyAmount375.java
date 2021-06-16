package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 06/15/2021
 * 375. 猜数字大小 II https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 * 我们正在玩一个猜数游戏，游戏规则如下：
 *
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 *
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 *
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 *

 *
 *
 *
 *
 */
public class GetMoneyAmount375 {

    @Test
    public void test375(){

        System.out.println(getMoneyAmount(10));

    }


    public int getMoneyAmount(int n) {

        int ans = 0;

        int left = 1;
        while (left< n){
            int mid = left + ((n - left)>>1);
            System.out.println(mid);
            ans += mid;
            left = mid+1;
        }

        return ans;

    }

}
