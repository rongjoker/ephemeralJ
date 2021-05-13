package org.rongjoker.binarysearch;

/**
 * @date 05/13/2021
 * 29. 两数相除 https://leetcode-cn.com/problems/divide-two-integers/
 * 二分
 *
 */
public class DivideTwoIntegers29 {

    /**
     * 两数相除
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return 商（不包含小数）
     */
    public int divide(int dividend, int divisor) {
        long ans = 0;
        long x = dividend;
        long y = divisor;
        boolean negative = (x < 0 && y > 0) || (x > 0 && y < 0);

        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        // 由于x/y的结果肯定在[0,x]范围内，所以对x使用二分法
        long left = 0;
        long right = x;
        while (left < right) {
            long mid = left + right + 1 >> 1;
            if (quickMulti(mid, y) <= x) {
                // 相乘结果不大于x，左指针右移
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        ans = negative ? -left : left;

        // 判断是否溢出
        if (ans < Integer.MIN_VALUE || ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int)ans;
    }

    /**
     * 快速乘法
     *
     * @param a 乘数
     * @param b 被乘数
     * @return 积
     */
    public  long quickMulti(long a, long b) {
        long result = 0;

        while (b > 0) {
            if ((b & 1) == 1) {
                // 当前最低位为1，结果里加上a
                result += a;
            }
            // 被乘数右移1位，相当于除以2
            b >>= 1;
            // 乘数倍增，相当于乘以2
            a += a;
        }

        return result;
    }

}
