package org.rongjoker.prefix;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * @date 06/17/2021
 * 面试题 17.23. 最大黑方阵 https://leetcode-cn.com/problems/max-black-square-lcci/
 *
 * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
 *
 * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-black-square-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindSquare {


    @Test
    public void test() {

        System.out.println(Arrays.toString(findSquare(new int[][]{{1}})));

    }


    /**
     * 前缀和迭代，超时
     * @param matrix
     * @return
     */
    public int[] findSquare(int[][] matrix) {

        int len = matrix.length;
        int[][] prefix = new int[len + 1][len + 1];
        for (int r = 0; r < len; ++r) {
            for (int c = 0; c < len; ++c) {
                prefix[r + 1][c + 1] = prefix[r][c + 1] + prefix[r + 1][c] + matrix[r][c] - prefix[r][c];
            }
        }
        int[] ans = new int[3];
        for (int r = 0; r < len; ++r) {
            for (int c = 0; c < len; ++c) {

                loop:for (int r1 = 0; r1 <= r; ++r1) {
                    for (int c1 = 0; c1 <= c; ++c1) {
                        if (c - c1 == r - r1) {
                            int span = r - r1 + 1;
                            int sum_out = prefix[r + 1][c + 1] - prefix[r + 1][c1] - prefix[r1][c + 1] + prefix[r1][c1];

                            if (c - c1 == 0) {
                                if (sum_out == 0 && span > ans[2]) {
                                    ans[0] = r1;
                                    ans[1] = c1;
                                    ans[2] = span;
                                    break loop;
                                }
                            } else {
                                int sum_in = prefix[r][c] - prefix[r][c1 + 1] - prefix[r1 + 1][c] + prefix[r1 + 1][c1 + 1];
                                if (sum_out - sum_in == 0 && span > ans[2]) {
                                    ans[0] = r1;
                                    ans[1] = c1;
                                    ans[2] = span;
                                    break loop;
                                }
                            }
                        }
                    }
                }
            }
        }

        return ans[2] == 0 ? new int[]{} : ans;


    }
}
