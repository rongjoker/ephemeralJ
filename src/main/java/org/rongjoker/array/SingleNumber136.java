package org.rongjoker.array;

import org.junit.Test;

import java.util.HashSet;

/**
 * @date 04/10/2021
 * 136. 只出现一次的数字 https://leetcode-cn.com/problems/single-number/
 *
 *
 */
public class SingleNumber136 {

    @Test
    public void test136(){
        int[] nums = new int[]{4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }


    public int singleNumber(int[] nums) {
        HashSet<Integer> map = new HashSet<>();

        for(int i:nums){
            if(map.contains(i))map.remove(i);
            else map.add(i);
        }

        return map.iterator().next();

    }
}
