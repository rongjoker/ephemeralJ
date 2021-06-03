package org.rongjoker.array;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 06/03/2021
 * 149. 直线上最多的点数 https://leetcode-cn.com/problems/max-points-on-a-line/
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * 腾讯面试题
 * hard
 * 求每个点与剩下的点的斜率，斜率相同在同一条线上
 * 从大向小循环，退出的可能性更高
 */
public class MaxPoints149 {


    @Test
    public void test149() {
//        System.out.println(maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
        System.out.println(maxPoints(new int[][]{{7,3},{19,19},{-16,3},{13,17},{-18,1},{-18,-17},{13,-3},{3,7},{-11,12},{7,19},{19,-12},{20,-18},{-16,-15},{-10,-15},{-16,-18},{-14,-1},{18,10},{-13,8},{7,-5},{-4,-9},{-11,2},{-9,-9},{-5,-16},{10,14},{-3,4},{1,-20},{2,16},{0,14},{-14,5},{15,-11},{3,11},{11,-10},{-1,-7},{16,7},{1,-11},{-8,-3},{1,-6},{19,7},{3,6},{-1,-2},{7,-3},{-6,-8},{7,1},{-15,12},{-17,9},{19,-9},{1,0},{9,-10},{6,20},{-12,-4},{-16,-17},{14,3},{0,-1},{-18,9},{-15,15},{-3,-15},{-5,20},{15,-14},{9,-17},{10,-14},{-7,-11},{14,9},{1,-1},{15,12},{-5,-1},{-17,-5},{15,-2},{-12,11},{19,-18},{8,7},{-5,-3},{-17,-1},{-18,13},{15,-3},{4,18},{-14,-15},{15,8},{-18,-12},{-15,19},{-9,16},{-9,14},{-12,-14},{-2,-20},{-3,-13},{10,-7},{-2,-10},{9,10},{-1,7},{-17,-6},{-15,20},{5,-17},{6,-6},{-11,-8}}));
//        System.out.println(maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
//        System.out.println(maxPoints(new int[][]{{2, 1}, {3, 7}, {5, 1}, {9, 10000}, {2, 30}, {1000000, 4}}));
    }

    public int maxPoints(int[][] points) {

        int ans = 0;
        int len = points.length;

        for (int i = len - 1; i > ans; --i) {
            Map<Double, Integer> count = new HashMap<>();
            for (int j = i - 1; j >= 0; --j) {
                double c;
                if (points[i][0] == points[j][0]) c = Double.MAX_VALUE;
                else if (points[i][1] == points[j][1]) c = 0;
                else {
                    c = ((double) (points[i][1] - points[j][1])) / (double) (points[i][0] - points[j][0]);
                }

                int a = count.getOrDefault(c, 0);
                ans = Math.max(ans, a + 1);
                count.put(c, a + 1);
            }
        }

        return ans+1;


    }
}
