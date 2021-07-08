package org.rongjoker.prefix;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @date 07/08/2021
 * 1124. 表现良好的最长时间段 https://leetcode-cn.com/problems/longest-well-performing-interval/
 *
 */
public class LongestWPI1124 {


    @Test
    public void test1124(){
        System.out.println(longestWPI(new int[]{9,9,6,0,6,6,9}));
        System.out.println(longestWPI2(new int[]{9,9,6,0,6,6,9}));

    }


    public int longestWPI2(int[] hours) {
        int len = hours.length,ans=0,sum=0;
        int[] prefix = new int[len+1];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0;i<len;++i){
            sum+= (hours[i]>8?1:-1);
            while (!stack.isEmpty() && prefix[stack.peekLast()]<sum)stack.pollLast();
            if(!stack.isEmpty()){
                ans = Math.max(ans,i-stack.peekLast());
            }
            stack.addLast(i);
            prefix[i+1] = sum;

        }
        return ans;

    }


    public int longestWPI(int[] hours) {
        int len = hours.length,ans=0,sum=0;
        int[] prefix = new int[len+1];
        for(int i=0;i<len;++i){
            sum+= (hours[i]>8?1:-1);
            for(int j=0;j<=(i-ans);++j){
                if(sum-prefix[j]>0)ans = Math.max(ans,i-j+1);
                prefix[i+1] = sum;
            }
        }
        return ans;

    }
}
