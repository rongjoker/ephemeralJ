package org.rongjoker.bit;

import org.junit.Test;

import java.util.HashMap;

/**
 * @date 04/20/2021
 * @date 05/14/2021
 * 421. 数组中两个数的最大异或值 https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXorOfTwoNumbersInAnArray421 {




    @Test
    public void test421(){
        System.out.println(4 | 1);
        System.out.println(3 | 1);
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

    public int findMaximumXOR(int[] nums) {
        int maxNum = nums[0];
        for (int num : nums) maxNum = Math.max(maxNum, num);
        int len = (Integer.toBinaryString(maxNum)).length();
        int n = nums.length, bitmask = 1 << len;//最大值+1(比如一共有5位，那么bitmask是100000)

        String[] strNums = new String[n];
        //这一步利用异或运算将所有数据都格式化为统一的二进制长度
        //-1就是最大的位数，bitmask其他位数都是0，这保证了计算完的数据依然是原数据，只是长度达成一致
        for (int i = 0; i < n; ++i)
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);

        TrieNode trie = new TrieNode();
        int maxXor = 0;
        for (String num : strNums) {
            TrieNode node = trie, xorNode = trie;
            int currXor = 0;
            for (Character bit : num.toCharArray()) {
                if (node.children.containsKey(bit)) {
                    node = node.children.get(bit);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(bit, newNode);
                    node = newNode;
                }

                // 互补的数字就是异或可以得到1的数字，当前为1，则互补为0，反之亦反
                Character toggledBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggledBit)) {
                    currXor = (currXor << 1) | 1;//前进一位，用1进行或运算，即当前位变成最大值1
                    xorNode = xorNode.children.get(toggledBit);//有互补的数字(异或得到最大值1的那一部分)
                } else {//没有则继续走下去
                    currXor = currXor << 1;//前进一位，当前位变成0(没有互补，异或得到的是0)
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, currXor);
        }

        return maxXor;
    }


}

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();

    public TrieNode() {
    }
}

