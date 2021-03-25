package org.rongjoker.ds;


import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

/**
 * @date 03/24/2021
 *
 * 111. 二叉树的最小深度 https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * dfs题目 和求最大相反的是求最小需要排除掉为null的节点
 *
 *
 */
public class MinimumDepthOfBinaryTree111 {

    @Test
    public void test111(){


        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);;
        root.right = new TreeNode(2);

//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);


//        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        root.right.right.right.right.right = new TreeNode(6);

        System.out.println(minDepth(root));

    }

    public int minDepth(TreeNode root) {

        if(null==root)return 0;

        return minDepthFetch(root);

    }



    public int minDepthFetch(TreeNode root) {

        if(null!=root.left &&null==root.right)
            return minDepthFetch(root.left)+1;
        else if(null==root.left &&null!=root.right)
            return minDepthFetch(root.right)+1;
        else if(null!=root.left){
            return Math.min(minDepthFetch(root.left), minDepthFetch(root.right))+1;
        }else {
            return 1;
        }

    }


}
