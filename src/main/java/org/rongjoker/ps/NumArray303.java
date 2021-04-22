package org.rongjoker.ps;

/**
 * @date 03/02/2021
 * 303. 区域和检索 - 数组不可变  https://leetcode-cn.com/problems/range-sum-query-immutable/
 * <p>
 * 前缀和入门
 * 记录前缀的和，而不是当前位置的和
 * 比如prefix[1]记录的是nums[0]；prefix[0]记录的是0，即无前缀；
 */
public class NumArray303 {


    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray303 obj = new NumArray303(nums);
        System.out.println(obj.sumRange(0, 2));
        System.out.println(obj.sumRange(2, 5));
        System.out.println(obj.sumRange(0, 5));
    }


    int[] ps;


    public NumArray303(int[] nums) {
        int len = nums.length;
        ps = new int[len + 1];

        for (int i = 0; i < len; i++) {
            ps[i + 1] = ps[i] + nums[i];
        }

    }

    public int sumRange(int i, int j) {
        return ps[j + 1] - ps[i];
    }


}
