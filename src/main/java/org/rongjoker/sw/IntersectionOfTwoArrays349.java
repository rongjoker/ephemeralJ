package org.rongjoker.sw;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  @date 02/19/2021
 *  349. 两个数组的交集  https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *  双指针题目
 *
 *
 */
public class IntersectionOfTwoArrays349 {



    @Test
    public void test349() {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {2,3};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }


    public int[] intersection(int[] nums1, int[] nums2) {

        int len1=nums1.length,len2=nums2.length;

        if(len1==0 || len2==0)
            return new int[0];


        Set<Integer> set = new HashSet<>();

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int index1=0,index2=0;

        while (index1<len1 && index2<len2){
            if(nums1[index1]==nums2[index2]){
                set.add(nums1[index1]);
                ++index1;
                ++index2;
            }else if(nums1[index1]<nums2[index2]){
                ++index1;
            }else if(nums1[index1]>nums2[index2]){
                ++index2;
            }

        }

        len1 = set.size();
        if(len1==0)
            return new int[0];

        int[] array =new int[len1];
        Iterator<Integer> iterator = set.stream().iterator();
        for (int i = 0; i < len1; i++) {
            array[i]=iterator.next();
        }

        return array;

    }


}
