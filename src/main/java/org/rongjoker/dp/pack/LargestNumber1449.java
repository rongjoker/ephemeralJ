package org.rongjoker.dp.pack;

import org.junit.Test;

/**
 * @date 06/07/2021
 * 1449. 数位成本和为目标值的最大数字 https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/
 *
 * 完全背包
 *
 */
public class LargestNumber1449 {

    //[5,4,4,5,5,5,5,5,5]
    //29

    @Test
    public void test1449() {
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("1234");
//        stringBuilder.insert(0,9);
//        System.out.println(stringBuilder);
        System.out.println(largestNumber(new int[]{5,4,4,5,5,5,5,5,5},29));//"9333333"
//        System.out.println(largestNumber(new int[]{4,3,2,5,6,7,2,5,5},9));
//        System.out.println(largestNumber(new int[]{7,6,5,5,5,6,8,7,8},12));
//        System.out.println(largestNumber(new int[]{2,4,6,2,4,6,4,4,4},5));
        System.out.println(largestNumber(new int[]{6,10,15,40,40,40,40,40,40},47));


    }


    public String largestNumber(int[] cost, int target) {

        String[] dp = new String[target + 1];
        dp[0] = "";

        int min = Integer.MAX_VALUE;
        for (int i : cost) {
            min = Math.min(min, i);
        }
        if (min > target) return "0";

        //从低到高，更容易进入长度比较
        for (int i = 1; i <=9; i++) {
            for (int j = cost[i-1]; j <= target; ++j) {
                String temp = dp[j - cost[i-1]];
                if (temp == null) continue;
                temp = i +  temp;// 因为是从低到高枚举的物品，所以，优先把数字放在前面
//                if (null == dp[j] || temp.length()> dp[j].length() ||temp.compareTo(dp[j]) > 0)
                if(compare(temp,dp[j] ))
                    dp[j] = temp;
            }
        }

        //从大到小，会非常慢
//        for (int i = 9; i >0; i--) {
//            for (int j = cost[i-1]; j <= target; ++j) {
//                String temp = dp[j - cost[i-1]];
//                if (temp == null) continue;
//                temp = temp + i;// 因为是从低到高枚举的物品，所以，优先把数字放在前面
////                if (null == dp[j] || temp.length()> dp[j].length() ||temp.compareTo(dp[j]) > 0)
//                if(compare(temp,dp[j] ))
//                    dp[j] = temp;
//            }
//        }


        return dp[target] != null ? dp[target] : "0";


    }


    // 判断两个字符串组成的数字的大小
    private boolean compare(String a, String b) {
        if(b == null) return true;
        int n = a.length(), m = b.length();
        if(n > m) return true;
        if(m > n) return false;

        for(int i = 0; i < n; i++) {
            if(a.charAt(i) > b.charAt(i)) return true;
            else if(a.charAt(i) < b.charAt(i)) return false;
        }

        return true;
    }


}
