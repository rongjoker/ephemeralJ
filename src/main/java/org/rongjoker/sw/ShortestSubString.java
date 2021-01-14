package org.rongjoker.sw;

import org.junit.Test;

/**
 * @date 01/14/2021
 *
 * 滑动窗口
 *
 *
 */
public class ShortestSubString {

    /**
     * 左边找到字符串起始点后，右边拉窗口，依次匹配字符串，拉到字符串结束点为止,然后左侧向右移动，找到最后一个起始字串
     * 这种方法如果字符串有重复字节就会出错
     */
    @Test
    public void testSliding(){

        String shortest = null;

        String s = "ABBCBGHIDEBFDG",t="BM";

        int n=s.length(),m=t.length();

        int left=0,right=0,hit=0;

        while (left<=n-1 && right<=n-1){
            if(s.charAt(left) == t.charAt(hit)){
                right=left+1;hit++;

                while (hit<=m-1&& right<=n-1){
                    if(s.charAt(right) == t.charAt(hit)){
                        right++;hit++;
                    }else right++;
                }
                if(hit>=m){
                    shortest = s.substring(left,right);
                    while (left<right-1){
                        left++;
                        if(s.charAt(left) == t.charAt(0)) shortest = s.substring(left,right);
                    }
                    hit=0;
                }

            }else left++;


        }

        System.out.println(shortest);











    }


}
