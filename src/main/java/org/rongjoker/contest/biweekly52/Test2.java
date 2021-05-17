package org.rongjoker.contest.biweekly52;

import org.junit.Test;

import java.util.Arrays;

public class Test2 {

    @Test
    public void test2(){

        System.out.println(Arrays.toString(memLeak(8, 11)));
        System.out.println(Arrays.toString(memLeak(2, 2)));

    }


    public int[] memLeak(int memory1, int memory2) {
        int count = 1;
        while (true){
            if (memory1>=memory2){
                if(memory1<count)break;
                memory1 -=count;
            }else {
                if(memory2<count)break;
                memory2 -=count;
            }

            ++count;
        }
        return new int[]{count,memory1,memory2};
    }
}
