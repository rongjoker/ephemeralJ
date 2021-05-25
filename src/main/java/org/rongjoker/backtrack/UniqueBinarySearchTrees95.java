package org.rongjoker.backtrack;

import org.junit.Test;
import org.rongjoker.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 05/20/2021
 * 95. 不同的二叉搜索树 II https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 * 递归+备忘录
 */
public class UniqueBinarySearchTrees95 {

    @Test
    public void test95(){
        System.out.println(generateTrees(3));
    }


    public List<TreeNode> generateTrees(int n) {
        return backtrack2(1,n);

    }

    /**
     * 利用null作为哨兵，优化代码
     * @param start
     * @param max
     * @return
     */
    public List<TreeNode> backtrack2(int start,int max){
        List<TreeNode> list = new ArrayList<>();
        if (start <= 0 || max < start) {
            list.add(null);
            return list;
        }
        for(int i = start;i<=max;++i){
            List<TreeNode> left = backtrack2(start,i-1);
            List<TreeNode> right = backtrack2(i+1,max);

            for(TreeNode l:left){
                for(TreeNode r:right){
                    TreeNode cur = new TreeNode(i);
                    cur.left = l;
                    cur.right = r;
                    list.add(cur);
                }
            }
        }

        return list;
    }

    /**
     *
     * 标准情况，标准的树形回溯
     * @param start
     * @param max
     * @return
     */
    public List<TreeNode> backtrack(int start,int max){
        List<TreeNode> list = new ArrayList<>();
        if (start <= 0 || max < start) {
            list.add(null);
            return list;
        }
        for(int i = start;i<=max;++i){
            List<TreeNode> left = backtrack(start,i-1);
            List<TreeNode> right = backtrack(i+1,max);

            if(left.size()>0 && right.size()>0){
                for(TreeNode l:left){
                    for(TreeNode r:right){
                        TreeNode cur = new TreeNode(i);
                        cur.left = l;
                        cur.right = r;
                        list.add(cur);
                    }
                }
            }else if (left.size()>0){
                for(TreeNode l:left){
                    TreeNode cur = new TreeNode(i);
                    cur.left = l;
                    list.add(cur);

                }
            }else if (right.size()>0){
                for(TreeNode r:right){
                    TreeNode cur = new TreeNode(i);
                    cur.right = r;
                    list.add(cur);

                }
            }else list.add(new TreeNode(i));

        }

        return list;
    }



}
