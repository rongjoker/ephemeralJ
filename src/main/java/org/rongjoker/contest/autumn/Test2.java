package org.rongjoker.contest.autumn;

import java.util.Arrays;
import org.junit.Test;

/**
 *
 * 心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 *
 * 输入：cards = [1,2,8,9], cnt = 3
 *
 * 输出：18
 *
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 *
 * 输入：cards = [3,3,1], cnt = 1
 *
 * 输出：0
 *
 * 解释：不存在获取有效得分的卡牌方案。
 *
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 *
 *
 */
public class Test2 {


    @Test
    public void test2(){

        System.out.println(maxmiumScore(new int[]{1,4,8,9},3));
        System.out.println(maxmiumScore(new int[]{2,3,5,5,5,5},3));
        System.out.println(maxmiumScore(new int[]{1,2,8,9},3));
        System.out.println(maxmiumScore(new int[]{3,3,1},1));
        System.out.println(maxmiumScore(new int[]{3,3,1},3));




    }

    /**
     * 思路错误
     * @param cards
     * @param cnt
     * @return
     */
    public int maxmiumScore(int[] cards, int cnt) {
        int len = cards.length;
        Arrays.sort(cards);
        if(cnt==1){
            for (int i = len-1; i >=0; i--) {
                if(cards[i]%2==0)return cards[i];
            }
            return 0;
        }

        int sum = 0,ts = cnt;
        int index = len-1;
        int min_odd = Integer.MAX_VALUE,min_awe = Integer.MAX_VALUE;
        while (ts>0){
            int tt = cards[index];
            if(tt%2==0)min_awe = Math.min(tt,min_awe);
            else min_odd = Math.min(tt,min_odd);

            sum+=tt;

            index--;
            ts--;
        }
        if(len==cnt) return sum%2==0 ?sum :0;

        if(sum%2==0)return sum;


        int other_odd = 0,other_awe = 0;

        for (int i = index; i >=0; i--) {
            if(cards[i] % 2 == 0)other_awe = cards[i];
            else other_odd = cards[i];

            if(other_awe!=0 && other_odd!=0)break;
        }

        int max = Integer.MIN_VALUE;

        if(other_odd>0) max = (other_odd-min_awe);//   1 111

        if(other_awe>0) max = Math.max(other_awe-min_odd,max);

        return max==Integer.MIN_VALUE?0:sum+max;

    }


}
