package org.rongjoker.dp.target;

import org.junit.Test;

/**
 * @date 05/23/2021
 * 5765. 跳跃游戏 VII https://leetcode-cn.com/problems/jump-game-vii/
 * 第 242 场周赛
 * 区间和 dp
 *
 */
public class JumpGame7 {

    @Test
    public void test7() {
        System.out.println(canReach("011010", 2, 3));
        System.out.println(canReach("01101110", 2, 3));
        System.out.println(canReach("01000110110",2,3));
    }


    public boolean canReach(String s, int minJump, int maxJump) {

        int len = s.length();
        if (s.charAt(len - 1) == '1') return false;

        int[] prex = new int[len+1];
        prex[0] = 0;
        for (int i = 1; i <= minJump; i++) {
            prex[i] = 1;
        }

        boolean[] reach = new boolean[len];
        reach[0] = true;

        for (int i = minJump; i < len; i++) {
            int left = Math.max(i - maxJump,0), right = i - minJump;
            if(s.charAt(i) == '0'){
                int space = prex[right+1] - prex[left];
                reach[i] = space > 0;//区间有可抵达
            }
            prex[i+1] = prex[i] + (reach[i]?1:0);

        }

        return reach[len - 1];


    }
}
