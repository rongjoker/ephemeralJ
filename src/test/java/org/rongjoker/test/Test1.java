package org.rongjoker.test;

import org.junit.Test;

import java.math.BigDecimal;

public class Test1 {

    @Test
    public void test(){
        System.out.println(minSpeedOnTime(new int[]{1,1,100000},2.01));//10000000
        System.out.println(minSpeedOnTime(new int[]{1,3,2},6));
        System.out.println(minSpeedOnTime(new int[]{1,3,2},2.7));
        System.out.println(minSpeedOnTime(new int[]{1,3,2},1.9));
        System.out.println(minSpeedOnTime(new int[]{1},1.9));
    }

    public int minSpeedOnTime(int[] dist, double hour) {

        int h = Double.valueOf(hour).intValue();
        int len = dist.length;

        if(len==1){
            double s = dist[0] / hour;
            return s > (int) s ? (int) s +1 : (int) s;
        }


        if(len - 1 > h)return -1;


        int min = 1;
        int sum = 10000000;

        double last = dist[len - 1];
        while (min <= sum){
            int middle = min + ((sum - min) >> 1);
            int p = timePlus(dist,middle);
            double a = BigDecimal.valueOf(hour).subtract(BigDecimal.valueOf(p)).doubleValue();//double 相减有bug
            double tt = BigDecimal.valueOf(a *middle).doubleValue();

            if(tt == last ){//时间够，即速度还可以降低
                return middle;
            }else
                if(tt < last){
                min = middle + 1;//速度需要增加才可以
            }else{
                sum = middle - 1;
            }



        }
        return min;

    }

    public int timePlus(int[] dist,int speed){
        int p = 0;
        int len = dist.length;
        for (int i = 0; i < len - 1; i++) {
            int temp = dist[i]/speed;
            if(dist[i]%speed>0)++temp;
            p+=temp;

        }
        return p;
    }


}
