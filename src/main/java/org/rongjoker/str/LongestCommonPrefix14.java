package org.rongjoker.str;

/**
 * @date 03/26/2021
 * 14. 最长公共前缀 https://leetcode-cn.com/problems/longest-common-prefix/
 *
 */
public class LongestCommonPrefix14 {

    public String longestCommonPrefix(String[] strs) {

        if(strs.length==0)return "";
        if(strs.length==1)return strs[0];
        int len = strs[0].length(),length = strs.length;
        char temp;
        StringBuilder sb = new StringBuilder();
        flag:for(int i=0;i<len;i++){
            temp = strs[0].charAt(i);

            for(int j=1;j<length;j++){
                if(strs[j].length()<=i || strs[j].charAt(i)!=temp)
                    break flag;
            }
            sb.append(temp);
        }

        return sb.toString();

    }
}
