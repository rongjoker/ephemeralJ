package org.rongjoker.binarytree;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @date 02/04/2021
 *
 * 94. 二叉树的中序遍历  https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 利用递归和栈来分别解决
 */
public class InorderTraversal94 {

    @Test
    public void test94(){


        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(7);

        System.out.println(inorderTraversal(root));
        System.out.println(inorderTraversalOptimize(root));


    }

    List<Integer> list = new ArrayList<>();

    /**
     * 递归的方式来做，效率高，但是内存无法局部释放
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(null!=root){
            inorderTraversal(root.left);
            list.add(root.val);
            inorderTraversal(root.right);
        }
        return list;

    }


    /**
     * 利用栈来实现,左节点先入栈，一直到最左节点，然后取出，找右节点，入栈，重复操作
     *      * 利用栈来实现,当前节点先入栈，左节点再入栈，左节点出栈，当前节点出栈；将指针指向右节点继续操作
     *      * 本质是把所有的树看作只有当前节点+左节点
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalOptimize(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        while (root!=null || !stack.isEmpty()){
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }

        }

        return list;

    }



}
