package org.rongjoker.contest.week248;

import org.junit.Test;

import java.util.Arrays;

public class Test2 {

    @Test
    public void test2(){
        System.out.println(eliminateMaximum(new int[]{1,3,4},new int[]{1,1,1}));
        System.out.println(eliminateMaximum(new int[]{1,1,2,3},new int[]{1,1,1,1}));
        System.out.println(eliminateMaximum(new int[]{3,2,4},new int[]{5,3,2}));


    }


    public int eliminateMaximum(int[] dist, int[] speed) {

        int len = dist.length;
        int[] target = new int[len];
        int max = 0;
        for(int i=0;i<len;++i){
            target[i] = dist[i]/speed[i];
            if(dist[i]%speed[i]>0)target[i]++;
            max = Math.max(max,target[i]);
        }
        Arrays.sort(target);
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if(target[i]<i+1)break;
            ans++;
        }
        return ans;



    }



}
