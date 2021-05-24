package org.rongjoker.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test20210523 {


    @Test
    public void test1() {

        System.out.println(Arrays.toString(maximizeXor(new int[]{0, 1, 2, 3, 4}
                , new int[][]{
                        {3, 1}
                        , {1, 3}, {5, 6}
                }
        )));
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {

        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        for (int[] num : queries) max = Math.max(max, num[0]);
        int maxLen = Integer.toBinaryString(max).length();

        int len = queries.length;


        TrieNode root = new TrieNode();

        for (int num : nums) {
            TrieNode cur = root;
            root.min = Math.min(root.min, num);
            for (int i = maxLen; i >= 0; --i) {
                int temp = num >> i & 1;
                if (cur.children[temp] == null) {
                    cur.children[temp] = new TrieNode();
                }
                cur.children[temp].min = Math.min(cur.children[temp].min, num);
                cur = cur.children[temp];
            }
        }

        int[] ans = new int[len];

        for (int i = 0; i < len; ++i) {
            int[] at = queries[i];
            TrieNode cur = root;
            int curv = 0;
            for (int j = maxLen; j >= 0; --j) {
                curv <<= 1;
                int tv = at[0] >> j & 1;
                if (cur == null || cur.min > at[1]) {
                    ans[i] = -1;
                    break;
                } else {
                    if (cur.children[tv ^ 1] != null && cur.children[tv ^ 1].min <= at[1]) {
                        curv += 1;
                        cur = cur.children[tv ^ 1];
                    } else cur = cur.children[tv];
                }
            }
            if (ans[i] != -1) ans[i] = curv;

        }
        return ans;


    }

    static class TrieNode {
        int min = Integer.MAX_VALUE;//最小值
        TrieNode[] children = new TrieNode[2];
    }


    @Test
    public void testNextGreaterElement(){
        //230421
//        System.out.println(nextGreaterElement(230241));//230412
//        System.out.println(nextGreaterElement(21));//230412
        System.out.println(nextGreaterElement(2147483476));//2147483647
    }

    public int nextGreaterElement(int n) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while(n>0){
            list.add(n%10);
            n/=10;
        }
        boolean change = false;
        for(int i=0;i<list.size() -1;++i){

            if(list.get(i+1)< list.get(i)){
                change = true;
                int temp = list.get(i+1);
                list2.add(temp);

                list.set(i+1,list.get(i));
//                list.set(i,temp);

            }else {
                list2.add(list.get(i));

            }
            list.set(i,0);

            if(change) break;


        }
        if(change){
            long ans = 0;
            for(int i = list.size()-1;i>=0;--i){
                ans *=10;
                ans +=list.get(i);
            }
            long ans2 = 0;
            list2.sort((Comparator.comparingInt(o -> o)));
            for(int i=0;i<list2.size();++i){
                ans2 *=10;
                ans2 +=list2.get(i);
            }
            ans+=ans2;

            return ans<=Integer.MAX_VALUE?(int)ans:-1;

        }else return -1;

    }
}
