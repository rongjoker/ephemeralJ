package org.rongjoker.bytedance;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 高频率题目
 */
public class HighFrequency2 {



    @Test
    public void test33(){
        System.out.println(search(new int[]{4,5,6,7,0,1,2},0));

    }


    /**
     * 33. 搜索旋转排序数组 https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
     *
     *
     */
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length -1;
        while(left<right){
            int mid = left + ((right - left)>>1);
            if(nums[mid]==target)return mid;

            if(nums[mid]<nums[right]){
                if(nums[mid]<target && nums[right]>target)left = mid +1;
                else right = mid-1;
            }else{
                if(nums[left]<target && nums[mid]>target)right = mid-1;
                else left = mid+1;

            }
        }


        if(nums[left]==target)return left;
        return -1;


    }


    /**
     *
     *
     *  160. 相交链表 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode tempA = headA, tempB =  headB;
        while(tempA!=tempB){
            if(tempA!=null) tempA = tempA.next;
            else tempA = headB;

            if(tempB!=null) tempB = tempB.next;
            else tempB = headA;
        }

        return tempA;


    }


    /**
     * 快速排序
     * @param array
     * @param lo
     * @param hi
     */
    public void quickSort2(int[] array, int lo, int hi) {

        if (lo >= hi) return;

        int temp = array[lo];

        int i = lo, j = hi;

        while (i < j) {//while是关键，只要i<j，循环就会走下去，知道i和j相遇，相遇点即数据中点，左边小右边大，相当于停留在正确的位置，随后左右各自继续重复操作，但是待排序总数递减了一

            while (array[j] >= temp && i < j)
                j--;

            if(i < j){//说明找到了一个比pivot小，但是放在了右边的元素，将它放到【最左边】,此时右边这个位置为空，可以方便左边做逼近寻找
                array[i] = array[j];
                i++;
            }//否则说明当前的轴点就是最小值，则不需要移动，也不会有下面的这些操作

            while (array[i] <= temp && i < j) {
                i++;
            }

            if(i < j){//说明找到了一个比pivot大，但是放在了左边的元素，将它放到原来右边空出来的位置,此时左边这个位置为空，可以方便右边做逼近寻找
                array[j] = array[i];
                j--;
            }

        }

        //结束后，i==j即轴点;大小数字已经正好分配到2边，把i点放入轴点数字

        array[i] = temp;//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        System.out.println("i:"+i+";j:"+j);

        //此时i=j=轴点，以此为分割左右
        quickSort2(array, lo, j - 1);  //对左边快排
        quickSort2(array, j + 1, hi); //对右边快排
    }


    /**
     * 15. 三数之和 https://leetcode-cn.com/problems/3sum/
     *
     *
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(len<3)return ans;
        Arrays.sort(nums);
        if(nums[0]>0)return ans;
        for(int p =0;p<len-1;p++){
            if(nums[p]>0)break;
            if(p>0 && nums[p] == nums[p-1])continue;
            int left = p+1,right = len-1;
            while(left<right){
                if( nums[right]<0)break;

                int sum = nums[left] + nums[right] +nums[p];
                if(sum==0){
                    List<Integer> a = new ArrayList<>();
                    a.add(nums[left]);
                    a.add(nums[p]);
                    a.add(nums[right]);
                    ans.add(a);

                    while(right > left && nums[left]==nums[left+1]){
                        left++;
                    }
                    while(right > left && nums[right]==nums[right-1]){
                        right--;
                    }

                    left++;
                    right--;
                }else if (sum<0)left++;
                else right--;


            }

        }

        return ans;

    }


    /**
     *
     * 516. 最长回文子序列 https://leetcode-cn.com/problems/longest-palindromic-subsequence/
     *
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        char[] cs = s.toCharArray();
        int[][] dp = new int[len][len];//起止
        for(int i=0;i<len;i++)dp[i][i]=1;
        for(int i=len-1;i>=0;--i){
            for(int j=i+1;j<len;++j){
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1] + 2;
                else dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
            }
        }
        return dp[0][len -1];
    }


    /**
     *
     * 83. 删除排序链表中的重复元素 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     *
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null)
            return null;
        if(head.next==null)
            return head;

        head.next = deleteDuplicates(head.next);
        if(head.next.val == head.val){
            head = head.next;

        }
        return head;
    }


    /**
     *
     * 221. 最大正方形 https://leetcode-cn.com/problems/maximal-square/
     * 只要周边有1个不为1，就无法形成正方形
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int m = matrix.length, n = matrix[0].length, max = 0;

        int [][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if(matrix[i][j] == '1'){
                    dp[i][j] = 1;

                    if (i > 0 && j > 0 && matrix[i-1][j] == '1' && matrix[i-1][j-1] == '1' && matrix[i][j-1] == '1') {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }

                    max = Math.max(max,dp[i][j]);
                }

            }
        }

        return max*max;

    }


    //24. 两两交换链表中的节点 https://leetcode-cn.com/problems/swap-nodes-in-pairs/
    //递归
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)   return head;
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
    //迭代
    public ListNode swapPairs2(ListNode head) {
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        newHead.next = head;
        while(cur.next != null && cur.next.next != null){
            ListNode a = cur.next;
            ListNode b = a.next;
            cur.next = b;
            a.next = b.next;
            b.next = a;
            cur = a;
        }
        return newHead.next;
    }

}
