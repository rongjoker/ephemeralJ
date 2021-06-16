package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 *
 * @date 06/16/2021
 *
 * 1898. 可移除字符的最大数目 https://leetcode-cn.com/problems/maximum-number-of-removable-characters/
 * 二分查找，确认好左右边界比较困难
 *
 *
 */
public class MaximumRemovals1898 {

    @Test
    public void test2() {

        System.out.println(maximumRemovals("qlevcvgzfpryiqlwy","qlecfqlw",new int[]{12,5}));
        System.out.println(maximumRemovals("abcacb","ab",new int[]{3,1,0}));
        System.out.println(maximumRemovals("abcbddddd","abcd",new int[]{3,2,1,4,5,6}));
        System.out.println(maximumRemovals("abcab", "abc", new int[]{0, 1, 2, 3, 4}));
        System.out.println(maximumRemovals("kkwiypfzruadoeyfzogmpslfbvrumcrogouomuaidyhqvlaumguqcipcbfkdnp", "kkiyaogslrroadmcb", new int[]{52, 44, 9, 12, 54, 5, 16, 36, 23, 8, 43, 58, 15, 13, 28, 2, 29, 27, 34, 60, 25, 35, 20, 7, 31, 11, 51, 39, 19, 24, 21, 38, 42, 57, 49, 37, 59, 50}));
//30
    }


    public int maximumRemovals(String s, String p, int[] removable) {

        char[] sCharArray = s.toCharArray(), psCharArray = p.toCharArray();

        int left = 0, right = removable.length;//确认好左右边界比较困难
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            boolean f = check(sCharArray, psCharArray, mid, removable);
//            System.out.println(mid+":"+f);
            if (f) {
                left = mid+1;
            } else right = mid-1;
        }

        return left - 1;

    }


    public boolean check(char[] sCharArray, char[] psCharArray, int limit, int[] removable) {

        int[] status = new int[sCharArray.length];
        for (int i = 0; i < limit; ++i) status[removable[i]] = -1;

        int index = 0;
        int i = 0;
        loop:
        for (; i < psCharArray.length; ++i) {

            if (index >= sCharArray.length) break;
            while (status[index] == -1 || sCharArray[index] != psCharArray[i]) {
                index++;
                if (index >= sCharArray.length) break loop;
            }
            index++;


        }

        return i == psCharArray.length;
    }
}
