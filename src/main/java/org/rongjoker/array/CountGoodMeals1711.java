package org.rongjoker.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @date 07/07/2021
 *
 * 1711. 大餐计数 https://leetcode-cn.com/problems/count-good-meals/
 *
 */
public class CountGoodMeals1711 {

    @Test
    public void test1711(){
        System.out.println(countPairs(new int[]{1048576,1048576}));
        System.out.println(countPairs(new int[]{1,3,5,7,9}));
        System.out.println(countPairs(new int[]{1,1,1,3,3,3,7}));
        System.out.println(countPairs(new int[]{149,107,1,63,0,1,6867,1325,5611,2581,39,89,46,18,12,20,22,234}));

    }


    /**
     * 两数之和 加强版本
     * @param deliciousness
     * @return
     */
    public int countPairs(int[] deliciousness) {
        int M = 1000000007;
        int[] two = new int[22];
        for (int i = 0; i < 22; i++) two[i] = (int)Math.pow(2,i);//此处效率较低

        Map<Integer,Integer> count = new HashMap<>();
        long ans = 0;
        for(int d:deliciousness){
            for(int n:two){
                ans+= count.getOrDefault(n-d,0);
                ans%=M;
            }
            count.put(d,count.getOrDefault(d,0)+1);
        }
        return (int)ans;

    }


//    /**
//     * 优化版本
//     * @param deliciousness
//     * @return
//     */
//    public int countPairs2(int[] deliciousness) {
//        int M = 1000000007;
//        int max = 0;
//        for(int d:deliciousness)max = Math.max(d,max);
//
//        Map<Long,Integer> count = new HashMap<>();
//        long ans = 0;
//        for(int d:deliciousness){
//            for(long n:two){
//                ans+= count.getOrDefault(n-d,0);
//                ans%=M;
//            }
//            count.put((long)d,count.getOrDefault((long)d,0)+1);
//        }
//        return (int)ans;
//
//    }
}
