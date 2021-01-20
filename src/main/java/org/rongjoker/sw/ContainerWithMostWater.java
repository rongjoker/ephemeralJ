package org.rongjoker.sw;

import org.junit.Test;

/**
 * @date 01/19/2021
 * 11. 盛最多水的容器  https://leetcode-cn.com/problems/container-with-most-water/
 * <p>
 * 双指针
 */
public class ContainerWithMostWater {


    @Test
    public void test11() {

        int[] height = {1, 2, 1};


        System.out.println(maxArea(height));


    }


    /**
     * 双指针，思路类似快速排序
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0, left = 0, right = height.length - 1, temp;
        while (right > left) {

            if (height[left] <= height[right]) {
                temp = height[left];
                max = Math.max(height[left] * (right - left), max);
                while (temp > height[left + 1] && right > left) {
                    left++;
                }

                if (right > left + 1) {
                    left++;
                } else break;
            } else {
                temp = height[right];
                max = Math.max(height[right] * (right - left), max);
                while (temp > height[right - 1] && right > left) {
                    right--;
                }

                if (right - 1 > left) {
                    right--;
                } else break;
            }

        }


        return max;

    }

    public int maxAreaOptimize(int[] height) {
        int max = 0, left = 0, right = height.length - 1, leftTemp, rightTemp;
        while (right > left) {
            leftTemp = height[left];
            rightTemp = height[right];

            if (height[left] <= height[right]) {
                max = Math.max(height[left] * (right - left), max);
                while (leftTemp > height[left + 1] && right > left) {
                    left++;
                }

                if (right > left + 1) {
                    left++;
                } else break;
            } else {
                max = Math.max(height[right] * (right - left), max);
                while (rightTemp > height[right - 1] && right > left) {
                    right--;
                }

                if (right - 1 > left) {
                    right--;
                } else break;
            }

        }


        return max;

    }
}
