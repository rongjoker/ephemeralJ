package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 04/11/2021
 *
 * 4. 寻找两个正序数组的中位数  https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 *
 */
public class FindMedianSortedArrays4 {


    @Test
    public void test4(){

        int[] nums1 = {1}, nums2 = {};
        System.out.println(findMedianSortedArrays(nums1,nums2));


    }



    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length,n=nums2.length,medium = (m+n)>>1;
        int[] nums3 = new int[medium+1];
        int left=0,right=0,index=0;
        while(index<=medium){
            if(left<m && right <n){
                if(nums1[left]<=nums2[right]){
                    nums3[index] = nums1[left++];
                }else  nums3[index] = nums2[right++];

            }else if(left<m){
                nums3[index] = nums1[left++];

            }else if(right <n){
                nums3[index] = nums2[right++];
            }
            index++;

        }

        if((m+n)%2==1){
            return nums3[medium];
        }else  return (double)(nums3[medium] + nums3[medium-1])/(double)2;

    }
}
