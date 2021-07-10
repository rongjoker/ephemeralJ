package org.rongjoker.contest.biweekly56;

import org.junit.Test;

public class Test1 {


    @Test
    public void test1() {

        System.out.println(countTriples(5));
        System.out.println(countTriples(10));



    }


    public int countTriples(int n) {

        int max = n*n;

        int ans =0;

        for(int i=1;i<= n;++i){
            for(int j=1;j<= n;++j){
                int temp = (i*i) + (j*j);

                if(temp<=max){
                    double dd =Math.sqrt(temp);
                    if(dd -  Math.floor(dd) ==0)ans++;
                }else break;


            }
        }

        return ans;

    }

}
