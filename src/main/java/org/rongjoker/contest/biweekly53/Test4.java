package org.rongjoker.contest.biweekly53;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test4 {

    @Test
    public void test4(){
        System.out.println(minimumXORSum(new int[]{1,0,3},
                new int[]{5,3,4}));
    }



    public int minimumXORSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int ans = 0;
        int n = nums1.length;
        List<Integer> list2 = new ArrayList<>();
        for (int i : nums2) {
            list2.add(i);
        }
        for (int i = 0; i < nums1.length; i++) {
            int temp = nums1[i];
            int temp_index = 0;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < list2.size(); j++) {
                    int xor = temp ^ list2.get(j);
                if(xor <min){
                    temp_index = j;
                    min = xor;

                }
            }
            ans+=min;
            list2.remove(temp_index);

        }
        return ans;


    }
}
