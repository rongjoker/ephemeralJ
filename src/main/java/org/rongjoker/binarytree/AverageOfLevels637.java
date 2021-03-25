package org.rongjoker.binarytree;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @date 02/04/2021
 *
 * 637. 二叉树的层平均值 https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
 *
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * 本质是二叉树的层次遍历
 *
 *
 */
public class AverageOfLevels637 {


    @Test
    public void test637(){

        //[2147483647,2147483647,2147483647]
        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(2147483647);
        root.right = new TreeNode(2147483647);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);

        System.out.println(averageOfLevelsOptimize1(root));


    }


    /**
     * 层次遍历，一层一层的计算，每次存储一层
     * 这种方法最符合直觉，但是效率不是最高，因为要不停的更新线性表（删除的复杂度都不低）,涉及到线性表，应该避免删除操作，
     * 可以采用2种方案优化，1是用链表进行二叉树遍历，2是将下标进行累加,不重置
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> list= new ArrayList<>();
        List<TreeNode> queue=new ArrayList<>();
        List<TreeNode> childQueue=new ArrayList<>();
        queue.add(root);

        double a = 0;int num=1;
        while (!queue.isEmpty()){
            root= queue.get(0);
            if(null!=root.left)
                childQueue.add(root.left);

            if(null!=root.right)
                childQueue.add(root.right);

            a+=root.val;
            queue.remove(0);




            if(queue.isEmpty()){
                list.add(a/num);

                a=0;num=childQueue.size();
                queue=childQueue;
                childQueue=new ArrayList<>();
            }

        }

        return list;

    }

    /**
     * 优化版本1 用链表替代线性表,并且利用位移向前，不需要保留每一层，这个方法天秀,不需要两层之间依次替换，不需要记录当前层是否轮训完
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevelsOptimize1(TreeNode root) {

        List<Double> list= new ArrayList<>();
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(root);

        double a = 0;
        int current_len=0;
        while (!queue.isEmpty()){
            current_len = queue.size();

            for (int i = 0; i < current_len; i++) {
                root= queue.poll();
                if(null!=root.left)
                    queue.add(root.left);

                if(null!=root.right)
                    queue.add(root.right);

                a+=root.val;
            }

            list.add(a/current_len);
            a=0;

        }

        return list;

    }



}
