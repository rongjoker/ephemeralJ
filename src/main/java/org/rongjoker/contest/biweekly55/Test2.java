package org.rongjoker.contest.biweekly55;

import org.junit.Test;

public class Test2 {

    //"kpygkivtlqoockpygkivtlqoocssnextkqzjpycbylkaondsskpygkpygkivtlqoocssnextkqzjpkpygkivtlqoocssnextkqzjpycbylkaondsycbylkaondskivtlqoocssnextkqzjpycbylkaondssnextkqzjpycbylkaondshijzgaovndkjiiuwjtcpdpbkrfsi"
    //"kpygkivtlqoocssnextkqzjpycbylkaonds"


    @Test
    public void test2(){

        System.out.println(removeOccurrences("daabcbaabcbc","abc"));
        System.out.println(removeOccurrences("dababc","abc"));
//        System.out.println(removeOccurrences("kpygkivtlqoocskpygkpygkivtlqoocssnextkqzjpycbylkaondskivtlqoocssnextkqzjpycbylkaondssnextkqzjpycbylkaondshijzgaovndkjiiuwjtcpdpbkrfsi","kpygkivtlqoocssnextkqzjpycbylkaonds"));
        System.out.println(removeOccurrences("kpygkivtlqoocskpygkpygkivtlqoocssnextkqzjpycbylkaondskivtlqoocssnextkqzjpycbylkaondssnextkqzjpycbylkaondshijzgaovndkjiiuwjtcpdpbkrfsi","kpygkivtlqoocssnextkqzjpycbylkaonds"));


        String  s = "kpygkivtlqoocskpygkivtlqoocssnextkqzjpycbylkaondssnextkqzjpycbylkaondshijzgaovndkjiiuwjtcpdpbkrfsi",
        ss = "kpygkivtlqoocskpyskivtlqoocssnextkqzjpycbylkaondssnextkqzjpycbylkaondshijzgaovndkjiiuwjtcpdpbkrfsi";

    }


    public String removeOccurrences(String s, String part) {

        StringBuilder stringBuilder = new StringBuilder(s);
        int len1 = 0,len2 = part.length();

        while (len1 !=stringBuilder.length()){
            len1 = stringBuilder.length();

            loop:for(int i = 0;i<stringBuilder.length();i++){
                int k=0;
                for(int j = i;j<stringBuilder.length();j++){
                    if(stringBuilder.charAt(j)==part.charAt(k)){
                        k++;
                    }else break;

                    if(k==len2){
                        stringBuilder.delete(j-k+1,j+1);
                        break loop;
                    }
                }
            }

        }
        return stringBuilder.toString();


    }

}
