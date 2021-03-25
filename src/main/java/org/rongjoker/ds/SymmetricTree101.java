package org.rongjoker.ds;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

/**
 * @date 03/24/2021
 *
 * 101. 对称二叉树 https://leetcode-cn.com/problems/symmetric-tree/
 * dfs题目
 *
 *
 */
public class SymmetricTree101 {


    @Test
    public void test101(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);;
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);


        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(4);

        System.out.println(isSymmetric(root));


    }



    public boolean isSymmetric(TreeNode root) {

        if(null==root)return false;

        return isSymmetric(root.left, root.right);



    }

    public boolean isSymmetric(TreeNode left,TreeNode right) {

        if(left==null && right==null)
            return true;
        else if(left!=null && right!=null){
            if(left.val == right.val)
                return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
            else return false;
        }else return false;

    }


}
