package org.rongjoker.bit;

/**
 *  @date 04/19/2021
 *  5719. 每个查询的最大异或值 https://leetcode-cn.com/problems/maximum-xor-for-each-query/
 *
 *
 * # 利用自反性和python操作数组的便利性 0 <= nums[i] < 2maximumBit
 * # 由于 0 <= nums[i] < 2maximumBit 所以每个数字能运算的最大数字就是2maximumBit -1 ，所以就是把数字n ^ (2maximumBit -1 ) 即可
 * # 也就是n ^ k = (2maximumBit -1 )  => k = n ^ (2maximumBit -1 )
 * # 异或运算满足交换律和结合律
 */
public class MaximumXorForEachQuery5719 {

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int max = (1<<maximumBit) -1;
        int len = nums.length;
        int current = 0;
        for (int num : nums) {
            current ^= num;
        }
        int[] ans = new int[len];

        for (int i=len-1;i>=0;--i){
            ans[len - i -1] = max ^ current;
            current ^= nums[i];
        }
        return ans;

    }
}
