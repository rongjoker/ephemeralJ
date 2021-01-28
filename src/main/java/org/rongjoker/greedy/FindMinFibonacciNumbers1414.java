package org.rongjoker.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 01/28/2021
 * <p>
 * 1414. 和为 K 的最少斐波那契数字数目 https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
 * 从最大的向最小的遍历，不为负就一直原地累加
 * 不看题解，5分钟写完
 */
public class FindMinFibonacciNumbers1414 {

    @Test
    public void test1414() {

        System.out.println(findMinFibonacciNumbers(19));


    }

    public int findMinFibonacciNumbers(int k) {

        List<Integer> fns = new ArrayList<>();
        int fn1 = 1, fn2 = 1, total = 0;
        fns.add(fn1);
        fns.add(fn2);

        while (fn2 < k) {
            fn2 = fn2 + fn1;
            fns.add(fn2);
            fn1 = fn2 - fn1;
        }
        int index = fns.size() - 1;
        while (k != 0) {
            if (k - fns.get(index) < 0) index--;
            else {
                k = k - fns.get(index);
                total++;
            }
        }

        return total;

    }


}
