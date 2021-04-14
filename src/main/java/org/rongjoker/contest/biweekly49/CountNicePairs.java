package org.rongjoker.contest.biweekly49;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 */
public class CountNicePairs {


    @Test
public void test3(){

        System.out.println(rev(120));


}

    public int countNicePairs(int[] nums) {

        int temp;
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            temp = num - rev(num);
            map.put(temp,map.getOrDefault(temp,0)+1);
        }

        long count = 0,times;
        int mod = (int)(Math.pow(10, 9) + 7);
        for (int key : map.keySet()) {
            times = map.get(key);
            count = (count + (times)*(times-1)/2)%mod;
        }
        return (int) (count%mod);



    }


    public int rev(int source){
        int result = 0;

        while(source>0){
            result = result*10 +source%10;
            source/=10;
        }
        return result;


    }
}
