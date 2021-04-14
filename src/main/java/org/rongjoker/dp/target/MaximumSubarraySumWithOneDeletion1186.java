package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 04/14/2021
 * 1186. 删除一次得到子数组最大和 https://leetcode-cn.com/problems/maximum-subarray-sum-with-one-deletion/
 * 53. 最大子序和 的升级版本
 * 要么删除，要么不删除；删除里包含删除当前或者从之前删除过的继承
 *
 */
public class MaximumSubarraySumWithOneDeletion1186 {


    @Test
    public void test1186(){

        //[2,1,-2,-5,-2]
        System.out.println(maximumSum(new int[]{-1,-1,-1,-1}));
        System.out.println(maximumSum(new int[]{1,-2,-2,3}));
        System.out.println(maximumSum(new int[]{2,1,-2,-5,-2}));


    }



    public int maximumSum(int[] arr) {

        int len = arr.length;

        if(len==1)return arr[0];

        int max = arr[0],max_delete=0,max_no_delete=max;

        for(int i=1;i<len;++i){
            max_delete = Math.max(max_delete<0?arr[i]:max_delete+arr[i],max_no_delete);

            if(max_no_delete<0)max_no_delete=arr[i];
            else max_no_delete+=arr[i];

            max = Math.max(max,Math.max(max_delete,max_no_delete));
        }




        return max;


    }




}
