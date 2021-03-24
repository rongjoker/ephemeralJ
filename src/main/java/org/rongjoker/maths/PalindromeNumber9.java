package org.rongjoker.maths;

import org.junit.Test;

/**
 * @date 03/24/2021
 * 9. 回文数 https://leetcode-cn.com/problems/palindrome-number/
 *
 * 直接双指针，或者利用前一半数字和后一半数字反转比较是否相等
 *
 *
 */
public class PalindromeNumber9 {


    @Test
    public void test9(){
//        System.out.println(isPalindromeMath(12221));
        System.out.println(isPalindromeMath(0));

    }



    //双指针的解法
    public boolean isPalindrome(int x) {

        if(x<0)
            return false;

        if(x<10)
            return true;

        String temp = String.valueOf(x);
        int len = temp.length();

        int left=0,right=len-1;

        while (left<right){
            if(temp.charAt(left)!=temp.charAt(right))
                return false;

            ++left;
            --right;
        }

        return true;

    }

    public boolean isPalindromeMath(int x) {

        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;



    }


}
