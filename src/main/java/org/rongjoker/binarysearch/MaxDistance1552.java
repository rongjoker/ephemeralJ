package org.rongjoker.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 06/18/2021
 * 1552. 两球之间的磁力 https://leetcode-cn.com/problems/magnetic-force-between-two-balls/
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 * <p>
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 * <p>
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/magnetic-force-between-two-balls
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 极大化极小
 *
 */
public class MaxDistance1552 {

    @Test
    public void test1552() {

        System.out.println(maxDistance(new int[]{1,2,3,4,7},3));
        System.out.println(maxDistance(new int[]{5,4,3,2,1,1000000000},2));


    }


    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int len = position.length;
        int left = 1, right = position[len - 1] - position[0];
        if (m == 2) return right;
        if (m == position[len - 1]) return left;
        int ans = left;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(position, len,m, mid)) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            } else right = mid - 1;
        }

        return ans;
    }

    public boolean check(int[] position,int len, int m,int distance) {
        int count = 1;
        int left = 0,right = left +1;
        while (right<len){
            if(position[right] - position[left]>=distance){
                count++;
                left = right;
                right = left +1;
                if(count>=m)return true;
            }else right++;
        }
        return false;
    }


}
