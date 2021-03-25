package org.rongjoker.ds;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @date 03/23/2021
 *
 * 113. 路径总和 II https://leetcode-cn.com/problems/path-sum-ii/
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 112题目的升级版本，这个是要找到具体的路径
 * 通常用回溯法解决，本质也是dfs
 *
 */
public class pathSum113 {


    @Test
    public void test113(){

        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);;
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);

        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);


        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(pathSum(root,22));


    }

    List<List<Integer>> lists = new ArrayList<>();

    Deque<Integer> deque = new ArrayDeque<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        this.backtracking(root,targetSum);

        return lists;


    }



    public void backtracking(TreeNode root, int targetSum) {

        if(root==null)
            return ;

        deque.addLast(root.val);

        if(root.left==null && root.right==null && root.val==targetSum){
            lists.add(new ArrayList<>(deque));
        }

        if(null!=root.left){
            backtracking(root.left,targetSum-root.val);
            deque.removeLast();
        }

        if(null!=root.right){
            backtracking(root.right,targetSum-root.val);
            deque.removeLast();//这一步不可缺失
        }


    }



}
