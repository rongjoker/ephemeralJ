package org.rongjoker.binarysearch;


import org.junit.Test;

/**
 * @date 06/18/2021
 * LCP 12. 小张刷题计划 https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua/
 * 为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。
 *
 * 在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。
 *
 * 我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
public class MinTime12 {


    //[50,47,68,33,35,84,25,49,91,75]
    //1

    @Test
    public void test12(){

        System.out.println(minTime(new int[]{1,2,3,3},2));
        System.out.println(minTime(new int[]{999,999,999},4));
        System.out.println(minTime(new int[]{1,2,3,3,3},2));
        System.out.println(minTime(new int[]{50,47,68,33,35,84,25,49,91,75},1));

        System.out.println(check(new int[]{50,47,68,33,35,84,25,49,91,75},1,240));
//        System.out.println(check(new int[]{999,999,999},4,800));


    }


    public int minTime(int[] time, int m) {

        if(m>=time.length)return 0;

        int sum = 0;

        for (int t : time) sum+=t;

        int left = 0,right = sum,ans = sum;

        while (left<=right){
            int mid = left + ((right - left)>>1);
            if(check(time,m,mid)){
                ans = Math.min(ans,mid);
                right = mid - 1;
            }else left = mid + 1;
        }


        return ans;
    }

    public boolean check(int[] time, int m,int max){
        int count = 0;

        int temp_max = 0,temp_sum = 0;
        for (int t : time) {
            temp_max = Math.max(t,temp_max);
            temp_sum += t;
            if(temp_sum > max + temp_max){
                count++;
                if(count>m)return false;
                temp_max = t;
                temp_sum = t;
            }
        }

        if(temp_sum>=0)count++;//这里是关键，只要有剩余，肯定会占用一天

        return count<=m;
    }



}
