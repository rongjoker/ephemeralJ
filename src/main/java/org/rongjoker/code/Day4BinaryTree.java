package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

public class Day4BinaryTree {

    //104. 二叉树的最大深度
    @Test
    public void testmaxDepth() {

    }

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }



}



