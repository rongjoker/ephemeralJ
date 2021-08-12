package org.rongjoker.bytedance;

import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层序遍历  https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 *
 *
 */
public class ZigzagLevelOrder103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(null==root) return array;

        List<TreeNode> next = new ArrayList<>();
        next.add(root);
        zigzagLevelOrder(next,true);

        return array;


    }

    List<List<Integer>> array = new ArrayList<>();

    public void zigzagLevelOrder(List<TreeNode> prev,boolean seq) {
        if(prev.size()==0)return;
        List<TreeNode> next = new ArrayList<>();
        List<Integer> nextVal = new ArrayList<>();

        for(TreeNode p:prev){
            if(p.left!=null)next.add(p.left);
            if(p.right!=null)next.add(p.right);
            if(seq)nextVal.add(p.val);
        }

        if(!seq){
            for(int i = prev.size()-1;i>=0;--i)nextVal.add(prev.get(i).val);
        }
        array.add(nextVal);
        zigzagLevelOrder(next,!seq);

    }





}
