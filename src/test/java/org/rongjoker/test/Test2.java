package org.rongjoker.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 删除有序数组中的重复项
 *
 *
 */
public class Test2 {


    @Test
    public void testRemoveRepeatArray(){
        System.out.println(Arrays.toString(removeRepeatArray(new int[]{1, 1, 1, 2, 3, 4, 4, 5})));
        System.out.println(Arrays.toString(removeRepeatArray(new int[]{1, 2})));
    }



    public int[] removeRepeatArray(int[] nums){
        int len = nums.length;
        if(len<=1)return nums;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < len; i++) {
            if(nums[i]!=nums[i-1])list.add(nums[i]);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) ans[i] = list.get(i);

        return ans;
    }
}
