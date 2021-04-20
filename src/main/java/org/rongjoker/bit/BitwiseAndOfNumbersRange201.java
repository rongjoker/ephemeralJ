package org.rongjoker.bit;

import org.junit.Test;

/**
 *
 *
 */
public class BitwiseAndOfNumbersRange201 {

    @Test
    public void test201(){
        System.out.println(rangeBitwiseAnd(5,7));
    }

    public int rangeBitwiseAnd(int left, int right) {
        String sl = toBinary(left);
        String sr = toBinary(right);

        System.out.println(sl);
        System.out.println(sr);




        return 0;



    }

    public String toBinary(int i){
        return Integer.toBinaryString(i);
    }


}
