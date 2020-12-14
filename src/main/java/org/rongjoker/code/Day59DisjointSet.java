package org.rongjoker.code;

import org.junit.Test;

/**
 * 倒计时59天，解决并查集
 *
 * @date 10/28/2020
 */
public class Day59DisjointSet {

    @Test
    public void testDisjointSet() {

        int[][] edge = {{0, 1}, {1, 2}, {1, 3},
                {2, 4},
                {3, 4}, {2, 5}

        };

        int length = edge.length;

        int[] parent = new int[6];
        initialize(parent);
        int i;
        for (i = 0; i < length; i++) {
            int x = edge[i][0];
            int y = edge[i][1];
            if (union_vertices(x, y, parent) == 0) {

                System.out.println(i+"-cycle detected!");
                System.exit(0);
            }

        }

        System.out.println("no cycle found!");


    }


    public void initialize(int[] parent) {

        int n = parent.length;
        int i;
        for (i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /**
     * 向上找根节点
     *
     * @param x
     * @param parent
     * @return
     */
    int find_root(int x, int[] parent) {
        int x_root = x;
        while (parent[x_root] != -1) {
            x_root = parent[x_root];
        }

        return x_root;
    }


    /**
     * 合并2个节点 返回1表示可以合并；返回0表示合并失败：2个节点在一个集合里
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

        parent[x_root] = y_root;//两个集合合并为1个集合,此处没有优化，性能会比较差，可以根据两棵树的度(rank)来优化，小的往大的上拼，rank不变
        return 1;

    }


}
