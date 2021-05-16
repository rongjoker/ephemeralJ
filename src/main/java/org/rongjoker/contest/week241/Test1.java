package org.rongjoker.contest.week241;

import org.junit.Test;

public class Test1 {

    @Test
    public void test1(){

//        System.out.println(subsetXORSum(new int[]{1,3}));
//        System.out.println(subsetXORSum(new int[]{5,1,6}));
        System.out.println(subsetXORSum(new int[]{3,4,5,6,7,8}));

    }


    public int subsetXORSum(int[] nums) {

        int len = nums.length;
        if (len==0) return 0;
        if (len==1) return nums[0];
        backtrack(nums,len,0,0);
        return ans;

    }

    int ans = 0;
//    List<Integer> path = new ArrayList<>();

    public void backtrack(int[] nums, int length, int start, int ret){

        for (int i = start; i < length; i++) {
            ret^= nums[i];
//            path.add(i);
            ans +=ret;
            backtrack(nums,length,i+1,ret);//关键，这里的是i而不是start
//            path.remove(path.size()-1);
            ret^= nums[i];
        }
    }





}
