package org.rongjoker.sw;

import org.junit.Test;

/**
 *  42. 接雨水 https://leetcode-cn.com/problems/trapping-rain-water/
 *  双指针，先从左到右扫，依次减去前一个的高度，遇到比左边高的，停止，记录面积，然后重新左指针更新，继续向右，如果扫描到达最右边，左指针未到达最右边，则从最右边向左指针重复上述操作
 *
 *
 */
public class TrappingRainWater42 {

    @Test
    public void test42() {

        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));


    }


    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) return 0;
        int left = 0, right = len - 1, index = 1, sum = 0, temp = 0;
        while (index <= right) {
            temp += (height[left] - height[index - 1]);//容器面积
            if (height[index] >= height[left]) {
                sum += temp;
                temp = 0;
                left = index;
            }
            ++index;
        }


        index = right - 1;
        temp = 0;
        while (index >= left) {
            temp += (height[right] - height[index + 1]);//容器面积
            if (height[index] > height[right]) {
                sum += temp;
                temp = 0;
                right = index;
            }
            --index;
        }

        return sum;

    }
}
