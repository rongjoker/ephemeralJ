package org.rongjoker.contest.week235;

import org.junit.Test;

/**
 * @date 04/14/2021
 *
 *
 */
public class TruncateSentence {


    @Test
    public void test1(){

        System.out.println(truncateSentence("Hello how are you Contestant",4));


    }

    public String truncateSentence(String s, int k) {

        int len = s.length();
        if(len==1)return s;
        int index = 1;
        while(index<len && k>0){
            if(s.charAt(index)==' '){
                if(k==1) return s.substring(0,index);
                if(index==len-1 || s.charAt(index+1)!=' ')--k;
            }
            ++index;
        }
        return s.substring(0,index);

    }
}
