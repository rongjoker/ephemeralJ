package org.rongjoker.binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @date 02/04/2021
 *
 * 1339. 分裂二叉树的最大乘积 https://leetcode-cn.com/problems/maximum-product-of-splitted-binary-tree/
 *
 */
public class MaximumProductOfSplittedBinaryTree1339 {


    @Test
    public void test1339(){


        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);

        System.out.println(maxProduct(root));

    }

    List<Long> list = new ArrayList<>();


    public long sum(TreeNode root){

        if(null!=root){
            long sum = root.val+sum(root.left)+sum(root.right);
            list.add(sum);
            return sum;
        }else return 0;

    }

    public int maxProduct(TreeNode root) {

        long sum = sum(root);
        long max = 0;
        long temp;
        int len=list.size();
        for (int i = 0; i < len; i++) {
            temp = list.get(i)*(sum - list.get(i));
            max=Math.max(max,temp);
        }
        max%=(Math.pow(10, 9) + 7);

        return (int)max;

    }

}
