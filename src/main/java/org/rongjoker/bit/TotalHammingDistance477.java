package org.rongjoker.bit;

import org.junit.Test;

/**
 *
 *  @date 05/28/2021
 * 477. 汉明距离总和 https://leetcode-cn.com/problems/total-hamming-distance/
 * 类似层次遍历
 *
 */
public class TotalHammingDistance477 {


    @Test
    public void test477(){
        System.out.println(totalHammingDistance2(new int[]{4,14,2}));
    }


    /**
     * 优化的2个地方，一处为长度写死了30位，一处为位运算优化，减少移动的次数
     * @param nums
     * @return
     */
    public int totalHammingDistance3(int[] nums) {
        int[][] array= new int[30][2];
        int ans = 0;
        for(int num:nums){
            for(int i = 0;i<30;++i){
                int cur =  num &1;
                array[i][cur]++;
                ans += array[i][cur^1];
                num >>=1;//每次只需要移动一个位置
            }
        }

        return ans;


    }


    public int totalHammingDistance2(int[] nums) {
        int max = 0;
        for(int num:nums){
            max = Math.max(max,num);
        }
        int len = Integer.toBinaryString(max).length();
        int[][] array= new int[len+1][2];
        int ans = 0;
        for(int num:nums){
            for(int i = len;i>=0;--i){
                int cur =  num >> i &1;
                array[i][cur]++;
                ans += array[i][cur^1];
            }
        }

        return ans;
    }

}
