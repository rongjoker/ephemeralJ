package org.rongjoker.prefix;

import org.junit.Test;

import java.util.*;

/**
 * @date 07/22/2021
 *
 * 面试题 17.05.  字母与数字 https://leetcode-cn.com/problems/find-longest-subarray-lcci/
 *
 * 前缀和，非常巧妙
 */
public class FindLongestSubarray {


    @Test
    public void test() {

        System.out.println(Arrays.toString(findLongestSubarray(new String[]{"A", "1", "B", "C", "D", "2", "3", "4", "E", "5", "F", "G", "6", "7", "H", "I", "J", "K", "L", "M"})));
        System.out.println(Arrays.toString(findLongestSubarray2(new String[]{"A", "1", "B", "C", "D", "2", "3", "4", "E", "5", "F", "G", "6", "7", "H", "I", "J", "K", "L", "M"})));
        System.out.println(Arrays.toString(findLongestSubarray(new String[]{"A","A"})));
        System.out.println(Arrays.toString(findLongestSubarray2(new String[]{"A","1"})));


    }


    public String[] findLongestSubarray2(String[] array) {
        int left = 0;
        int ans = 0;
        int len = array.length;
        Map<Integer,Integer> dict = new HashMap<>();
        dict.put(0,-1);
        int total = 0;
        for (int i = 0; i < len; ++i) {
            String cur = array[i];
            if(Character.isDigit(cur.charAt(0))){
                total++;
            }else total--;
            int prev = dict.getOrDefault(total,Integer.MAX_VALUE);
            if(prev!=Integer.MAX_VALUE){
                if(i-prev>ans){
                    ans = i-prev;
                    left = prev + 1;
                }

            }else dict.put(total,i);


        }

        String[] ans_new = new String[ans];
        System.arraycopy(array, left, ans_new, 0, ans);

        return ans_new;

    }

    public void setZeroes(int[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0)return;
        Set<Integer> row = new HashSet<>();
        Set<Integer> column = new HashSet<>();
        int w = matrix.length,h=matrix[0].length;
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                if(matrix[i][j]==0){
                    row.add(i);
                    column.add(j);
                }
            }
        }

        for(int i:row){
            for(int j=0;j<h;j++){
                matrix[i][j] = 0;
            }
        }

        for(int i:column){
            for(int j=0;j<w;j++){
                matrix[j][i] = 0;
            }
        }
    }

    /**
     * 默认前缀和的方法，大数据量会超时
     * @param array
     * @return
     */
    public String[] findLongestSubarray(String[] array) {
        int left = 0;
        int ans = 0;
        int len = array.length;
        int[][] prefix = new int[len + 1][2];
        for (int i = 0; i < len; ++i) {
            String cur = array[i];
            if(Character.isDigit(cur.charAt(0))){
                prefix[i + 1][0] = prefix[i][0];
                prefix[i + 1][1] = prefix[i][1] + 1;//数字
            }else {
                prefix[i + 1][0] = prefix[i][0] + 1;//字母
                prefix[i + 1][1] = prefix[i][1];
            }

            for(int j=0;j<i;++j){
                int digital = prefix[i + 1][1] - prefix[j][1];
                int code = prefix[i + 1][0] - prefix[j][0];
                if(digital == code && digital>ans){
                    left = j;ans = digital;
                    break;
                }
            }
        }

        String[] ans_new = new String[ans*2];
        System.arraycopy(array, left, ans_new, 0, ans*2);

        return ans_new;

    }


}
