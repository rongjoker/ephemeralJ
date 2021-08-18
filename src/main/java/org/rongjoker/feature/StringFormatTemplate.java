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

        String code = String.format("my name is %s,i am %s so far%n","joker",31);

        System.out.printf("i am writing codes like this:%s",code);

    }

}
