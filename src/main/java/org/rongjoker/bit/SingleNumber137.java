package org.rongjoker.bit;


import org.junit.Test;

/**
 * @date 05/18/2021
 * 137. 只出现一次的数字 II https://leetcode-cn.com/problems/single-number-ii/
 * 把每个数字的二进制分的每个位数相加除3取余数
 */
public class SingleNumber137 {


    @Test
    public void test137(){
        System.out.println(singleNumber(new int[]{2,2,3,2}));
        System.out.println(singleNumber(new int[]{0,1,0,1,0,1,99}));
        System.out.println(singleNumber(new int[]{-2,-2,1,1,4,1,4,4,-4,-2}));
    }


    /**
     * 可能存在负数，故最好直接用32确定范围
     * 其实就是把每个数字的二进制分的每个位数相加除3取余数，就是剩余的值
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
//        int max = 0;
//        for(int num:nums){
//            max = Math.max(max,num);
//        }
//        max = Integer.toBinaryString(max).length();
        int ans = 0,cur;
        for (int i = 32;i>=0;--i){
            ans <<= 1;
            cur = 0;
            for(int num:nums){
                cur += (num >>i &1);
            }
            ans += (cur%3);
        }
        return ans;

    }



}
