package org.rongjoker.contest.week255;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * 5851. 找出不同的二进制字符串 https://leetcode-cn.com/contest/weekly-contest-255/problems/find-unique-binary-string/
 * 类似桶排序
 *
 *
 */
public class Test2 {



    @Test
    public void test2(){
        System.out.println(findDifferentBinaryString(new String[]{"01","10"}));
        System.out.println(findDifferentBinaryString(new String[]{"00","01"}));
        System.out.println(findDifferentBinaryString(new String[]{"111","011","001"}));
        System.out.println(findDifferentBinaryString(new String[]{"110","111","010"}));
        System.out.println(findDifferentBinaryString(new String[]{"000","001","110"}));
//        System.out.println(findDifferentBinaryString(new String[]{"001"}));


    }

    public String findDifferentBinaryString(String[] nums) {
        int len = nums[0].length();
        boolean[] visited = new boolean[1<<16];
        for(String s:nums){
            char[] tt = s.toCharArray();
            int temp = 0;
            for(int i=0;i<len;++i){
                temp += ((tt[i]-'0')<<(len-i-1));
            }
            visited[temp] = true;
        }
        StringBuilder sr = new StringBuilder();
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                for(int x=len-1;x>=0;x--){
                    sr.append(i >> x &1);
                }
                return sr.toString();
            }
        }

        return sr.toString();

    }




}
