package org.rongjoker.greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 01/12/2021
 * <p>
 * 455. 分发饼干 https://leetcode-cn.com/problems/assign-cookies/
 * <p>
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/assign-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AssignCookies {

    @Test
    public void test455() {

        int[] g = {1, 2, 3}, s = {3};
        System.out.println(findContentChildrenOptimize(g, s));

    }

    public int findContentChildren(int[] g, int[] s) {
        int n = g.length, m = s.length;
        if (m == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int max = 0, flag = 0;
        for (int i = 0; i < n; i++) {
            for (int j = flag; j < m; j++) {
                flag++;
                if (s[j] >= g[i]) {
                    max++;
                    break;
                }
            }
        }
        return max;
    }

    /**
     * 优化掉一个变量，空间复杂度从8%提升到97%
     * @param g
     * @param s
     * @return
     */
    public int findContentChildrenOptimize(int[] g, int[] s) {
        int n = g.length, m = s.length;
        if (m == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int max = 0,i=0, j = 0;
        while (i < n){
            while (j < m){
                if (s[j] >= g[i]) {
                    j++;
                    max++;
                    break;
                }
                j++;
            }
            i++;
        }
        return max;
    }


}
