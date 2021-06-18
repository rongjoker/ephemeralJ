package org.rongjoker.binarysearch;


import org.junit.Test;

import java.util.Arrays;

/**
 * @date 06/16/2021
 *  1760. 袋子里最少数目的球 https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag/
 *
 *给你一个整数数组 nums ，其中 nums[i] 表示第 i 个袋子里球的数目。同时给你一个整数 maxOperations 。
 *
 * 你可以进行如下操作至多 maxOperations 次：
 *
 * 选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 正整数 个球。
 * 比方说，一个袋子里有 5 个球，你可以把它们分到两个新袋子里，分别有 1 个和 4 个球，或者分别有 2 个和 3 个球。
 * 你的开销是单个袋子里球数目的 最大值 ，你想要 最小化 开销。
 *
 * 请你返回进行上述操作后的最小开销。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 极小化极大类型题目
 *
 */
public class MinimumSize1760 {


    @Test
    public void test1760(){

        System.out.println(minimumSize(new int[]{9},2));
        System.out.println(minimumSize(new int[]{2,4,8,2},4));
        System.out.println(minimumSize(new int[]{7,17},2));


    }


    /**
     * 一遍过，不过有优化的空间
     * @param nums
     * @param maxOperations
     * @return
     */
    public int minimumSize(int[] nums, int maxOperations) {

        Arrays.sort(nums);

        int max =0;
        for (int num : nums) max = Math.max(max,num);
        int left = 1,right = max,ans = max;
        while(left<=right){
            int mid = left + ((right - left)>>1);
            if(check(nums,maxOperations,mid)){
                right = mid - 1;
                ans = Math.min(ans,mid);
            }else left = mid +1;

        }
        return ans;

    }

    public boolean check(int[] nums, int maxOperations,int min){
        int count = 0;

        for (int num : nums) {
            if(num>min){
                count+= (num/min);
                if(num%min==0)count--;
            }

            if(count>maxOperations)return false;

        }

        return true;
    }



}
