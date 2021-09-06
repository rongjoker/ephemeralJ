package org.rongjoker.array;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

/**
 * 1996. 游戏中弱角色的数量
 *
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 *
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 *
 * 返回 弱角色 的数量。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：properties = [[5,5},{6,3},{3,6]]
 * 输出：0
 * 解释：不存在攻击和防御都严格高于其他角色的角色。
 *
 * 输入：properties = [[1,5},{10,4},{4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 */
public class NumberOfWeakCharacters1996 {
    
    @Test
    public void test1996(){

        System.out.println(numberOfWeakCharacters(new int[][]{{5,5},{6,3},{3,6}}));
        System.out.println(numberOfWeakCharacters(new int[][]{{1,5},{10,4},{4,3}}));
        System.out.println(numberOfWeakCharacters(new int[][]{{1,1},{2,1},{2,2},{1,2}}));//[[1,1},{2,1},{2,2},{1,2]]
        System.out.println(numberOfWeakCharacters(new int[][]{{7,9},{10,7},{6,9},{10,4},{7,5},{7,10}}));//[[7,9},{10,7},{6,9},{10,4},{7,5},{7,10]]

        
    }

    public int numberOfWeakCharacters(int[][] properties) {

        Arrays.sort(properties, Comparator.comparingInt(o -> o[0]));
        int ans = 0;

        int len = properties.length,maxIndex = len-1,candidate = len-1;
        for (int i = len - 2; i >= 0; i--) {

            if(properties[i][0] < properties[candidate][0]) maxIndex = candidate;

            if(properties[i][0] < properties[maxIndex][0] && properties[i][1] < properties[maxIndex][1])ans++;
            else if(properties[i][0] == properties[maxIndex][0] && properties[i][1] > properties[maxIndex][1]) {//之前的候选者失效
                maxIndex = i;
                candidate = i;
            }
            else if(properties[i][0] < properties[maxIndex][0] && properties[i][1] > properties[maxIndex][1] && properties[i][1] > properties[candidate][1]) candidate = i;
            //成为候选者


        }

        return ans;


    }

}
