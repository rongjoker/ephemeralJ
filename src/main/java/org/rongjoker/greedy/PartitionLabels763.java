package org.rongjoker.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 05/31/2021
 * 763. 划分字母区间 https://leetcode-cn.com/problems/partition-labels/
 * 贪心算法，效率更高
 *
 *
 */
public class PartitionLabels763 {


    @Test
    public void test763(){
        System.out.println(partitionLabels2("ababcbacadefegdehijhklij"));
        System.out.println(partitionLabels2("a"));
        System.out.println(partitionLabels2("caedbdedda"));
        System.out.println(partitionLabels2("abcdefg"));
        System.out.println(partitionLabels2("qiejxqfnqceocmy"));
    }


    public List<Integer> partitionLabels2(String s) {

        List<Integer> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        int len = s.length();
        if(len==1){
            ans.add(1);
            return ans;
        }
        int[] position = new int[26];
        for (int i = 0; i < len; i++) position[chars[i] - 'a'] = i;
        int end = 0,start = 0;
        for (int i = 0; i < len; i++) {
            end = Math.max(end,position[chars[i] - 'a']);
            if(i==end){
                ans.add(end - start + 1);
                start = i + 1;
            }
        }

        return ans;

    }


    /**
     * 蛮力算法
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {

        List<Integer> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        int len = s.length();
        if(len==1){
            ans.add(1);
            return ans;
        }

        int start = 0;
        while (start<len){
            int m = findBoard(chars,start,len);
            ans.add(m);
            start+=m;
        }

        return ans;



    }


    public int findBoard(char[] chars,int start,int len){
        int max = start;
        int left = len;
        for (int i = start; i < left; ++i) {
            for (int j = len-1; j >max; --j) {
                if(chars[i] == chars[j]){
                    max = j;
                    left = j;
                    break;
                }

            }
            if(max==len-1 || max==i)break;
        }

        return max - start + 1;

    }





}
