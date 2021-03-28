package org.rongjoker.binarytree;

import org.rongjoker.datastructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @date 03/28/2021
 * 173. 二叉搜索树迭代器 https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * 3月28的每日一题
 * 可以借助栈来实现
 */
public class BSTIterator {

    Deque<TreeNode> stack = new LinkedList<>();

    TreeNode current;
    int cur;

    public BSTIterator(TreeNode root) {
        current = root;
    }

    public int next() {
        while ((current!=null)){
            stack.push(current);
            current = current.left;
        }

        current = stack.pop();
        cur = current.val;
        current = current.right;
        return cur;
    }

    public boolean hasNext() {
        return current!=null || !stack.isEmpty();
    }




}
