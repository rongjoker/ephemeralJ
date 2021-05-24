package org.rongjoker.stack;

import org.junit.Test;

import java.util.*;

/**
 * @date 05/24/2021
 * 556. 下一个更大元素 III https://leetcode-cn.com/problems/next-greater-element-iii/
 *
 * 注意细节问题 char 比较也用Comparator.comparingInt(o -> o)
 *
 */
public class NextGreaterElement556 {


    @Test
    public void test556(){
        System.out.println(nextGreaterElement(230241));
        System.out.println(nextGreaterElement(21));
        System.out.println(nextGreaterElement(12));
        System.out.println(nextGreaterElement(2147483476));
    }


    public int nextGreaterElement(int n) {

        String s = Integer.toString(n);
        int len = s.length();
        if (len ==1)return -1;

        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = len-1; i >0; --i) {
            list.add(chars[i]);
            if(chars[i] > chars[i-1]){
                list.sort((Comparator.comparingInt(o -> o)));
                boolean change = false;
                for (int j = 0; j < list.size(); j++) {
                    if(list.get(j)> chars[i-1] && !change) {//找到大于当前的最小的那个替换掉即可
                        chars[i+j] = chars[i-1];
                        chars[i-1] = list.get(j);change = true;
                    }else chars[i+j] = list.get(j);
                }
                long aLong = Long.parseLong(new String(chars));
                return aLong > Integer.MAX_VALUE?-1:(int)aLong;
            }



        }

        return  -1;

    }


}
