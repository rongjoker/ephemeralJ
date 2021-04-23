package org.rongjoker.dp.target;

import java.util.*;

/**
 * @date 04/23/2021
 * 368. 最大整除子集 https://leetcode-cn.com/problems/largest-divisible-subset/
 *
 *
 */
public class LargestDivisibleSubset368 {


    //这种方法，速度很慢，双10%，可以再优化
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int lens = nums.length;

        List<Integer> dp = new ArrayList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        if (lens==0)return dp;

        Arrays.sort(nums);
        dp.add(nums[0]);
        if (lens==1){
            return dp;
        }
        List<Integer> temp;
        for(int i =1;i< lens;++i){
            for(int j=0;j<i;++j){
                if (nums[i]% nums[j] ==0 ){
                    if(map.get(j)==null){
                        temp = new ArrayList<>();
                        temp.add(nums[j]);
                    }else{
                        temp = new ArrayList<>(map.get(j));
                    }
                    temp.add(nums[i]);

                    if(map.get(i)==null || map.get(i).size()< temp.size()){
                        map.put(i,temp);
                    }

                    if(dp.size()< temp.size())
                        dp = temp;

                }
            }
        }

        return dp;
    }
}
