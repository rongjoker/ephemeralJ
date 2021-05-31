package org.rongjoker.contest.week243;

import org.junit.Test;

public class Test2 {

    @Test
    public void test2(){
        //"-132"
        //3

        System.out.println(maxValue("987",9));
        System.out.println(maxValue("-13",2));
        System.out.println(maxValue("-132",3));

//        System.out.println("23".substring(0,0));

    }

    public String maxValue(String n, int x) {
        boolean negative = false;

        if(n.charAt(0)=='-')negative = true;

        return negative?min_v(n,x):max_v(n,x);

    }

    public String max_v(String n, int x){

        char[] chars = n.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if((chars[i] - '0')<= x){
                if((chars[i] - '0')< x){
                    return n.substring(0, i) + x + n.substring(i, len);
                }else{
                    for (int j = i+1; j < len; j++) {
                        if((chars[j] - '0')< x){
                            return n.substring(0, j) + x + n.substring(j, len);
                        }

                    }
                }
                break;


            }
        }

        return n + x;

    }

    public String min_v(String n, int x){

        char[] chars = n.toCharArray();
        int len = chars.length;
        for (int i = 1; i < len; i++) {
            if((chars[i] - '0')>= x){
                if((chars[i] - '0')> x){
                    return n.substring(0, i) + x + n.substring(i, len);
                }else{
                    for (int j = i+1; j < len; j++) {
                        if((chars[j] - '0')> x){
                            return n.substring(0, j) + x + n.substring(j, len);
                        }

                    }
                }
                break;


            }
        }

        return n + x;
    }

}
