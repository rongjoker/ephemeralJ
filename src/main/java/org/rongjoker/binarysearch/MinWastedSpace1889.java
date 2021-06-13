package org.rongjoker.binarysearch;

import java.util.Arrays;

/**
 * @date 06/13/2021
 * 1889. 装包裹的最小浪费空间 https://leetcode-cn.com/problems/minimum-space-wasted-from-packaging/
 *
 *
 *
 */
public class MinWastedSpace1889 {


    /**
     *
     * 蛮力算法会超时
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
            int m = Arrays.stream(box).max().getAsInt();
            Arrays.sort(box);
            if(m<packages[len-1])continue;
            int start = 0;
            for (int aPackage : packages) {
                while (box[start]<aPackage)++start;
                w+= (box[start] - aPackage);
            }
            ans = Math.min(ans,w);

        }


        return ans== Long.MAX_VALUE ? -1:(int) (ans % 1000000007);
    }


    public int minWastedSpace2(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        long ans = Long.MAX_VALUE;
        int len = packages.length;

        for (int[] box : boxes) {
            long w = 0;
            int m = Arrays.stream(box).max().getAsInt();
            Arrays.sort(box);
            if(m<packages[len-1])continue;
            int start = 0;
            for (int aPackage : packages) {
                while (box[start]<aPackage)++start;
                w+= (box[start] - aPackage);
            }
            ans = Math.min(ans,w);

        }


        return ans== Long.MAX_VALUE ? -1:(int) (ans % 1000000007);
    }
}
