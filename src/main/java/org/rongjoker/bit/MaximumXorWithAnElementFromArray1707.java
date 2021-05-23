package org.rongjoker.bit;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @date 05/17/2021
 * 1707. 与数组中元素的最大异或值 https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/
 * <p>
 * 相当于421的升级版本，更棒的板子题目
 * 边查询边插入，查询完当前值与之前所有值的比较之后，将当前值插入字典树
 */
public class MaximumXorWithAnElementFromArray1707 {


    @Test
    public void test1707() {
        System.out.println(Arrays.toString(maximizeXor(new int[]{536870912,0,534710168,330218644,142254206}
        , new int[][]{
                {558240772,1000000000}
        , {307628050,1000000000}, {3319300,1000000000},{2751604,683297522},
                        {214004,404207941}
        }
        )));
    }


    @Test
    public void rest2(){
        int[] ints = {536870912, 0, 534710168, 330218644, 142254206};
        int max = 0;
        for (int anInt : ints) {
            if (anInt <= 404207941){
                System.out.println(anInt+":"+Integer.toBinaryString(anInt));
                max = Math.max(max,anInt ^ 214004);
            }

        }
        System.out.println("----");
        System.out.println(536870912 ^ 214004);
        System.out.println(330218644 ^ 214004);//max
        System.out.println(142254206 ^ 214004);

        System.out.println(404207941 >> 31);
        System.out.println(max);
        System.out.println("-----");
        System.out.println(Integer.toBinaryString(536870912));
        System.out.println(Integer.toBinaryString(534710168));
        System.out.println(Integer.toBinaryString(330218644));//03
        System.out.println(Integer.toBinaryString(142254206));

        System.out.println("----");
        System.out.println(Integer.toBinaryString(404207941));



    }

    int maxLen;

    public int[] maximizeXor(int[] nums, int[][] queries) {

        maxLen = Math.max(Arrays.stream(queries).max(Comparator.comparingInt(xx -> xx[1])).get()[1],Arrays.stream(nums).max().getAsInt());
        maxLen = Integer.toBinaryString(maxLen).length();//动态选出最长的长度，避免写死字典树的长度
        int len = queries.length;
        int[] ans = new int[len];
        TrieNode root = new TrieNode();
        root.min = Integer.MAX_VALUE;
        for (int num : nums)
            insert(root, num);
        for (int i = 0; i < len; ++i) {
            ans[i] = query(root, queries[i][1], queries[i][0]);
        }
        return ans;


    }

    void insert(TrieNode root, int num) {
        root.min = Math.min(root.min,num);
        TrieNode cur = root;
        int temp;
        for (int i = maxLen; i >= 0; --i) {
            temp = num >> i & 1;
            if (cur.children[temp] == null) {
                cur.children[temp] = new TrieNode();
                cur.children[temp].min = num;
            }else {
                cur.children[temp].min = Math.min(cur.children[temp].min,num);
            }
            cur = cur.children[temp];
        }
    }

    int query(TrieNode root, int upper, int xor) {
        int  tempX;
        int max = 0;
        TrieNode xNode = root;
        for (int i = maxLen; i >= 0; --i) {
            if (xNode == null) {
                return -1;
            }
            if(xNode.min > upper) return -1;

            max <<= 1;
            tempX = xor >> i & 1;
            int cur = tempX ^ 1;
            //这里有严重的bug，可能找到的合适的值，实际上最终比upper大
            if (xNode.children[cur] != null && xNode.children[cur].min <= upper) {//这一步的比较不可遗漏，必须满足2个条件才向下推进
                //优先选大的
                max += 1;
            } else {
                cur = tempX;
            }
            xNode = xNode.children[cur];
        }

        return max;
    }

    static class TrieNode {
        int min = 0;//这里做优化
        TrieNode[] children = new TrieNode[2];
    }


}
