package org.rongjoker.array;

import org.junit.Test;

/**
 *  @date 08/10/2021
 *
 * 413. 等差数列划分 https://leetcode-cn.com/problems/arithmetic-slices/
 *
 *
 *
 */
public class NumberOfArithmeticSlices413 {

    @Test
    public void test413(){
        System.out.println(numberOfArithmeticSlices(new int[]{1,2,3}));
        System.out.println(numberOfArithmeticSlices(new int[]{1,2,3,5,7}));
        System.out.println(numberOfArithmeticSlices(new int[]{1,2,3,4,7,8,9,10}));

    }


    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if(len<3)return 0;
        int ans = 0;

        for (int i = 0; i <= len - 3;) {
            int j = i+1;
            int gap = nums[j] - nums[i];

            while(nums[j] - nums[j-1] == gap){
                j++;
                if(j==len)break;
            }
            if(j-i>=3){
                ans += ((j-i-2)*(j-i-1)/2);
                i = j-1;//此处是关键，从当前截止后，可以从当前开始新的运算
            }else i++;
            if(j==len)break;

        }
        return  ans;

    }
}
