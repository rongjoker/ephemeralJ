package org.rongjoker.array;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 *
 */
public class SortColors75 {


    @Test
    public void test75(){

        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));

    }

    public void sortColors(int[] nums) {
        int part2 = nums.length;
        if (part2 < 2 ) return ;
        int left = 0;
        int part0 = 0;
        while (left < part2){//右边已经排序过了，不需要再次排序
            if (nums[left] == 0){
                if (left!=part0)
                    swap(nums,left,part0);
                left++;
                part0++;
            }else if (nums[left] == 1){
                left++;
            } else{
                part2--;
                swap(nums,left,part2);
            }
        }


    }

    public void swap(int[] nums,int le,int r){
        int temp = nums[le];
        nums[le] = nums[r];
        nums[r] = temp;
    }
}
