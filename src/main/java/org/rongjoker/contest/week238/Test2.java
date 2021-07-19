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

        System.out.println(maxFrequency(new int[]{9930,9923,9983,9997,9934,9952,9945,9914,9985,9982,9970,9932,9985,9902,9975,9990,9922,9990,9994,9937,9996,9964,9943,9963,9911,9925,9935,9945,9933,9916,9930,9938,10000,9916,9911,9959,9957,9907,9913,9916,9993,9930,9975,9924,9988,9923,9910,9925,9977,9981,9927,9930,9927,9925,9923,9904,9928,9928,9986,9903,9985,9954,9938,9911,9952,9974,9926,9920,9972,9983,9973,9917,9995,9973,9977,9947,9936,9975,9954,9932,9964,9972,9935,9946,9966},3056));
        System.out.println(maxFrequency2(new int[]{9930,9923,9983,9997,9934,9952,9945,9914,9985,9982,9970,9932,9985,9902,9975,9990,9922,9990,9994,9937,9996,9964,9943,9963,9911,9925,9935,9945,9933,9916,9930,9938,10000,9916,9911,9959,9957,9907,9913,9916,9993,9930,9975,9924,9988,9923,9910,9925,9977,9981,9927,9930,9927,9925,9923,9904,9928,9928,9986,9903,9985,9954,9938,9911,9952,9974,9926,9920,9972,9983,9973,9917,9995,9973,9977,9947,9936,9975,9954,9932,9964,9972,9935,9946,9966},3056));



    }

    public int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int ans = 0,j=len-2,temp=k,prev_h = nums[len-1], prev_w=0;
        for(int i=len-1;i>ans;--i){
            int h = nums[i];
            temp+=((prev_h-h)*prev_w);
            while(j>=0&& temp>=(h-nums[j])){
                temp-=(h-nums[j--]);
            }
            prev_w =i-j-1;
            ans = Math.max(ans,prev_w);
            prev_h = h;

        }

        return ans+1;

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
