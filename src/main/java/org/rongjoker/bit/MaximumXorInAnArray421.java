package org.rongjoker.bit;


import org.junit.Test;

/**
 * @date 04/20/2021
 * @date 05/14/2021
 * 421. 数组中两个数的最大异或值 https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXorInAnArray421 {


    @Test
    public void test421(){

//        System.out.println(findMaximumXOR(new int[]{3,10,5,25,2,8}));
        System.out.println(findMaximumXOR(new int[]{2,4}));
        System.out.println(findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}));
    }

    /**
     * 用数组而不是hash来建立字典树
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int n :nums)
            max = Math.max(max,n);
        int len = nums.length;
        int max_length = Integer.toBinaryString(max).length();
        int mask = 1 << max_length;
        String[] num2Str = new String[len];
        for (int i = 0; i < len; i++) {
            num2Str[i] = Integer.toBinaryString(nums[i] ^ mask).substring(1);
        }

        int temp,need;
        TrieNode root = new TrieNode();
        TrieNode cur_node,xorNode;
        int ans = 0;
        int cur_max;
        for (String str : num2Str) {
            cur_node = root;
            xorNode = root;
            cur_max = 0;
            for (int i = 0; i < max_length; i++) {
                temp = str.charAt(i) - '0';//只会有0和1，故用这种方式转换int和char，巧妙
                if(cur_node.children[temp]==null)
                    cur_node.children[temp] = new TrieNode();

                need = temp ^ 1;

                if(xorNode.children[need]!=null){
                    cur_max = (cur_max<< 1) | 1;
                    xorNode = xorNode.children[need];
                }else {
                    cur_max <<= 1;
                    xorNode = xorNode.children[temp];
                }

                cur_node = cur_node.children[temp];
            }

            ans = Math.max(ans,cur_max);
        }

        return ans;

    }


    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }
}



