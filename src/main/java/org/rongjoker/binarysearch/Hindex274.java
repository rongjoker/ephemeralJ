package org.rongjoker.binarysearch;


import org.junit.Test;

import java.util.Arrays;

/**
 * @date 07/11/2021
 * 274. H 指数 https://leetcode-cn.com/problems/h-index/
 */
public class Hindex274 {


    @Test
    public void test274(){

        System.out.println(hIndex(new int[]{0,0,0,0}));
        System.out.println(hIndex(new int[]{0,1}));
        System.out.println(hIndex(new int[]{11,15}));
        System.out.println(hIndex(new int[]{3,0,6,1,5}));


    }

    public int hIndex(int[] citations) {

        int len = citations.length;
        if(len==0)return 0;

        if(len==1)return citations[0]>=1?1:0;

        Arrays.sort(citations);
        int left=1,right=len,ans=0;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            if(citations[len-mid]>=mid){
                ans = Math.max(ans,mid);
                left = mid+1;
            }else right = mid-1;
        }

        return ans;
    }
}
