package org.rongjoker.ds;

import org.junit.Test;

/**
 * @date 01/26/2021
 * <p>
 * 547. 省份数量 https://leetcode-cn.com/problems/number-of-provinces/
 * 并查集和dfs都可以解决
 */
public class NumberOfProvinces547ds {

    @Test
    public void test547() {
        int[][] grid = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(grid));

    }


    int[] parent;
    int[] rank;


    void initialize(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int index) {
        return parent[index] == index ? index : find(parent[index]);
    }

    void union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);
        if (root_x == root_y) return;
        if (rank[root_x] > rank[root_y]) {
            parent[root_y] = root_x;
        } else if (rank[root_x] < rank[root_y]) {
            parent[root_x] = root_y;
        } else {
            parent[root_x] = root_y;
            rank[root_y]++;
        }
    }


    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length,provinces=0;
        initialize(n);

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(isConnected[i][j]==1){
                    union(i,j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if(parent[i]==i)++provinces;

        }

        return provinces;

    }
}
