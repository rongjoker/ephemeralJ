package org.rongjoker.contest.biweekly49;

import org.junit.Test;

public class AreSentencesSimilar {


    @Test
    public void test2(){




        System.out.println(areSentencesSimilar("xD iP tqchblXgqvNVdi","FmtdCzv Gp YZf UYJ xD iP tqchblXgqvNVdi"));
        System.out.println(areSentencesSimilar("My name is Haley","My Haley"));
        System.out.println(areSentencesSimilar("of","A lot of words"));
        System.out.println(areSentencesSimilar("Luky","Lucccky"));
        System.out.println(areSentencesSimilar("A A AAa","A AAa"));


    }



    public boolean areSentencesSimilar(String sentence1, String sentence2) {

        String[] nums1 =  sentence1.split(" "),nums2 =  sentence2.split(" ");
        int left = 0,len1= nums1.length,len2= nums2.length, min_len = Math.min(len1,len2);
        while(left< min_len){
            if(!nums1[left].equals(nums2[left])){
                break;
            }
            ++left;
        }

        if(left==min_len) return true;


        int right=0;

        while(right< min_len-left){
            if(!nums1[len1 - right-1].equals(nums2[len2 - right-1])){
                break;
            }
            ++right;
        }


        return left +right ==min_len;


    }
}
