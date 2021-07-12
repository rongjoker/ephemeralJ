package org.rongjoker.bit;

import org.junit.Test;

/**
 * @date 07/12/2021
 *  面试题 01.01. 判定字符是否唯一 https://leetcode-cn.com/problems/is-unique-lcci/
 *
 *
 */
public class IsUnique {


    @Test
    public void testIsUnique(){
        System.out.println(isUnique("leetcode"));
        System.out.println(isUnique("abc"));
    }



    public boolean isUnique(String astr) {
        char[] cs = astr.toCharArray();
        int mask = 0;
        for(char c:cs){
            int temp = (mask >> (c-'a')) & 1;
            if(temp == 1)return false;
            mask |=(1 << (c-'a'));//或运算回避了重复添加的问题
        }

        return true;

    }
}
