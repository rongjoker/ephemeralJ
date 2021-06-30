package org.rongjoker.prefix;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * @date 06/30/2021
 * 1371. 每个元音包含偶数次的最长子字符串 https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 *
 *
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 *
 */
public class FindTheLongestSubstring1371 {


    @Test
    public void test1371(){

        System.out.println(findTheLongestSubstring("eleetminicoworoep"));
        System.out.println(findTheLongestSubstring("leetcodeisgreat"));
        System.out.println(findTheLongestSubstring("bcbcbc"));


    }


    public int findTheLongestSubstring(String s) {

        int len = s.length(),ans=0;
        int[][] prefix = new int[len+1][5];
        char[] cs = s.toCharArray();
        for(int i=0;i<len;i++){
            prefix[i+1][0] = prefix[i][0] + (cs[i]=='a'?1:0);
            prefix[i+1][1] = prefix[i][1]+ (cs[i]=='e'?1:0);
            prefix[i+1][2] = prefix[i][2]+ (cs[i]=='i'?1:0);
            prefix[i+1][3] = prefix[i][3]+ (cs[i]=='o'?1:0);
            prefix[i+1][4] = prefix[i][4]+ (cs[i]=='u'?1:0);

        }



        for(int i=len;i>ans;i--){
            for(int j=0;j<i-ans;j++){

                if((prefix[i][0] - prefix[j][0])%2==0 &&
                        (prefix[i][1] - prefix[j][1])%2==0 &&
                        (prefix[i][2] - prefix[j][2])%2==0 &&
                        (prefix[i][3] - prefix[j][3])%2==0 &&
                        (prefix[i][4] - prefix[j][4])%2==0
                ){
                    ans = Math.max(ans,i-j);
                    break;
                }
            }
        }

        return ans;


    }


    /**
     * 状态压缩
     * 直接用一个长度为 32 的数组来存储对应状态出现的最早位置即可
     *       //如果status对应的pos[status]大于0 说明已经找到符合要求的子串
     *             //因为两个子串的奇偶性相等，说明中间子串是符合要求的。
     *             //奇偶性相同的两个数的差，必定为偶数 只会有一个偶数00000
     *             //因此出现两个相同状态的数，他们中间必定出现了偶数次数的aeiou
     * @param s
     * @return
     */
    public int findTheLongestSubstring2(String s) {
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }




}
