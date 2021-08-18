package org.rongjoker.feature;

import org.junit.Test;

/**
 * @date 08/18/2021
 *
 *
 */
public class StringFormatTemplate {



    @Test
    public void testStringFormat(){

        int[] nums = new int[]{1,2,3,4,5,6};

        String code2 = String.format("codes:%s",nums);

        System.out.printf("code2:%s",code2);

        String code = String.format("my name is %s,i am %s so far%n","joker",31);

        System.out.printf("i am writing codes like this:%s",code);

    }

}
