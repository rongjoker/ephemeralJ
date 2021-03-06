package org.rongjoker.ds;

import org.junit.Test;

/**
 * 并查集-按照rank优化
 *
 * @date 01/20/2021
 * <p>
 * 并查集用来检测多个【边】会不会形成环,初始化所有点的父节点为-1
 * 过程是不停的检测每条边的2个端点的根结点是否相等，不想等则可以直接合并，将其中一个点设为另一个点的父节点,按照rank来减少2个集合合并的时候出现度的上升
 */
public class DisjointSetRank {

    int[] parent = new int[6];
    int[] rank = new int[6];

    @Test
    public void testDisjointSet() {

        int[][] edge = {{0, 1}, {1, 2}, {1, 3},
                {2, 4},
                {3, 4}, {2, 5}

        };

        int length = edge.length;

        initialize(parent,rank);
        int i;
        for (i = 0; i < length; i++) {
            int x = edge[i][0];
            int y = edge[i][1];
            if (union_vertices(x, y, parent) == 0) {
                System.out.println("point-" + i + "-cycle detected!");
                System.exit(0);
            }

        }

        System.out.println("no cycle found!");


    }




    /**
     * 初始化父节点集合，所有的节点的父节点默认为-1
     *
     * @param parent 父节点集合
     */
    public void initialize(int[] parent,int[] rank) {

        int n = parent.length;
        int i;
        for (i = 0; i < n; i++) {
            parent[i] = -1;
            rank[i] = 0;
        }
    }

    /**
     * 向上找根节点
     * 用递归来做，简化代码
     *
     * @param x
     * @param parent
     * @return
     */
    int find_root(int x, int[] parent) {
        return parent[x] == -1?x:(find_root(parent[x],parent));
    }


    /**
     * 合并2个节点
     * 返回1表示可以合并；
     * 返回0表示合并失败：2个节点在一个集合里
     *
     * @param x
     * @param y
     * @param parent
     * @return
     */
    int union_vertices(int x, int y, int[] parent) {

        int x_root = find_root(x, parent);
        int y_root = find_root(y, parent);

        if (x_root == y_root)
            return 0;

        //可以根据两棵树的度(rank)来优化，小的往大的上拼，rank不变
        if(rank[x_root]>rank[y_root]){
            parent[y_root] = x_root;

        }else if(rank[x_root]<rank[y_root]){
            parent[x_root] = y_root;
        }else {//两边相等，合并，其中作为父亲的rank+1
            parent[x_root] = y_root;
            rank[y_root]++;
        }

        return 1;

    }


}
