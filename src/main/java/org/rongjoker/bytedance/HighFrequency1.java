package org.rongjoker.bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 *
 * 高频率题目
 */
public class HighFrequency1 {


    @Test
    public void test42(){
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{4,2,0,3,2,5}));

    }


    /**
     * 42. 接雨水 https://leetcode-cn.com/problems/trapping-rain-water/
     *
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 双指针，先从左到右扫，依次减去前一个的高度，遇到比左边高的，停止，记录面积，然后重新左指针更新，继续向右，
     * 如果扫描到达最右边，左指针未到达最右边，则从最右边向左指针重复上述操作
     *
     *
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) return 0;
        int left = 0, right = len - 1, index = 1, sum = 0, temp = 0;
        while (index <= right) {
            temp += (height[left] - height[index - 1]);//容器面积,因为遇到比left高的就会退出，所以不会出现负数的情况
            if (height[index] >= height[left]) {
                sum += temp;
                temp = 0;
                left = index;
            }
            ++index;
        }


        index = right - 1;
        temp = 0;
        while (index >= left) {
            temp += (height[right] - height[index + 1]);//容器面积
            if (height[index] > height[right]) {
                sum += temp;
                temp = 0;
                right = index;
            }
            --index;
        }

        return sum;

    }


    @Test
    public void test581(){
        System.out.println(findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
        System.out.println(findUnsortedSubarray(new int[]{1,4,2,3,5}));
        System.out.println(findUnsortedSubarray(new int[]{5,4,3,2,1}));
        System.out.println(findUnsortedSubarray(new int[]{1,2,3,4}));
        System.out.println(findUnsortedSubarray(new int[]{1,3,5,4,2}));

    }

    /**
     * 581. 最短无序连续子数组 https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
     * 用两个单调栈来不停的找左右2个节点
     *
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {

        int len = nums.length;

        if(len==1)return 0;

        Deque<Integer> left_queue = new ArrayDeque<>();
        left_queue.addLast(0);
        int left = len-1;
        for(int i = 1;i<len;++i){
            if(!left_queue.isEmpty() && nums[i]<nums[left_queue.peekLast()]) {
                while (!left_queue.isEmpty() && nums[i]<nums[left_queue.peekLast()])left_queue.pollLast();

                if(left_queue.isEmpty()) {
                    left = -1;
                    break;
                }
                left = Math.min(left_queue.peekLast(),left);//看看能否向左继续消灭
            }else left_queue.addLast(i);
        }


        if(left==len-1)return 0;

        Deque<Integer> right_queue = new ArrayDeque<>();
        right_queue.addLast(len-1);

        int right = 0;
        for(int i = len-2;i>=0;--i){
            if(!right_queue.isEmpty() && nums[i]>nums[right_queue.peekLast()]) {
                while (!right_queue.isEmpty() && nums[i]>nums[right_queue.peekLast()])right_queue.pollLast();
                if(right_queue.isEmpty()) {
                    right = len;
                    break;
                }
                right = Math.max(right_queue.peekLast(),right);//看看能否向右继续消灭
            }else right_queue.addLast(i);
        }

        return right - left -1 ;

    }


    @Test
    public void test60(){
        System.out.println(getPermutation(3,3));
    }


    /**
     *
     * 60. 排列序列 https://leetcode-cn.com/problems/permutation-sequence/
     *
     * @param n
     * @param k
     * @return
     */
    /**
     * 记录数字是否使用过
     */
    private boolean[] used;

    /**
     * 阶乘数组
     */
    private int[] factorial;

    private int n;
    private int k;

    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        calculateFactorial(n);

        // 查找全排列需要的布尔数组
        used = new boolean[n + 1];
        Arrays.fill(used, false);

        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }


    /**
     * @param index 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     * @param path
     */
    private void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }

        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 计算阶乘数组
     *
     * @param n
     */
    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }


    /**
     * 162. 寻找峰值 https://leetcode-cn.com/problems/find-peak-element/
     * 二分查找
     *
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {

        int len = nums.length;
        if(len==1)return 0;
        if(nums[0]>nums[1])return 0;
        if(nums[len-1]>nums[len-2])return len-1;

        int left = 1,right = len - 2;

        while(left<=right){
            int mid = left + ((right - left)>>1);
            if(nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1])return mid;
            else if(nums[mid]<nums[mid-1]) right = mid - 1;
            else left = mid +1;
        }
        return left;

    }


    @Test
    public void test915(){
        System.out.println(partitionDisjoint(new int[]{5,0,3,8,6}));
        System.out.println(partitionDisjoint(new int[]{1,1,1,0,6,12}));

    }


    /**
     * 915. 分割数组 https://leetcode-cn.com/problems/partition-array-into-disjoint-intervals/
     *
     * 给定一个数组 A，将其划分为两个连续子数组 left 和 right， 使得：
     * left 中的每个元素都小于或等于 right 中的每个元素。
     * 在完成这样的分组后返回 left 的长度。可以保证存在这样的划分方法。
     *
     *
     * @param nums
     * @return
     */
    public int partitionDisjoint(int[] nums) {
        int len = nums.length;
        int[] right_min = new int[len];
        right_min[len-1] = nums[len-1];
        for(int i = len-2;i>=0;--i)right_min[i] = Math.min(nums[i+1],right_min[i+1]);


        int index = 0,max = nums[0];
        for (int i = 0; i < len; i++) {
            max = Math.max(max,nums[i]);
            if(max<=right_min[i]){
                index = i;
                break;
            }
        }

        return index+1;

    }

    public int partitionDisjoint2(int[] A) {
        int n = A.length;
        int max = A[0];
        int leftMax = A[0];
        int pos = 0;
        for(int i = 0; i < n; i++){
            max = Math.max(max, A[i]);
            if(A[i] >= leftMax)
                continue;
            leftMax = max;
            pos = i;
        }
        return pos+1;
    }


    /**
     *
     * 838. 推多米诺 https://leetcode-cn.com/problems/push-dominoes/
     *
     *
     *
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {

        int N = dominoes.length();
        int[] indexes = new int[N+2];
        char[] symbols = new char[N+2];
        int len = 1;
        indexes[0] = -1;
        symbols[0] = 'L';

        for (int i = 0; i < N; ++i)
            if (dominoes.charAt(i) != '.') {
                indexes[len] = i;
                symbols[len++] = dominoes.charAt(i);
            }

        indexes[len] = N;
        symbols[len++] = 'R';

        char[] ans = dominoes.toCharArray();
        for (int index = 0; index < len - 1; ++index) {
            int i = indexes[index], j = indexes[index+1];
            char x = symbols[index], y = symbols[index+1];
            char write;
            if (x == y) {
                for (int k = i+1; k < j; ++k)
                    ans[k] = x;
            } else if (x > y) { // RL
                for (int k = i+1; k < j; ++k)
                    ans[k] = k-i == j-k ? '.' : k-i < j-k ? 'R' : 'L';
            }
        }

        return String.valueOf(ans);


    }


    @Test
    public void test670(){

//        System.out.println(maximumSwap(2736));
//        System.out.println(maximumSwap(9973));
        System.out.println(maximumSwap(98368));//98863
        System.out.println(maximumSwap(1993));//9913
        System.out.println(maximumSwap(43456));//63454

    }


    /**
     * 670. 最大交换 https://leetcode-cn.com/problems/maximum-swap/
     * 单调栈无法解决
     *
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        char[] charArray = s.toCharArray();

        // 记录每个数字出现的最后一次出现的下标
        int[] last = new int[10];
        for (int i = 0; i < len; i++) {
            last[charArray[i] - '0'] = i;
        }

        // 从左向右扫描，找到当前位置右边的最大的数字并交换
        for (int i = 0; i < len - 1; i++) {
            // 找最大，所以倒着找
            for (int d = 9; d > charArray[i] - '0'; d--) {
                if (last[d] > i) {
                    swap(charArray, i, last[d]);
                    // 只允许交换一次，因此直接返回
                    return Integer.parseInt(new String(charArray));
                }
            }
        }
        return num;
    }

    private void swap(char[] charArray, int index1, int index2) {
        char temp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = temp;
    }



}
