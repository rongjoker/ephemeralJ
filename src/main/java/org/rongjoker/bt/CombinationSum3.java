package org.rongjoker.bt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 04/24/2021
 * <p>
 * <p>
 * 216. 组合总和 III  https://leetcode-cn.com/problems/combination-sum-iii/
 *
 * 回溯法注意几处细节
 */
public class CombinationSum3 {


    @Test
    public void test216() {
        System.out.println(combinationSum3(3, 7));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        total = k;
        backtracking(1, n);
        return list;
    }


    List<List<Integer>> list = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int total;

    public void backtracking(int index, int target) {

        //注意这里，只有还未满足条件才继续进行循环，否则直接返回
        if (target == 0) {
            if (path.size() == total)
                list.add(new ArrayList<>(path));
            return;
        } else if (target < 0) return;

        for (int i = index; i <= 9; ++i) {
            path.add(i);
            backtracking(i + 1, target - i);
            path.remove(path.size() - 1);

        }

    }
}
