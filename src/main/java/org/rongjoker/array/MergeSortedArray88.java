package org.rongjoker.array;


import org.junit.Test;

import java.util.Arrays;

/**
 * @date 04/05/2021
 * 88. 合并两个有序数组 https://leetcode-cn.com/problems/merge-sorted-array/
 * 合并算法
 *
 *
 */
public class MergeSortedArray88 {

    @Test
    public void test88(){

        int[] nums1 = {1,2,3,0,0,0},nums2 = {2,5,6};int  m = 3, n = 3;

        merge(nums1,m,nums2,n);

        System.out.println(Arrays.toString(nums1));
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0)return;

        int p = nums1.length-1;m--;n--;
        while(p>=0 && n>=0){

            while (m>-1 && nums1[m] > nums2[n]) {
                nums1[p--] = nums1[m--];
            }
                nums1[p--]=nums2[n--];
        }
    }

}
