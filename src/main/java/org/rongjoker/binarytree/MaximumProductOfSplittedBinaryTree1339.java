package org.rongjoker.binarytree;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

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

    Long maxs = 0L;

    public int maxProduct(TreeNode root) {
        int mode = (int)(Math.pow(10,9)+7);
        //long s = sum(root);
        max(root,sum(root));

        return (int)(maxs%mode);


    }

    public long max(TreeNode root,long total){
        if(root==null)return 0;
        long temp = root.val +max(root.left,total) +max(root.right,total);
        maxs = Math.max(maxs,(total - temp)*temp);
        return temp;
    }



    public long sum(TreeNode root){
        if(root==null)return 0;
        return root.val +sum(root.left) +sum(root.right);
    }

}
