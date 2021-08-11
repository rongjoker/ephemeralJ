package org.rongjoker.bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 求数组中比左边元素都大同时比右边元素都小的元素，返回这些元素的索引
 *
 * 要求时间复杂度 o(n)
 *
 *
 */
public class FindMiddle {


    @Test
    public void testFind(){

        System.out.println(find(new int[]{2, 3, 1, 8, 9, 20, 12}));
        System.out.println(find2(new int[]{2, 3, 1, 8, 9, 20, 12}));

    }




    //求数组中比左边元素都大同时比右边元素都小的元素，返回这些元素的索引
    public List<Integer> find(int[] nums){

        int len = nums.length;

        List<Integer> ans = new ArrayList<>();

        int[] max_left = new int[len];
        max_left[0] = nums[0];
        for (int i = 1; i < len; i++) max_left[i] = Math.max(max_left[i-1],nums[i-1]);
        int min_right = nums[len-1];
        for (int i = len-2; i >=0 ; i--) {
            min_right = Math.min(min_right,nums[i+1]);
            if(nums[i]> max_left[i] && nums[i]<min_right)ans.add(i);
        }

        return ans;
    }

    //从左向右的版本
    public List<Integer> find2(int[] nums){

        int len = nums.length;

        List<Integer> ans = new ArrayList<>();

        int[] min_right = new int[len];
        min_right[len-1] = nums[len-1];
        for (int i = len-2; i >=0 ; i--) min_right[i] = Math.min(min_right[i+1],nums[i+1]);

        int max_left = nums[0];
        for (int i = 1; i < len; i++) {
            max_left = Math.max(max_left,nums[i-1]);
            if(nums[i]> max_left && nums[i]<min_right[i])ans.add(i);
        }

        return ans;
    }


}
