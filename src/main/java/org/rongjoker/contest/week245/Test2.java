package org.rongjoker.contest.week245;

import org.junit.Test;

import java.util.*;

public class Test2 {

    @Test
    public void test2(){

//        System.out.println(maximumRemovals("abcacb","ab",new int[]{3,1,0}));
//        System.out.println(maximumRemovals("abcbddddd","abcd",new int[]{3,2,1,4,5,6}));
        System.out.println(maximumRemovals("kkwiypfzruadoeyfzogmpslfbvrumcrogouomuaidyhqvlaumguqcipcbfkdnp","kkiyaogslrroadmcb",new int[]{52,44,9,12,54,5,16,36,23,8,43,58,15,13,28,2,29,27,34,60,25,35,20,7,31,11,51,39,19,24,21,38,42,57,49,37,59,50}));
//30
    }

    public int maximumRemovals(String s, String p, int[] removable) {

        int ans = 0;

        char[] sCharArray = s.toCharArray();
        char[] psCharArray = p.toCharArray();

        track(sCharArray,psCharArray,0,sCharArray.length - psCharArray.length,psCharArray.length,0);

        for (int i : removable) {
            list.removeIf(integers -> integers.contains(i));
            if(list.size()>0)ans++;
        }


        return ans;

    }


    List<HashSet<Integer>> list = new ArrayList<>();

    Deque<Integer> path = new ArrayDeque<>();



    public void track(char[] sCharArray,char[] psCharArray,int start,int len,int target,int index){

        if(index==target){
            list.add(new HashSet<>(path));
            return;
        }

        for(int i = start;i<=len;++i){
            if(sCharArray[i] == psCharArray[index]){
                path.addLast(i);
                track(sCharArray,psCharArray,i+1,len+1,target,index+1);
                path.pollLast();
            }

        }

    }

}
