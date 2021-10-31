package org.rongjoker.sw;

import org.junit.Test;

public class LengthOfLongestSubstringKDistinct {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        java.util.Map<Character,Integer> hash = new java.util.HashMap<>();
        int len = s.length();
        int ans = 0;
        int start = 0;
        char[] cs = s.toCharArray();
        for(int i=0;i<len;++i){
            char c = cs[i];
            hash.put(c,hash.getOrDefault(c,0)+1);
            while(hash.size()>k){
                int t = hash.getOrDefault(cs[start],0)-1;
                if(t==0)hash.remove(cs[start]);
                else hash.put(cs[start],t);
                start++;
            }
            ans = Math.max(ans,i-start+1);


        }
        return ans;
    }

    @Test
    public void test(){
        System.out.println(lengthOfLongestSubstringKDistinct("eceba",3));

    }
}
