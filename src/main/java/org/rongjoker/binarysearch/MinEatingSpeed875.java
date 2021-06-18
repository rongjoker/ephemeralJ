package org.rongjoker.binarysearch;

import org.junit.Test;

/**
 * @date 06/18/2021
 *
 * 875. 爱吃香蕉的珂珂 https://leetcode-cn.com/problems/koko-eating-bananas/
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 极小化极大
 *
 *
 */
public class MinEatingSpeed875 {


    @Test
    public void test875(){
        System.out.println(minEatingSpeed(new int[]{3,6,7,11},8));
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20},5));
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20},6));
    }


    public int minEatingSpeed(int[] piles, int h) {

        int len = piles.length;

        //排序后有一点点提速
//        Arrays.sort(piles);
//        int max = piles[len-1];
        int max = 0;
        for (int pile : piles) max = Math.max(max,pile);

        int left = 1,right = max,ans = max;;

        while (left<=right){
            int mid = left + ((right - left)>>1);
            if(check(piles,len,h,mid)){
                ans = Math.min(ans,mid);
                right = mid -1;
            }else left = mid +1;
        }

        return ans;
    }


    public boolean check(int[] piles, int len,int h,int min){

        int count = 0;

        for (int i = len-1; i >=0; i--) {
            count += (piles[i]/min);
            if(piles[i]%min>0)count++;
            if(count>h)return false;
        }
        return true;

    }
}
