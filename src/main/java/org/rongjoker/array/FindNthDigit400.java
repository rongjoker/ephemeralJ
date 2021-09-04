package org.rongjoker.array;

import org.junit.Test;

/**
 * @date 09/04/2021
 *
 * 400. 第 N 位数字
 *
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 位数字。
 *
 *  
 *
 * 注意：n 是正数且在 32 位整数范围内（n < 231）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 数学类题目
 */
public class FindNthDigit400 {


    @Test
    public void test400() {

        //        System.out.println(findNthDigit(9));
        System.out.println(findNthDigit(11));
        //        System.out.println(findNthDigit(188881));

    }

    public int findNthDigit(int n) {
        int section_cnt = 9;        //这个长度的数字，有多少个
        int num_len = 1;            //每个数字的长度
        while ((long) section_cnt * num_len < n) {
            n -= section_cnt * num_len;
            section_cnt *= 10;
            num_len++;
        }

        section_cnt /= 9; //计算出进制，比如10-99,section_cnt是90，进制是90/9=10
        int target_num = section_cnt + (n - 1) / num_len;//找到那个数字,从进制算起，要-1，比如11，是从10算起，所以-9后=2，为(2-1)即10开始的第一个
        int idx = (n - 1) % num_len;//目标数字的第几位
        return ("" + target_num).charAt(idx) - '0';

    }

}
