package org.rongjoker.binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @date 02/04/2021
 *
 * 144. 二叉树的前序遍历  https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 利用递归和栈来分别解决
 */
public class InorderTraversal144 {

    @Test
    public void test144(){

        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(7);

        System.out.println(preorderTraversal(root));
        System.out.println(preorderTraversalOptimize(root));


    }

    List<Integer> list = new ArrayList<>();

    /**
     * 递归的方式来做，效率高，但是内存无法局部释放
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if(null!=root){
            list.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return list;

    }


    /**
     * 利用栈来实现,左节点先入栈，一直到最左节点，然后取出，找右节点，入栈，重复操作
     * 记录入栈的顺序，即先序遍历的顺序
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalOptimize(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();

        while (root!=null || !stack.isEmpty()){
            if (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }

        }

        return list;

    }



}
