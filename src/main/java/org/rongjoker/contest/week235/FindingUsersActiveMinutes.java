package org.rongjoker.contest.week235;

import org.junit.Test;

import java.util.*;

/**
 * @date 04/14/2021
 *
 *
 */
public class FindingUsersActiveMinutes {

    @Test
    public void test2(){

        int[][] logs = {{0,5},{1,2},{0,2},{0,5},{1,3}};int k = 5;

        System.out.println(Arrays.toString(findingUsersActiveMinutes(logs, k)));
    }


    public int[] findingUsersActiveMinutes(int[][] logs, int k) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int[] log:logs){
            Set<Integer> set = map.get(log[0]);
            if(set==null){
                set = new HashSet<>();
                set.add(log[1]);
                map.put(log[0],set);
            }else
                set.add(log[1]);
        }

        int[] kk = new int[k];
        for(Set<Integer> kset:map.values()){
            kk[kset.size()-1]++;
        }

        return kk;

    }
}
