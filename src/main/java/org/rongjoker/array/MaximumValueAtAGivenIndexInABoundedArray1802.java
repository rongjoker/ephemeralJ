package org.rongjoker.array;

/**
 * @date 04/15/2021
 * 1802. 有界数组中指定下标处的最大值 https://leetcode-cn.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/
 *  叠罗汉塔的方式，铺满后剩下的做地基
 *
 *      //   1
 *     //  111
 *     // 111111
 *
 */
public class MaximumValueAtAGivenIndexInABoundedArray1802 {


    //叠罗汉塔的方式，铺满后剩下的做地基
    //第一个就是从index位置叠1个，下面不停的左右扩散1-3-5，某一边撞墙则那边停止
    //   1
    //  111
    // 111111
    public int maxValue(int n, int index, int maxSum) {

        int left = index, right = index;
        int result = 1;
        // 整个数组一开始全部填充为1，
        // rest记录先全部填充1后，剩下1的个数
        int rest = maxSum - n;
        while (left > 0 || right < n - 1) {
            int len = right - left + 1;
            if (rest >= len) {
                // 当前[l,r]范围全部+1
                rest -= len;
                result++;
                // 往左右两边扩
                left = Math.max(0, left - 1);//撞墙停止
                right = Math.min(n - 1, right + 1);//撞墙停止
            } else {
                break;
            }
        }
        // 从小向大叠罗汉塔，剩余的可以用来铺地基，能铺多少是多少
        result += rest / n;
        return result;


    }
}
