package org.rongjoker.binarytree;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

/**
 * @date 02/08/2021
 *
 * 226. 翻转二叉树 https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * 是指将二叉树的两个子节点互相交换
 * 用递归来做，借助一个临时变量，性能最高，空间占用比较大
 * 也可以用层次遍历的方式来做，节省空间
 *
 */
public class InvertBinaryTree226 {


    @Test
    public void test226(){

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println(invertTree(root));



    }



    public TreeNode invertTree(TreeNode root) {

        if(null==root)
            return null;

        TreeNode temp = root.left;

        root.left = root.right;
        root.right=temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;

    }
}
