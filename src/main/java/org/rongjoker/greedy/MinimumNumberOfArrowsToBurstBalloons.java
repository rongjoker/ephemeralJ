package org.rongjoker.greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @date 01/12/2021
 * 452. 用最少数量的箭引爆气球 https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * <p>
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    @Test
    public void test452() {

        int[][] points = {{1, 2},{2,3}};

//        System.out.println(findMinArrowShots(points));
        System.out.println(findMinArrowShotsOptimize(points));


    }


    /**
     * 右边排序，复杂度非常高 6.51%/5.06%
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
//        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
//        Arrays.sort(points, (a,b)->Integer.compare(a[1],b[1]));
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int length = points.length, hit = 0, min = 0;
        int[] g = new int[length];
        for (int i = 0; i < length; i++) {
            if (g[i] == 0) {
                g[i] = 1;
                hit = points[i][1];
                for (int j = i + 1; j < length; j++) {
                    int[] point = points[j];
//                    if (point[0] <= hit && point[1] >= hit) g[j] = 1;//point[1] >= hit不需要判断末尾，因为是按照末尾排序的
                    if (point[0] <= hit) g[j] = 1;//优化到23.48%
                    else break;
                }
                min++;
            }
        }
        return min;
    }

    /**
     * 50.67%/7.70%
     * @param points
     * @return
     */
    public int findMinArrowShotsOptimize(int[][] points) {
        if (points.length == 0) return 0;

        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int length = points.length, hit = points[0][1], min = 1;
        for (int i = 1; i < length; i++) {
            if(points[i][0]>hit){
                min++;
                hit = points[i][1];
            }
        }
        return min;
    }


}
