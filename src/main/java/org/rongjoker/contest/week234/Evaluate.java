package org.rongjoker.contest.week234;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluate {


    @Test
    public void test3(){

        List<List<String>> knowledge = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("name");
        list1.add("bob");

        List<String> list2 = new ArrayList<>();
        list2.add("age");
        list2.add("two");

        knowledge.add(list1);
        knowledge.add(list2);

        System.out.println(evaluate("(name)is(age)yearsold",knowledge));
    }


    public String evaluate(String s, List<List<String>> knowledge) {
        int len = s.length();
        if(len<3) return s;

        Map<String,String> map = new HashMap<>();

        for(List<String> kl:knowledge){
            map.put(kl.get(0),kl.get(1));
        }

        int left=-1,index=0;

        List<Character> chars = new ArrayList<>();
        boolean start=false;String temp;
        while(index<len){
            if(s.charAt(index)=='('){left=index;start=true;}

            if(!start){
                chars.add(s.charAt(index));
            }

            if(s.charAt(index)==')'){

                temp = map.getOrDefault(s.substring(left+1,index),"?");
                for(int j=0;j<temp.length();++j)
                    chars.add(temp.charAt(j));


                start=false;
            }

            ++index;


        }

        StringBuilder sb = new StringBuilder();
        for(char c:chars)
            sb.append(c);

        return sb.toString();

    }
}
