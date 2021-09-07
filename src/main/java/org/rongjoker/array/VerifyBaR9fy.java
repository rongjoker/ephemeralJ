package org.rongjoker.array;

import java.util.Scanner;

/**
 *
 *
 *
 *
 */
public class VerifyBaR9fy {

    public static void main(String[] args) {
        VerifyBaR9fy verifyBaR9fy = new VerifyBaR9fy();
        Scanner sc=new Scanner(System.in);
        String s1=sc.nextLine();
        System.out.println(verifyBaR9fy.verify(s1));
    }


    public String  verify(String name){

        int len = name.length();
        if(len<2)return "Wrong";
        char[] chars = name.toCharArray();
        if(!(chars[0]>='A'&&chars[0]<='Z' ||
            chars[0]>='a'&&chars[0]<='z'
        ))return "Wrong";

        int n = 0;
        for (int i = 1; i < len; i++) {
            if (chars[i]<='9' && chars[i]>='0')n++;
            else if(!(chars[i]>='A'&&chars[i]<='Z' ||
                chars[i]>='a'&&chars[i]<='z'
            ))return "Wrong";

        }

        return n>0?"Accept":"Wrong";

    }





}
