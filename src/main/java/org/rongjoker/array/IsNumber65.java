package org.rongjoker.array;

import org.junit.Test;

import java.util.Set;

/**
 *
 *
 *
 */
public class IsNumber65 {

    @Test
    public void test65(){
        System.out.println(isNumber("-123.456e789"));
        System.out.println(isNumber("+3.14"));
        System.out.println(isNumber("2e10"));
        System.out.println(isNumber("-.9"));
        System.out.println(isNumber(".1"));
        System.out.println("------");
        System.out.println(isNumber("abc"));
        System.out.println(isNumber("1a"));
        System.out.println(isNumber("e3"));
        System.out.println(isNumber("e3"));
        System.out.println(isNumber("-+3"));
        System.out.println(isNumber("--6"));
        System.out.println(isNumber("99e2.5"));
        System.out.println(isNumber("95a54e53"));
        System.out.println(isNumber("."));
        System.out.println(isNumber("6+1"));
        System.out.println(isNumber(".-4"));
    }


    public boolean isNumber(String s) {
        Set<Character> nums = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0','.','+','-','e','E');

        boolean num = false,negative = false,es = false,point = false;

        char[] cs = s.toCharArray();
        for(char c:cs){
            if(!nums.contains(c))return false;
            if(c == '+' || c == '-'){
                if(negative || num || point)return false;
                negative = true;
            }else if(c == '.'){
                if(point) return false;
                point = true;
            }else if(c == 'e'|| c == 'E'){
                if(!num || es) return false;
                num = false;
                negative = false;
                point = false;
                es = true;
            }else num = true;

        }

        if(es && point) return false;

        return num ;



    }
}
