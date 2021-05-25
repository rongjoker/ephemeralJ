package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @date 03/05/2021
 * 77. 组合 https://leetcode-cn.com/problems/combinations/
 * 这个题目实际是组合类型的回溯法入门
 */
public class Combinations77 {




    @Test
    public void test77() {

        permute = combine(4, 2);
        permute.forEach(System.out::println);


    }


    List<List<Integer>> permute = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> combine(int n, int k) {
        combineSeq(1, n, k);
        return permute;
    }

    public void combineSeq(int start, int n, int limit) {

        if (limit == 0) {
            permute.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.addLast(i);
            combineSeq(i + 1, n, limit - 1);
            path.removeLast();
        }
    }


}
