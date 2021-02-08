package org.rongjoker.ds;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 02/08/2021
 * <p>
 * 684. 冗余连接 https://leetcode-cn.com/problems/redundant-connection/
 * 并查集,找到冗余的边，即形成环的边
 */
public class RedundantConnection684 {


    @Test
    public void test684() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};

        System.out.println(Arrays.toString(findRedundantConnection(edges)));
    }


    int[] parent, rank;
    int len;


    public void initialize(int[][] edges) {
        len = edges.length;
        parent = new int[len+1];
        rank = new int[len+1];

        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }


    }

    public int findParent(int index) {
        return parent[index] != index ? findParent(parent[index]) : index;
    }

    public void union(int x, int y) {

        x = findParent(x);
        y = findParent(y);

        if (rank[x] > rank[y])
            parent[y] = x;
        else if (rank[y] > rank[x])
            parent[x] = y;
        else {
            parent[x] = y;
            rank[y]++;
        }

    }


    public int[] findRedundantConnection(int[][] edges) {

        this.initialize(edges);

        int [] redundant= new int[2];

        for (int i = 0; i < len; i++) {
            if(findParent(edges[i][0])==findParent(edges[i][1]))
                redundant = edges[i];
            else
                union(edges[i][0],edges[i][1]);
        }

        return redundant;
    }


}
