package org.rongjoker.contest.week250;

import org.junit.Test;

public class Test2 {

    @Test
    public void test2(){
        System.out.println(addRungs(new int[]{1,3,5,10},2));
        System.out.println(addRungs(new int[]{3,6,8,10},3));
        System.out.println(addRungs(new int[]{3,4,6,7},2));
        System.out.println(addRungs(new int[]{5},10));
        System.out.println(addRungs(new int[]{3},1));

    }

    public int addRungs(int[] rungs, int dist) {

        int cur = 0;
        int ans = 0;
        for(int rung:rungs){
            if(rung-cur>dist){
                int temp = ((rung-cur)/dist);
                if(((rung-cur)%dist)==0)
                    temp--;

                ans += temp;
            }
            cur = rung;
        }

        return ans;

    }


}
