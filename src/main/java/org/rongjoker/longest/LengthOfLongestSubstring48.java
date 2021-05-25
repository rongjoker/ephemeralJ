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
 * char[] chars = s.toCharArray(); 更快一点点
 *
 */
public class LengthOfLongestSubstring48 {


    @Test
    public void test48(){
        System.out.println(lengthOfLongestSubstring3("aab"));
        System.out.println(lengthOfLongestSubstring3("pwwkew"));
        System.out.println(lengthOfLongestSubstring3("dvdf"));
        System.out.println(lengthOfLongestSubstring3("cdd"));
    }

    public int lengthOfLongestSubstring3(String s) {
        int len = s.length();
        if(len<=1)return len;
        int ans = 0,index = 0,start = 0;
        Map<Character,Integer> hash = new HashMap<>();
        char[] chars = s.toCharArray();
        for(int i=0;i<len;++i){
            char temp = chars[i];
            if(hash.containsKey(temp)){
                index = hash.get(temp);
                ans = Math.max(ans,i - start);
                for(int j = start;j<=index;++j)hash.remove(chars[j]);
                start = index + 1;
            }
            hash.put(temp,i);
        }
        return Math.max(ans,len - start);
    }


    public int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        if(len<=1)return len;
        int ans = 0,index = 0,start = 0;
        Map<Character,Integer> hash = new HashMap<>();
        for(int i=0;i<len;++i){
            char temp = s.charAt(i);
            if(hash.containsKey(temp)){
                index = hash.get(temp);
                ans = Math.max(ans,i - start);
                for(int j = start;j<=index;++j)hash.remove(s.charAt(j));
                start = index + 1;
            }
            hash.put(temp,i);
        }
        return Math.max(ans,len - start);
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
