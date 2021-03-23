package org.rongjoker.ds;

import org.junit.Test;
import org.rongjoker.binarytree.TreeNode;

/**
 * @date 03/23/2021
 *
 * 112. 路径总和 https://leetcode-cn.com/problems/path-sum/
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 典型的dfs题目
 *
 * 递归中间如何跳过递归:对非计算的部分进行返回，可以直接跳出递归；返回计算的部分则会一直计算下去
 *
 */
public class pathSum112 {


    @Test
    public void test112(){

        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);;
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);

        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);


        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println(hasPathSum(root,22));


    }


    public boolean hasPathSum(TreeNode root, int targetSum) {

        if(root==null)return false;

        if(root.left==null && root.right==null)
            return root.val==targetSum;

        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);


    }







}
