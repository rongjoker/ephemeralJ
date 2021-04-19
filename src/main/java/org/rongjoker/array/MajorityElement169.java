package org.rongjoker.array;

import org.junit.Test;

/**
 *
 * @date 04/19/2021
 *
 * 169. 多数元素 https://leetcode-cn.com/problems/majority-element/
 *
 * 摩尔投票法：
 *
 * 核心就是对拼消耗。
 *
 * 玩一个诸侯争霸的游戏，假设你方人口超过总人口一半以上，并且能保证每个人口出去干仗都能一对一同归于尽。最后还有人活下来的国家就是胜利。
 *
 * 那就大混战呗，最差所有人都联合起来对付你（对应你每次选择作为计数器的数都是众数），或者其他国家也会相互攻击（会选择其他数作为计数器的数），但是只要你们不要内斗，最后肯定你赢。
 *
 * 最后能剩下的必定是自己人。
 *
 */
public class MajorityElement169 {


    @Test
    public void test169(){
        System.out.println(majorityElement(new int[]{10,9,9,9,10}));
    }


    public int majorityElement(int[] nums) {
        int count = 1;
        int temp = nums[0];
        for(int i =1;i<nums.length;++i){
            if(nums[i] == temp)++count;
            else {
                --count;
                if(count<0){
                    temp = nums[i];
                    ++count;
                }
            }

        }
        return temp;

    }




}
