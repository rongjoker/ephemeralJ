package org.rongjoker.binarysearch;

/**
 * @date 04/26/2021
 * <p>
 * 1011. 在 D 天内送达包裹的能力 https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 * <p>
 * 典型的非命中式 二分查找，不是精准匹配情况下确定left和right的范围
 */
public class CapacityToShipPackagesWithinDDays1011 {


    public int shipWithinDays(int[] weights, int D) {
        int sum = 0, max = 0;
        for (int w : weights) {
            sum += w;
            max = Math.max(w, max);
        }
        int left = max, right = sum, middle, tempd, tempv;
        while (left < right) {
            middle = left + ((right - left) >> 1);
            tempd = 1;
            tempv = 0;
            for (int w : weights) {
                if (tempv + w > middle) {
                    tempv = 0;
                    tempd++;
                }
                tempv += w;
                if (tempd > D) break;
            }
            if (tempd > D) left = middle + 1;
            else right = middle;

        }

        return left;


    }
}
