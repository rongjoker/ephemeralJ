package org.rongjoker.bit;

import org.junit.Test;

/**
 * @date 05/15/2021
 * @date 05/16/2021
 * 1803. 统计异或值在范围内的数对有多少 https://leetcode-cn.com/problems/count-pairs-with-xor-in-a-range/
 * <p>
 * 相当于421的升级版本，更棒的板子题目
 * 边查询边插入，查询完当前值与之前所有值的比较之后，将当前值插入字典树
 * 本质是查询所有异或运算小于high+1 和小于low的数量的差值，就是[high,low]的数据
 */
public class CountPairsWithXorInARange1803 {

    @Test
    public void test1803() {
        System.out.println(countPairs(new int[]{1, 4, 2, 7}, 2, 6));
        System.out.println(countPairs(new int[]{9, 8, 4, 2, 1}, 5, 14));
    }


    public int countPairs(int[] nums, int low, int high) {
        TrieNode root = new TrieNode();
        int ans = 0;
        for (int num : nums) {
            ans += (query(root, num, high + 1) - query(root, num, low));
            this.insert(num, root);
        }

        return ans;

    }

    void insert(int num, TrieNode root) {
        int temp;
        TrieNode cur = root;
        for (int i = 15; i >= 0; i--) {
            temp = (num >> i) & 1;
            if (cur.children[temp] == null)
                cur.children[temp] = new TrieNode();
            cur.children[temp].count++;
            cur = cur.children[temp];
        }

    }

    int query(TrieNode root, int num, int limit) {
        int ans = 0;
        TrieNode cur = root;
        int tempL, tempN;
        for (int i = 15; i >= 0; i--) {
            if (null == cur) break;
            tempL = (limit >> i) & 1;
            tempN = (num >> i) & 1;
            if (tempL == 1) {
                if (null != cur.children[tempN])
                    ans += cur.children[tempN].count;//当前的区间值为1，亦或运算为0，说明小于，这个分叉全部可取
                cur = cur.children[1 ^ tempN];//1，异或运算为1，说明当前位置和区间值相等，向下接着找
            } else {
                cur = cur.children[tempN];//0 区间值为0，找为0的那个分叉；为1的是比区间值大的数据，跳过
            }

        }
        return ans;

    }


    static class TrieNode {
        int count = 0;
        TrieNode[] children = new TrieNode[2];
    }
}
