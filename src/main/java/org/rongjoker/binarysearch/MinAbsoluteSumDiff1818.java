package org.rongjoker.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 04/14/2021
 * 1818. 绝对差值和 https://leetcode-cn.com/problems/minimum-absolute-sum-difference/
 *
 * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
 *
 * 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
 *
 * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
 *
 * 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-sum-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解法，1，先求出普通的不替换情况下的绝对差的和；
 * 2，然后计算每个位置进行替换的最小值，不替换的值-替换的值，也就是最大的收益
 * 3循环出最大的那个收益，将绝对值的和减去最大收益就是结果
 * 比赛的时候，第二步用的是暴力算法，超时未通过，用二分查找可以将复杂度从n降低到logn，本质上是找到与nums2[i]最接近的值，这符合二分查找的定义
 * 找最接近的值，是个技术活，需要注意的点很多，由于不是找完全一样的，所以需要考虑左边和右边，而且要考虑left==right的情况，以及越界
 *
 *
 */
public class MinAbsoluteSumDiff1818 {

    @Test
    public void test1818(){

//        int[] nums1 = {38,48,73,55,25,47,45,62,15,34,51,20,76,78,38,91,69,69,73,38,74,75,86,63,73,12,100,59,29,28,94,43,100,2,53,31,73,82,70,94,2,38,50,67,8,40,88,87,62,90,86,33,86,26,84,52,63,80,56,56,56,47,12,50,12,59,52,7,40,16,53,61,76,22,87,75,14,63,96,56,65,16,70,83,51,44,13,14,80,28,82,2,5,57,77,64,58,85,33,24}
//        ,nums2 = {90,62,8,56,33,22,9,58,29,88,10,66,48,79,44,50,71,2,3,100,88,16,24,28,50,41,65,59,83,79,80,91,1,62,13,37,86,53,43,49,17,82,27,17,10,89,40,82,41,2,48,98,16,43,62,33,72,35,10,24,80,29,49,5,14,38,30,48,93,86,62,23,17,39,40,96,10,75,6,38,1,5,54,91,29,36,62,73,51,92,89,88,74,91,87,34,49,56,33,67}
//;

//        int[] nums1 ={81,59,66,42,90,53,2,100,48,61,31,88,24,15,57,33,54,98,15,38,93,63,85,76,39,48,84,66,13,90,80,20,21,35,39,45,43,77,32,85,59,17,69,16,66,37,5,4,89,87,26,39,97,35,52,39,69,92,76,8,67,56,26,82,95,48,71,60,98,93,96,14,13,46,70,51,55,91,51,76,31,67,43,12,83,23,57,54,30,65,58,18,95,7,57,16,12,41,86,3}
//,nums2 = {73,2,7,44,82,86,24,7,54,74,91,77,75,25,77,19,16,15,73,23,80,43,83,10,39,39,68,84,5,71,64,76,35,42,7,45,55,47,20,14,24,73,61,71,76,72,93,53,53,26,5,42,36,7,28,43,65,34,19,72,65,97,1,91,58,1,39,18,78,11,14,88,12,96,36,28,74,62,27,2,62,85,94,48,66,7,90,79,47,88,54,79,37,39,63,34,46,83,65,10}
//;

        int[] nums1 ={38,48,73,55,25,47,45,62,15,34,51,20,76,78,38,91,69,69,73,38,74,75,86,63,73,12,100,59,29,28,94,43,100,2,53,31,73,82,70,94,2,38,50,67,8,40,88,87,62,90,86,33,86,26,84,52,63,80,56,56,56,47,12,50,12,59,52,7,40,16,53,61,76,22,87,75,14,63,96,56,65,16,70,83,51,44,13,14,80,28,82,2,5,57,77,64,58,85,33,24}
                ,nums2 = {90,62,8,56,33,22,9,58,29,88,10,66,48,79,44,50,71,2,3,100,88,16,24,28,50,41,65,59,83,79,80,91,1,62,13,37,86,53,43,49,17,82,27,17,10,89,40,82,41,2,48,98,16,43,62,33,72,35,10,24,80,29,49,5,14,38,30,48,93,86,62,23,17,39,40,96,10,75,6,38,1,5,54,91,29,36,62,73,51,92,89,88,74,91,87,34,49,56,33,67}
                ;
        System.out.println(minAbsoluteSumDiff(nums1,nums2));
        System.out.println(minAbsoluteSumDiff2(nums1,nums2));
//        System.out.println(minAbsoluteSumDiff2(new int[]{1,7,5},new int[]{2,3,5}));

//        ans:3000079024
//        max:99746

        long n1 = 3000079024L;
        int mod = (int)(Math.pow(10, 9) + 7);

        System.out.println(n1%1000000007);
        System.out.println(n1%mod);

    }


    public int minAbsoluteSumDiff2(int[] nums1, int[] nums2){
        int len = nums1.length;
        int[] copies = new int[len];
        System.arraycopy(nums1, 0, copies, 0, len);
        Arrays.sort(copies);
        long ans = 0;
        int M = 1000000007;
        int max = 0;

        for(int i=0;i<len;++i){
            if(nums2[i]!=nums1[i]){
                int temp = Math.abs(nums2[i] - nums1[i]);
                ans+= temp;
                max = Math.max(Math.abs(temp - dif(copies,nums2[i])),max);
            }
        }
        ans-=max;
        ans%=M;//不可提前取余数会出错

        return (int)ans;
    }

    /**
     * 找到最接近的值的差值
     *
     * @param copies
     * @param target
     * @return
     */
    public int dif(int[] copies,int target){
        int len = copies.length;
        int left = 0,right = len-1;
        int left_val = copies[left],right_val=copies[right];
        while (left<=right){
            int mid = left + ((right-left)>>1);
            if(copies[mid]==target)return 0;
            else if (copies[mid]<target){
                left_val = copies[mid];
                left = mid+1;
            }else {
                right_val = copies[mid];
                right = mid-1;
            }
        }

        return Math.min(Math.abs(left_val - target),Math.abs(right_val - target));

    }




    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

        int n = nums1.length;

        int[] nums11 = new int[n];
        System.arraycopy(nums1, 0, nums11, 0, n);
        Arrays.sort(nums11);


        long sum = 0;

        int difference=0,temp;

        for(int i=0;i<n;++i){
            if(nums1[i]!=nums2[i]){
                temp = Math.abs(nums1[i]-nums2[i]);
                sum+=temp;
                difference = Math.max(difference,Math.abs(temp - calculate(nums11,nums2[i],0,n-1)));
            }

        }

        System.out.println("difference:"+difference);

        int mod = (int)(Math.pow(10, 9) + 7);

        sum-=difference;


        return (int)(sum % mod);


    }

    //找最接近的值，是个技术活，需要注意的点很多，由于不是找完全一样的，所以需要考虑左边和右边，而且要考虑left==right的情况，以及越界

    public int calculate(int[] nums11,int target,int left,int right){
        int middle,left_val,right_val;
        left_val = nums11[left];right_val=nums11[right];
        while(left<=right){

            middle = left + ((right - left)>>1);

            if(nums11[middle]>target){
                right_val = nums11[middle];
                right=middle-1;
            }
            else if(nums11[middle]<target){
                left_val = nums11[middle];
                left=middle+1;
            }
            else return 0;
        }

        return Math.min(Math.abs(left_val - target),Math.abs(right_val - target));


    }


}
