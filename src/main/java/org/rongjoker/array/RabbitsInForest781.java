package org.rongjoker.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @date 04/04/2021
 * 781. 森林中的兔子 https://leetcode-cn.com/problems/rabbits-in-forest/
 * 4月4日每日一题
 * 哈希解法
 *
 *
 */
public class RabbitsInForest781 {

    @Test
    public void test781(){

        int[] answers= {};

        System.out.println(numRabbits(answers));
    }



    public int numRabbits(int[] answers) {

        if(answers.length==0)return 0;

        Map<Integer,Integer> map = new HashMap<>();

        int sum = 0,temp;

        for(int i :answers){
            if(!map.containsKey(i))map.put(i,1);
            else {
                temp = map.get(i);
                if(temp - i <1)map.put(i,temp+1);
                else {
                    sum+=temp;
                    map.put(i,1);
                }
            }
        }

        Set<Integer> set = map.keySet();

        for (int k :set){
            sum+=(k+1);
        }

        return sum;




    }
}
