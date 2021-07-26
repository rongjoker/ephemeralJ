package org.rongjoker.contest.week251;

import org.junit.Test;

public class Test2 {



    @Test
    public void test2(){
        System.out.println(maximumNumber("132",new int[]{9,8,5,0,3,6,4,2,6,8}));
        System.out.println(maximumNumber("021",new int[]{9,4,3,5,7,2,1,9,0,6}));
        System.out.println(maximumNumber("5",new int[]{1,4,7,5,3,2,5,6,9,4}));
        System.out.println(maximumNumber("214010",new int[]{6,7,9,7,4,0,3,4,4,7}));//"974676"
        System.out.println(maximumNumber("334111",new int[]{0,9,2,3,3,2,5,5,5,5}));//"334999"

//"334111"
//[0,9,2,3,3,2,5,5,5,5]
    }


    public String maximumNumber(String num, int[] change) {
        char[] chars = num.toCharArray();
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            int cur = chars[i] - '0';
//            System.out.println(cur +":"+ change[cur]);
            if(change[cur]>cur){
                chars[i] = Character.forDigit(change[cur],10);
                flag = true;
            }else if(change[cur]<cur && flag)break;
        }


        return new String(chars);
    }

}
