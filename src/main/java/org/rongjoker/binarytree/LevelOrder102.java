package org.rongjoker.binarytree;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @date 02/04/2021
 *
 * 102. 二叉树的层序遍历  https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 *
 */
public class LevelOrder102 {

    @Test
    public void test102(){

        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(2147483647);
        root.right = new TreeNode(2147483647);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);

        System.out.println(levelOrder(root));


    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();

        LinkedList<TreeNode> queue=new LinkedList<>();
        if(null!=root)
            queue.add(root);

        int len;
        while (!queue.isEmpty()){
            len = queue.size();

            List<Integer> current = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                current.add(poll.val);

                if(poll.left!=null)
                    queue.add(poll.left);

                if(poll.right!=null)
                    queue.add(poll.right);
            }

            lists.add(current);
        }

        return lists;


    }


}
