package org.rongjoker.binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @date 02/04/2021
 *
 * 145. 二叉树的后序遍历  https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * 利用递归和栈来分别解决
 */
public class PostorderTraversal145 {

    @Test
    public void test144(){

        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(7);

        System.out.println(postorderTraversal(root));
        System.out.println(postorderTraversalOptimize(root));


    }

    List<Integer> list = new ArrayList<>();

    /**
     * 递归的方式来做，效率高，但是内存无法局部释放
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if(null!=root){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            list.add(root.val);
        }
        return list;

    }


    /**
     * 利用栈来实现,左节点先入栈，一直到最左节点，然后取出，找右节点，入栈，重复操作
     * 记录入栈的顺序，即先序遍历的顺序
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalOptimize(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root!=null || !stack.isEmpty()){
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return list;

    }



}
