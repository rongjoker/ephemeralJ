package org.rongjoker.ds;

import org.junit.Test;

/**
 * @date 01/25/2021
 * <p>
 *  200. 岛屿数量 https://leetcode-cn.com/problems/number-of-islands/
 *  并查集的方式解决
 *  并查集的方式略微麻烦，因为需要构造一个并查集的初始方案,即可以把所有的1看作一个独立的小岛，能够合并的小岛最终形成一个大的岛，没合并1个，相当于减少1个独立的岛，合并结束，剩余的岛的数量即为结果
 */
public class NumberOfIslands200ds {


    @Test
    public void test200() {

        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

        System.out.println(numIslands(grid));


    }


    int[] parent;
    int[] rank;
    int total;


    public int findParent(int index){
        return parent[index]==index?index:findParent(parent[index]);
    }


    public void union(int index,int previous){
        int root_pre = findParent(previous);
        int root_in = findParent(index);
        if(root_pre==root_in)return;

        if(rank[root_pre]>rank[root_in]){
            parent[root_in] = root_pre;
        }else if(rank[root_pre]<rank[root_in]){
            parent[root_pre] = root_in;
        }else {
            parent[root_in] = root_pre;
            rank[root_pre]++;

        }
        --total;//合并成功，岛屿-1

    }


    public int numIslands(char[][] grid) {

        int nr = grid.length;
        int nc = grid[0].length;

        parent =new int[(nr+1)*nc];
        rank =new int[(nr+1)*nc];

        //初始化并查集
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == '1') {
                    parent[i * nc + j] = i * nc + j;//父亲为自己
                    ++total;
                }
            }
        }


        //开始计算
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == '1') {
                    ds(grid, i, j, nr, nc);
                }

            }
        }


        return total;

    }

    /**
     * 并查集的方式，把一个个独立的小岛合并成一个大岛，最终形成几个不能合并的大岛，即结果
     * 计算的时候，左边和上面（即上次的右边和下面）已经计算过，不需要重复计算，只要计算右边和下面即可
     * 并查集是依次循环，而不是层次去遍历，故不会遗漏点
     *
     * @param grid
     * @param r
     * @param c
     */
    void ds(char[][] grid, int r, int c, int row, int column) {

        grid[r][c] = '0';

        if (r + 1 < row && grid[r + 1][c] != '0') {
            union((r + 1) * column + c,r * column + c);
        }

        if (c + 1 < column && grid[r][c + 1] != '0') {
            union(r * column + c + 1,r * column + c);
        }


    }


}
