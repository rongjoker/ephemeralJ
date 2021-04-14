package org.rongjoker.contest.week234;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class NumDifferentIntegers {

    @Test
    public void test1(){



        System.out.println(numDifferentIntegers("a1b01c001"));



    }



    public int numDifferentIntegers(String word) {
        if(word.length()==1) return word.charAt(0)>'9'?0:1;

        int len=word.length();
        Set<String> set = new HashSet<>();

        int left=-1,index=0;String temp;
        while(index<len){
//            System.out.println(word.charAt(index)+";"+(word.charAt(index) <='9'));
            if(word.charAt(index)<='9'){
                if( index==0 || word.charAt(index-1)>'9')
                    left = index;
            }else{
                if( index>0 && word.charAt(index-1)<='9'){
                    while(word.charAt(left)=='0' && left<index-1)++left;
                    temp = word.substring(left,index);
                    set.add(temp);

                }

            }

            ++index;


        }

        if(word.charAt(index-1)<='9'){
            while(word.charAt(left)=='0' && left<index-1)++left;
            temp = word.substring(left,index);
            set.add(temp);

        }


        return set.size();

    }
}
