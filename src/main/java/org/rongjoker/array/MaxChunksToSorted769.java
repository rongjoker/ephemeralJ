package org.rongjoker.array;

import org.junit.Test;

/**
 * @date 07/07/2021
 * 769. 最多能完成排序的块 https://leetcode-cn.com/problems/max-chunks-to-make-sorted/
 *
 */
public class MaxChunksToSorted769 {


    @Test
    public void test769(){
        System.out.println(maxChunksToSorted(new int[]{2,0,1,3}));
        System.out.println(maxChunksToSorted(new int[]{1,2,0,3}));
    }

    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) ans++;
        }
        return ans;
    }
}
