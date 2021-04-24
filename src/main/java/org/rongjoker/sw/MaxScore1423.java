package org.rongjoker.sw;

/***
 * @date 04/24/2021
 * 1423. 可获得的最大点数 https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
 * 非常精巧的题目，表面上看是dp，实际上是反向求滑动窗口的最小值
 *
 *
 */
public class MaxScore1423 {

    public int maxScore(int[] cardPoints, int k) {


        int len = cardPoints.length;

        int left = 0 ,right = len - 1;
        int width = len - k;
        //滑动窗口最小值,窗口宽度为len-k
        int sum = 0,wid_val = 0;
        int ava = len-k;
        for (int i = 0;i<ava;++i)
            sum+=cardPoints[i];

        wid_val = sum;
        int temp = wid_val;

        for (int i = 0;i<k;++i){
            sum+=cardPoints[i+ava];
            wid_val = wid_val - cardPoints[i] + cardPoints[i+ava];
            temp = Math.min(temp,wid_val);
        }

        return sum - temp;

    }
}
