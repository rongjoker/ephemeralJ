package org.rongjoker.ds;

import org.junit.Test;
import org.rongjoker.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 03/25/2021
 * 337. 打家劫舍 III
 * dp+dfs
 * 利用hashmap做记忆化搜索来加速
 * 利用最初的打家劫舍的办法来做记忆化搜索
 */
public class HouseRobber337 {

    @Test
    public void test337() {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);


//        root.right.right = new TreeNode(6);


//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(3);
//        root.right.right.right = new TreeNode(4);
//        root.right.right.right.right = new TreeNode(5);
//        root.right.right.right.right.right = new TreeNode(6);

        System.out.println(rob(root));


    }

    public Map<TreeNode, Integer> map = new HashMap<>();

    //这种方法没有利用到记忆化搜索，复杂度很高(10%)
    //加入hashmap记录，复杂度(50%)
    public int rob(TreeNode root) {

        if (root == null)
            return 0;

        if (map.get(root) != null)
            return map.get(root);


        int max = Math.max(root.val + (null != root.left ? (rob(root.left.left) + rob(root.left.right)) : 0)
                        + (null != root.right ? (rob(root.right.left) + rob(root.right.right)) : 0)
                , rob(root.left) + rob(root.right));

        map.put(root, max);

        return max;


    }



    //返璞归真
    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }



}
