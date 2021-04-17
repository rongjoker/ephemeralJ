package org.rongjoker.sw;

import org.junit.Test;

import java.util.TreeSet;

/**
 * @date 04/16/2021
 * 220. 存在重复元素 III  https://leetcode-cn.com/problems/contains-duplicate-iii/
 *
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class ContainsNearbyAlmostDuplicate220 {

    @Test
    public void test220(){

        System.out.println(containsNearbyAlmostDuplicate(new int[]{2147483640,2147483641},1,100));
    }



    //比如nums[i]=4,t=2,则存在2 -- 6之间即可，即nums[i]-t 和 nums[i]+t 之间
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {

            Long  floor = set.floor((long)nums[i] + (long)t);//防止溢出

            if(floor!=null &&  floor >= ((long)nums[i] - (long)t))
                return true;

            set.add((long)nums[i]);

            if(i>=k){//用这种下标方式删除来维护滑动窗口的效果更好
                set.remove((long)nums[i-k]);
            }


        }

        return false;


    }



}
