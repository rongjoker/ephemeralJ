package org.rongjoker.dp.pack01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 416
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * 494
 * https://leetcode-cn.com/problems/target-sum/
 *
 * @date 01/05/2021
 * @date 01/06/2021
 * @date 01/07/2021 53. 最大子序和
 * @date 01/07/2021 152. 乘积最大子数组
 */
public class HomeWork {


    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * @date 01/07/2021
     */
    @Test
    public void test53() {

        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

//        System.out.println(maximumSubArray(array));
        System.out.println(maximumSubArray02(array));
    }

    public int maximumSubArray(int[] array) {

        int max = array[0];

        int[] dp = new int[array.length];

        dp[0] = array[0];

        for (int i = 1; i < array.length; i++) {

            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + array[i];
            } else {
                dp[i] = array[i];
            }

            if (dp[i] > max)
                max = dp[i];

        }

        return max;
    }


    /**
     * 优化空间,dp数组优化为一个int，即只保留前一位即可
     *
     * @param array
     * @return
     */
    public int maximumSubArray02(int[] array) {

        int max = array[0];
        int temp = array[0];

        for (int i = 1; i < array.length; i++) {

            if (temp > 0) {
                temp = temp + array[i];
            } else {
                temp = array[i];
            }

            max = Math.max(max, temp);

        }

        return max;
    }

    /**
     * 152. 最大子序乘积
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     *
     * @date 01/07/2021
     */
    @Test
    public void test152() {

        int[] array = {2, 3, -2, 4};
//        array = new int[]{-2, 0, -1};

//        System.out.println(maximumSubArray(array));
        System.out.println(maximumProductSubArray(array));


    }

    public int maximumProductSubArray(int[] nums) {

        int max = nums[0];

        int[] dp = new int[2];

        dp[0] = nums[0];
        dp[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int addTemp = nums[i] * dp[0];
            int minusTemp = nums[i] * dp[1];

            dp[0] = Math.max(minusTemp, Math.max(addTemp, nums[i]));
            dp[1] = Math.min(minusTemp, Math.min(addTemp, nums[i]));

            max = Math.max(max, Math.max(dp[0], dp[1]));

        }

        return max;
    }


    /**
     * 918. 环形子数组的最大和
     * <p>
     * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
     * <p>
     * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
     * <p>
     * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void test918() {

        int[] array = {1,-6,-7,4};

        System.out.println(maximumSumCircularSubArray(array));
    }


    /**
     * 沿用之前的线段和的方案,从中间取出连续的最小的线段,并处理特殊情况（全是负数）
     *
     * @param A
     * @return
     */
    public int maximumSumCircularSubArray(int[] A) {

        int sum = 0;
        boolean minus = true;
        for (int i = 0; i < A.length; i++) {
            if(A[i]>0)
                minus = false;
            sum += A[i];
        }

        //计算出最小线段和,如果最小线段和小于0，则数组的和减去【最小线段和】,如果大于0，则直接数组和
        int min = A[0];
        int temp = A[0];

        //特殊情况，如果数组和为负数,直接按线段和计算
        if(minus){
            return maximumSubArray02(A);
        }

        for (int i = 1; i < A.length; i++) {

            if (temp < 0) {
                temp = temp + A[i];
            } else {
                temp = A[i];
            }

            min = Math.min(min, temp);

        }

        if (min > 0)
            return sum;
        else {

            int max1 = sum - min;
            return Math.max(maximumSubArray02(A), max1);

        }
    }


    /**
     * 120. 三角形最小路径和 https://leetcode-cn.com/problems/triangle/
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     *
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void test120(){

        List<List<Integer>> triangle = new ArrayList<>();

        triangle.add(new ArrayList<>(){{
add(2);
        }});

        triangle.add(new ArrayList<>(){{
            add(3);
            add(4);
        }});

        triangle.add(new ArrayList<>(){{
            add(6);
            add(5);
            add(7);
        }});

        triangle.add(new ArrayList<>(){{
            add(4);
            add(1);
            add(8);
            add(3);
        }});



        System.out.println(minimumTotal(triangle));

    }

    /**
     * 自底向上，依次取最小的，直到最上面一层唯一的一个，结束
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle.size()==1)
            return triangle.get(0).get(0);

        int[][] dp = new int[2][triangle.size()+1];
        int n1 = triangle.size()-1;
        for (int i = n1; i >=0; i--) {

            int current = i%2;
            int previous = (i+1)%2;

            List<Integer> temps = triangle.get(i);
            int n2 = temps.size();
            for (int j = 0; j < n2; j++) {
                Integer integer = temps.get(j);
                dp[current][j] = Math.min(integer+ dp[previous][j],integer+dp[previous][j+1]);
            }
        }


        return dp[0][0];

    }


    /**
     * 198. 打家劫舍 https://leetcode-cn.com/problems/house-robber/
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */
    @Test
    public void test198(){


        int[] array = {2,1,1,2};

        System.out.println(robOptimize(array));
    }

    public int rob(int[] nums) {

        if(nums.length==0)return 0;

        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);

        int[] dp = new int[nums.length+2];//dp数组第一第二都默认为0，这样循环原可以从下标0开始

        for (int i = 0; i < nums.length; i++) {
            dp[i+2] = Math.max(dp[i] +nums[i],dp[i+1]);
        }

        return dp[nums.length+1];
    }

    /**
     * 利用滚动数组优化空间
     * @param nums
     * @return
     */
    public int robOptimize(int[] nums) {

        if(nums.length==0)return 0;

        if(nums.length==1)return nums[0];
        if(nums.length==2)return Math.max(nums[0],nums[1]);

        int dp1 = 0;
        int dp2 = 0;

        for (int i = 0; i < nums.length; i++) {
            int dp = Math.max(dp1 +nums[i],dp2);
            dp1 = dp2;dp2 = dp;
        }

        return dp2;
    }




}
