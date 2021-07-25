package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 04/04/2021
 * @date 04/05/2021
 * 33. 搜索旋转排序数组 https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * 二分查找，注意左边界和右边界的边界条件
 * 官方的解法更快
 *
 */
public class SearchInRotatedSortedArray33 {


    @Test
    public void test33(){

//        System.out.println(search2(new int[]{4, 5, 6, 7, 0, 1, 2},0));
//        System.out.println(search2(new int[]{4,5,6,7,0,1,2},3));
//        System.out.println(search2(new int[]{1},0));
//        System.out.println(search2(new int[]{1,3},3));
//        System.out.println(search3(new int[]{1,3},3));
//        System.out.println(search3(new int[]{1,3},1));
//        System.out.println(search3(new int[]{4,5,6,7,0,1,2},3));
        System.out.println(search4(new int[]{5,5,5,1,2,3,4,5},5));

        //[5,5,5,1,2,3,4,5]
        //5

    }


    /**
     *
     * 升级版本，允许数组重复
     * @param arr
     * @param target
     * @return
     */
    public int search4(int[] arr, int target) {
        int left = 0,right = arr.length-1,ans = Integer.MAX_VALUE;

        while(left<=right){
            int mid = left + ((right - left)>>1);
            if(arr[mid]==target){
                ans = Math.min(ans,mid);
                right = mid -1;
            }else if(arr[mid]>=arr[right] && arr[mid]>=arr[left]){
                if(arr[left]<=target && arr[mid]>target)right = mid -1;
                else left = mid +1;

            }else{
                if(arr[mid]<target && arr[right]>=target)left = mid +1;
                else right = mid -1;
            }

        }

        return ans == Integer.MAX_VALUE?-1:ans;

    }


    public int search3(int[] nums, int target) {
        int len = nums.length;
        int left = 0,right = len-1;
        while(left<=right){//除非返回值是nums[left]才能去掉等号
            int mid = left + ((right-left)>>1);
            if(nums[mid]==target)return mid;

            if (nums[mid]>nums[right]){//左边有序
                if(nums[mid]>target && nums[left]<=target)right = mid-1;
                else left = mid+1;
            }else{//右边有序
                if(nums[mid]<target && nums[right]>=target)left = mid+1;
                else right = mid-1;
            }

        }
        return -1;


    }


    public int search2(int[] nums, int target) {
        int left = 0,right = nums.length -1;
        while(left<=right){
            int mid = left + ((right - left)>>1);
            if(nums[mid]==target)return mid;

            if(nums[mid]<nums[right]){
                if(nums[mid]<target && nums[right]>target)left = mid +1;
                else right = mid-1;
            }else{
                if(nums[left]<target && nums[mid]>target)right = mid-1;
                else left = mid+1;

            }
        }


        if(nums[left]==target)return left;
        return -1;


    }


    public int search(int[] nums, int target) {

        if(nums.length==1) return target==nums[0]?0:-1;

        int spit=0,len=nums.length;
        if(nums.length==2) {
            if(nums[0]>nums[1])spit=1;
            else return binarySearch(nums,0,1,target);
        }else {
            int left=0,right=nums.length-1,middle;
            while (right>=left){
                middle = left + ((right - left)>>1);

                if(nums[middle]==target)return middle;

                if(nums[middle]>nums[0]){
                    if(middle==len-1) {
                        break;}
                    if(nums[middle+1]<nums[0]){spit = middle+1;break;}
                    else left = middle+1;
                }else if(nums[middle]<nums[0]){
                    if(nums[middle-1]>nums[0]){spit = middle;break;}
                    else right = middle-1;
                }else {spit = 1;break;}
            }
        }


        if(target<nums[spit] || (spit>0 && target>nums[spit-1]))return -1;

        int index;

        if(spit>0){
            index = binarySearch(nums,0,spit-1,target);
            if(index==-1)return binarySearch(nums,spit,len-1,target);
            else return index;
        }else return binarySearch(nums,0,len-1,target);

    }




    public int binarySearch(int[] nums,int left,int right, int target){
        int middle;
        while (left<=right){
            middle = left + ((right - left)>>1);
            if(nums[middle]>target)right=middle-1;
            else if(nums[middle]<target)left=middle+1;
            else return middle;
        }

        return -1;


    }


    public int searchOptimize(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }


}
