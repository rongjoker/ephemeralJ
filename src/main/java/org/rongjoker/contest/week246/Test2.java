package org.rongjoker.contest.week246;

import org.junit.Test;

public class Test2 {

    @Test
    public void test2(){

        System.out.println(numberOfRounds("12:01","12:44"));
        System.out.println(numberOfRounds("12:00","13:00"));
        System.out.println(numberOfRounds("20:00","06:00"));
        System.out.println(numberOfRounds("00:00","23:59"));

    }

    public int numberOfRounds(String startTime, String finishTime) {

        String[] start = startTime.split(":");
        String[] finish = finishTime.split(":");

        int[] ss = new int[2],ff = new int[2];

        for (int i = 0; i < 2; i++) {
            ss[0] = Integer.parseInt(start[0]);
            ff[0] = Integer.parseInt(finish[0]);

            ss[1] = Integer.parseInt(start[1]);
            ff[1] = Integer.parseInt(finish[1]);

        }

        if(ff[0]<ss[0] || (ff[0]==ss[0] && ff[1]<ss[1])){//通宵
            return count(ss,new int[]{24,0}) + count(new int[]{0,0},ff);
        }else{
            return count(ss,ff);
        }

    }

    public int count(int[] ss,int[] ff){

        int ans = 0;

        int h = ff[0] - ss[0];
        if(h>0){
            ans += h*4;
        }
        int minus = ss[1]/15;
        if((ss[1]%15)>0)minus++;
        ans -= minus;
        int add = ff[1]/15;
        ans += add;

        return ans;

    }


}
