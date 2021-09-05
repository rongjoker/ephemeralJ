package org.rongjoker.dp.decide;


import java.util.Arrays;
import org.junit.Test;

/**
 * @date 09/05/2021
 *
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以用若干个 互不相同的质数 相乘得到，那么我们称它为 好子集 。
 *
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
 *
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-number-of-good-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * 质数只可能比数组里的数字小
 *
 */
public class NumberOfGoodSubsets {


    @Test
    public void testNumberOfGoodSubsets(){


    }

    /**
     * 全部子集压缩进来
     * @param nums
     * @return
     */
    public int[]  initialize(int[] nums){
        int len = nums.length;
        int max = 1<<len;
        int[] dp = new int[max];
        final int INF = 20;//最大长度为14，故设置为20即可
        Arrays.fill(dp, INF);
        return dp;

    }


    public int numberOfGoodSubsets(int[] nums) {

        int[] awkward = new int[]{2,3,5,7,11,13};
        int[] others = new int[]{17,19,23,29};


        int[] dp = initialize(awkward);




        return 0;




    }




}
