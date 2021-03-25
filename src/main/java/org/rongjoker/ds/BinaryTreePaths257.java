package org.rongjoker.ds;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @date 03/24/2021
 * 257. 二叉树的所有路径 https://leetcode-cn.com/problems/binary-tree-paths/
 * dfs题目 返回所有的可能,实质上是dfs分支的回溯算法
 *
 */
public class BinaryTreePaths257 {



    @Test
    public void test257(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);


//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(3);
//        root.right.right.right = new TreeNode(4);
//        root.right.right.right.right = new TreeNode(5);
//        root.right.right.right.right.right = new TreeNode(6);

        System.out.println(binaryTreePaths(root));



    }

    List<List<Integer>> list = new ArrayList<>();
    Deque<Integer> deque = new ArrayDeque<>();


    public List<String> binaryTreePaths(TreeNode root) {
        this.path(root);
        int len;

        List<String> ss = new ArrayList<>();

        for (List<Integer> integers : list) {
            len = integers.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                if(i==0)
                    sb.append(integers.get(i));
                else sb.append("->").append(integers.get(i));

            }

            ss.add(sb.toString());

        }


        return ss;



    }


    public void path(TreeNode root){
        if(root==null)return;

        deque.addLast(root.val);

        if(root.left==null && root.right==null){
            list.add(new ArrayList<>(deque));
        }else{//这里做了优化

            if(root.left!=null){
                path(root.left);
                deque.removeLast();
            }

            if(root.right!=null){
                path(root.right);
                deque.removeLast();
            }
        }


    }



}
