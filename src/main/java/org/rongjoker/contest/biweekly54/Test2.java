package org.rongjoker.contest.biweekly54;

import org.junit.Test;

public class Test2 {


    @Test
    public void test2(){



    }

    //10737
    public int chalkReplacer(int[] chalk, int k) {

        long sum = 0;
        for (int i : chalk) sum+=i;
        k %= sum;

        for (int i = 0; i < chalk.length; i++) {
            if(k<chalk[i])return i;
            k-=chalk[i];
        }

        return 0;


    }


}
