package org.rongjoker.prefix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class FindMaxLength525 {

    @Test
    public void test525() {
        System.out.println(findMaxLength(new int[]{0,1,0,0,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,1,1,1,1,0,0}));
//        System.out.println(findMaxLength2(new int[]{0,1,0,0,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,1,1,1,1,0,0}));
        System.out.println(findMaxLength3(new int[]{0,1,0,0,0,0,1,1,0,1,0,0,1,1,0,1,0,1,1,0,1,1,1,1,0,0}));
        System.out.println(findMaxLength3(new int[]{0,1,0}));
    }


    /**
     * 利用hash做下标记录
     * @param nums
     * @return
     */
    public int findMaxLength3(int[] nums) {
        int len = nums.length;

        Map<Integer,Integer> dict = new HashMap<>();
        dict.put(0,-1);
        int max = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += (nums[i]==0)?-1:1;
            if(dict.containsKey(sum)){
                max = Math.max(max,i - dict.get(sum)) ;
            }else dict.put(sum,i);
        }

        return max;


    }


    public int findMaxLength2(int[] nums) {
        int len = nums.length;
        long[] prex = new long[len + 1];

        for (int i = 0; i < len; i++) {
            prex[i + 1] = prex[i] + nums[i];

        }

        int max = 0;
        for(int i = len-1;i>max;--i){

            int start = i % 2 == 0 ? 1 : 0;
            while (start + max< i) {
                if ((prex[i + 1] - prex[start]) * 2 == (i + 1 - start)) {
                    max = Math.max(max, i + 1 - start);
                    break;
                }
                start += 2;
            }

        }

        return max;


    }

    /**
     * 前缀和，超时
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        int len = nums.length;
        long[] prex = new long[len + 1];
        int max = 0;
        for (int i = 0; i < len; i++) {
            prex[i + 1] = prex[i] + nums[i];
            int start = i % 2 == 0 ? 1 : 0;
            while (start < i) {
                if ((prex[i + 1] - prex[start]) * 2 == (i + 1 - start)) {
                    max = Math.max(max, i + 1 - start);
                    break;
                }
                start += 2;
            }
        }

        return max;


    }
}
