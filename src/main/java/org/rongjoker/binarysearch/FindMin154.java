package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 04/05/2021
 * 154. 寻找旋转排序数组中的最小值 II https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * 二分查找一个被破坏过的有序数组的最小值
 * 二分查找，注意左边界和右边界的边界条件
 * 有重复数据,所以遇到重复数据要分割为左右两边继续比较
 *
 * 04/09/2021 每日一题 用新方案
 *
 */
public class FindMin154 {


    @Test
    public void test154(){
        int[] numbers = {1,3,5};
        System.out.println(findMin(numbers));
        System.out.println(findMin2(numbers));

    }


    public int findMin2(int[] nums) {
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.min(nums[0],nums[1]);
        int len=nums.length;

        int minK = findMinK2(nums, 0, len - 1, len);

        return nums[minK];


    }


    /**
     * 新的方案，效果更好
     *
     * @param nums
     * @param left
     * @param right
     * @param len
     * @return
     */
    public int findMinK2(int[] nums,int left,int right,int len) {
        if(left<0 || right>len-1) return -1;

        if (right>left){
            int middle = left + ((right - left)>>1);

            if(nums[middle]>nums[right]){
                return findMinK2(nums,middle+1,right,len);
            }else if(nums[middle]<nums[right]){
                return findMinK2(nums,left,middle,len);
            }else {
                int r =  findMinK2(nums,middle+1,right,len);
                int l = findMinK2(nums,left,middle,len);
                return nums[r]>nums[l]?l:r;
            }
        }

        return left;


    }



    public int findMin(int[] nums) {
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.min(nums[0],nums[1]);
        int len=nums.length;

        int minK = findMinK(nums, 0, len - 1, len);

        return minK!=-1?minK:nums[0];


    }


    public int findMinK(int[] nums,int left,int right,int len) {
        if(left<0 || right>len-1) return -1;

        if (right>=left){
            int middle = left + ((right - left)>>1);

            if(nums[middle]>nums[0]){
                if(middle==len-1) return nums[0];
                if(nums[middle+1]<=nums[0])return nums[middle+1];
                else return findMinK(nums,middle+1,right,len);
            }else if(nums[middle]<nums[0]){
                if(nums[middle-1]>=nums[0])return nums[middle];
                else return findMinK(nums,left,middle-1,len);
            }else {
                int tem = findMinK(nums,left,middle-1,len);
                if(tem==-1) return findMinK(nums,middle+1,right,len);
                else return tem;
            }
        }

        return -1;


    }


}
