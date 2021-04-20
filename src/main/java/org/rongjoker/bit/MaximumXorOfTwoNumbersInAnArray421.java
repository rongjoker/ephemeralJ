package org.rongjoker.bit;

import org.junit.Test;

import java.util.HashMap;

/**
 * @date 04/20/2021
 * 421. 数组中两个数的最大异或值 https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXorOfTwoNumbersInAnArray421 {


    @Test
    public void test421(){
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

    public int findMaximumXOR(int[] nums) {
        // Compute length L of max number in a binary representation
        int maxNum = nums[0];
        for (int num : nums) maxNum = Math.max(maxNum, num);
        int L = (Integer.toBinaryString(maxNum)).length();


        // zero left-padding to ensure L bits for each number
        int n = nums.length, bitmask = 1 << L;//最大值
        System.out.println(L+"---"+bitmask);
        String[] strNums = new String[n];
        for (int i = 0; i < n; ++i) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
            //真实的与或运算的结果
            System.out.println(Integer.toBinaryString(bitmask | nums[i])+";"+strNums[i]);
        }

        TrieNode trie = new TrieNode();
        int maxXor = 0;
        for (String num : strNums) {
            TrieNode node = trie, xorNode = trie;
            int currXor = 0;
            for (Character bit : num.toCharArray()) {
                // insert new number in trie
                if (node.children.containsKey(bit)) {
                    node = node.children.get(bit);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(bit, newNode);
                    node = newNode;
                }

                // compute max xor of that new number
                // with all previously inserted
                Character toggledBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggledBit)) {
                    currXor = (currXor << 1) | 1;
                    xorNode = xorNode.children.get(toggledBit);
                } else {
                    currXor = currXor << 1;
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, currXor);
        }

        return maxXor;
    }


}

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();

    public TrieNode() {
    }
}

