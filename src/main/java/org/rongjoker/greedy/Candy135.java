package org.rongjoker.greedy;

import org.junit.Test;

/**
 * 135. 分发糖果 https://leetcode-cn.com/problems/candy/
 * 利用单调性
 *
 *
 */
public class Candy135 {

    @Test
    public void test135() {
        System.out.println(candy(new int[]{1, 2, 2}));
        System.out.println(candy(new int[]{1, 0, 2}));
        System.out.println(candy(new int[]{1,0,2,3,5,6,2,7,1,9,11,999,3,1,2,5,7,9,0,2,1}));
        System.out.println(candy(new int[]{1,6,10,8,7,3,2}));//[1,6,10,8,7,3,2]
    }

    public int candy(int[] ratings) {
        int ans = 0;

        int left = 0, right, len = ratings.length;
        int[] ss = new int[len];
        for (right = 1; right < len; ++right) {
            if (ratings[right] >= ratings[right - 1]) {
                ss[right - 1] = 1;
                for (int j = right - 2; j >= left; --j) {
                    ss[j] = ss[j + 1] + 1;
                }
                if (left > 0 && ratings[left] > ratings[left - 1]) ss[left] = Math.max(ss[left - 1] + 1,ss[left] );
                left = right;
            }
        }

        if (right > left) {
            ss[right - 1] = 1;
            for (int j = right - 2; j >= left; --j) {
                ss[j] = ss[j + 1] + 1;
            }
            if (left > 0 && ratings[left] > ratings[left - 1]) ss[left] = Math.max(ss[left - 1] + 1,ss[left] );
        }

        for (int i : ss) ans += i;

        return ans;

    }
}
