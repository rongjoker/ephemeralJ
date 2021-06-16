package org.rongjoker.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 06/13/2021
 * @date 06/16/2021
 * 1889. 装包裹的最小浪费空间 https://leetcode-cn.com/problems/minimum-space-wasted-from-packaging/
 */
public class MinWastedSpace1889 {


    @Test
    public void test1889() {
        System.out.println(minWastedSpace2(new int[]{2, 3, 5}, new int[][]{
                {4, 8}, {2, 8}
        }));

        System.out.println(minWastedSpace2(new int[]{3, 5, 8, 10, 11, 12}, new int[][]{
                {12}, {11, 9}, {10, 5, 14}
        }));

    }


    /**
     * 蛮力算法会超时
     *
     * @param packages
     * @param boxes
     * @return
     */
    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        long ans = Long.MAX_VALUE;
        int len = packages.length;

        for (int[] box : boxes) {
            long w = 0;

            Arrays.sort(box);
            int m = box[box.length - 1];
            if (m < packages[len - 1]) continue;
            int start = 0;
            for (int aPackage : packages) {
                while (box[start] < aPackage) ++start;
                w += (box[start] - aPackage);
            }
            ans = Math.min(ans, w);

        }


        return ans == Long.MAX_VALUE ? -1 : (int) (ans % 1000000007);
    }


    /**
     * 利用前缀和+二分查找优化
     * 可以直接不用前缀和，最终肯定是所有的箱子容量 -  所有的背包容量
     *
     * @param packages
     * @param boxes
     * @return
     */
    public int minWastedSpace2(int[] packages, int[][] boxes) {

        Arrays.sort(packages);
        long ans = Long.MAX_VALUE;
        int len = packages.length;

        long available = 0;
        for (int p : packages) available += p;
//        available %= 1000000007;

        for (int[] box : boxes) {
            long w = 0;
            Arrays.sort(box);
            int m = box[box.length - 1];
            if (m < packages[len - 1]) continue;
            int start = 0, right;
            for (int b : box) {
                right = count(b, packages, start);
                long total = ((long) (right - start + 1)) * (long) b;
//                total %= 1000000007;
                w += total;
//                w %= 1000000007;
                if (right >= len - 1) break;
                start = right + 1;
            }
            ans = Math.min(ans, w - available);

        }

        return ans == Long.MAX_VALUE ? -1 : (int) (ans % 1000000007);
    }

    public int count(int capacity, int[] packages, int left) {

        int right = packages.length - 1, ans = left - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (packages[mid] <= capacity) {
                ans = mid;
                left = mid + 1;
            } else right = mid - 1;
        }

        return ans;
    }

}
