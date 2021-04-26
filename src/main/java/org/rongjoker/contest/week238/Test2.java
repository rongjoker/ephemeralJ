package org.rongjoker.contest.week238;

import org.junit.Test;

import java.util.Arrays;


/**
 * 1838. 最高频元素的频数
 *
 * 元素的 频数 是该元素在一个数组中出现的次数。
 *
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 *
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 滑动窗口
 *
 */
public class Test2 {

    @Test
    public void test2(){

        System.out.println(maxFrequency(new int[]{1,4,8,13},5));



    }


    public int maxFrequency(int[] nums, int k) {
        int len = nums.length;
        if(len==1)return 1;
        Arrays.sort(nums);

        int max = 1;
        int temp,space;
        for(int i = len-1;i>0;i--){
            temp = 1;
            space = k;
            for (int j = i-1; j >=0; j--) {
                space -= (nums[i] - nums[j]);
                if (space>=0) {
                    ++temp;
                }else break;
            }
            max = Math.max(max,temp);

        }

        return max;
    }


}
