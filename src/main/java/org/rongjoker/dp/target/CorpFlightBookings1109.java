package org.rongjoker.dp.target;

import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @date 08/31/2021
 * 1109. 航班预订统计
 *
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 *
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 *
 * 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
 *
 * 输入：bookings = [[1,2,10},{2,3,20},{2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CorpFlightBookings1109 {


    @Test
    public void test1109() {

        System.out.println(Arrays.toString(corpFlightBookings2(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5)));
//        System.out.println(Arrays.toString(corpFlightBookings(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2)));


    }

    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] nums = new int[n];
        for (int[] booking : bookings) {
            nums[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }



    public int[] corpFlightBookings(int[][] bookings, int n) {

        int[] ans = new int[n];

        for (int[] booking : bookings) {
            for (int i = booking[0]; i <= booking[1]; i++) {
                ans[i - 1] += booking[2];
            }
        }
        return ans;
    }


}
