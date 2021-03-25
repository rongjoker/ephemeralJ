package org.rongjoker.binarytree;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @date 02/08/2021
 * 98. 验证二叉搜索树  https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 方法一：最直观的方法，将二叉树递归中序遍历，然后对结果进行双指针验证是否有序
 * 方法二：迭代中序遍历二叉树，在遍历过程中即可判断是否有序，效率更高
 * 方法三：递归分析每个节点的上限和下限 @todo
 *
 *
 */
public class ValidateBinarySearchTree98 {

    @Test
    public void test98() {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println(isValidBST3(root));


    }


    List<Integer> list = new ArrayList<>();


    /**
     * 中序遍历
     *
     * @param root
     */
    public void inTravel(TreeNode root) {

        if (null == root) return;

        inTravel(root.left);
        list.add(root.val);
        inTravel(root.right);

    }

    public boolean isValidBST(TreeNode root) {

        inTravel(root);

        int len = list.size();

        if (len <= 1) return true;

        int i = 0, j = 1;

        while (j < len) {

            if (list.get(j) > list.get(i)) {
                i = j;
                ++j;
            } else
                return false;

        }

        return true;

    }

    /**
     * 利用栈来实现中序遍历
     * 中序遍历的本质是把一个点入栈，然后将它的左子树入栈,为空则取出栈的数据，并将右节点入栈
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>();

        long prev =  Long.MIN_VALUE;

        while (!stack.isEmpty() || null!=root){

            if(null!=root){
                stack.push(root);
                root=root.left;
            }else {//左侧遍历完，左侧为空，开始右侧
                root = stack.pop();
                System.out.println(root.val);//只有此次为打印和排序，其他的操作都是入栈和出栈

                if(root.val<=prev)
                    return false;

                prev = root.val;

                root=root.right;
            }
        }


        return true;

    }


    /**
     * 利用递归来处理，不需要进行中序遍历
     * 验证中序遍历的原理即可
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);

    }


    /**
     * 左节点的范围为(负无穷,当前节点)；右节点的范围为(当前节点,正无穷)
     * @param root
     * @param min
     * @param max
     * @return
     */
    public boolean isValid(TreeNode root,long min,long max) {
        if(null==root)return true;

        if(root.val>=max || root.val<=min)
            return false;

        return isValid(root.left,min,root.val) && isValid(root.right,root.val,max);

    }


}
