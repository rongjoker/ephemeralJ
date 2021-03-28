package org.rongjoker.longest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 03/28/2021
 * 剑指 Offer 48. 最长不含重复字符的子字符串  https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 *
 * 滑动窗口
 * 滑动窗口往往用hash来维护
 *
 */
public class LengthOfLongestSubstring48 {


    @Test
    public void test48(){
        System.out.println(lengthOfLongestSubstring("aab"));
    }



    public int lengthOfLongestSubstring(String s) {
        int max = 0,len=s.length(),left=0;
        Map<Character,Integer> hash = new HashMap<>();

        for(int i=0;i<len;++i){//简化操作，如果不包含则一直增加，只有包含才进行额外处理
            if(hash.get(s.charAt(i))!=null){
                max = Math.max(max,i-left);
                int right = hash.get(s.charAt(i));
                while(left<=right){
                    hash.remove(s.charAt(left));
                    ++left;
                }
            }
            hash.put(s.charAt(i),i);
        }
        max = Math.max(max,len-left);//最后以未重复结束
        return max;
    }

}
