package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 倒计时30天，写归并排序
 *
 * @date 11/26/2020
 */
public class Day30a4MergeSort {


    @Test
    public void testMergeSort() {

        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        array2 = new int[array.length + 1];
        array3 = new int[array.length + 1];

        this.mergeSort2(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));


    }


    /**
     * 归并排序(天勤)
     *
     * @param array
     */
    public void mergeSort2(int[] array, int low, int high) {
        if (high > low) {
            int middle = (low + high) / 2;
            mergeSort2(array, low, middle);
            mergeSort2(array, middle + 1, high);
            merge3(array, low, middle, high);
        }
    }

    public int[] array2 = null;

    /**
     * 归并排序
     *
     * @param array
     */
    public void mergeSort(int[] array) {

        int n = array.length;
        int i, j, k;
        int array2;
        for (i = 0; i < n - 1; ++i) {
            k = i;
            for (j = i + 1; j < n; ++j) {
                if (array[j] < array[k])
                    k = j;
            }
            array2 = array[i];
            array[i] = array[k];
            array[k] = array2;

        }


    }

    public void merge(int[] array, int low, int middle, int high) {

        int i = low, j = middle + 1; // i为第一组的起点, j为第二组的起点
        int m = middle, n = high; // m为第一组的终点, n为第二组的终点
        int k = 0; // k用于指向array2数组当前放到哪个位置
        while (i <= m && j <= n) { // 将两个有序序列循环比较, 填入数组array2
            if (array[i] <= array[j])
                array2[k++] = array[i++];
            else
                array2[k++] = array[j++];
        }
        while (i <= m) { // 如果比较完毕, 第一组还有数剩下, 则全部填入array2
            array2[k++] = array[i++];
        }
        while (j <= n) {// 如果比较完毕, 第二组还有数剩下, 则全部填入array2
            array2[k++] = array[j++];
        }

        System.out.println(Arrays.toString(array2));

        for (i = 0; i < k; i++) {// 将排好序的数填回到array数组的对应位置
            array[low + i] = array2[i];
        }
    }

    public int[] array3 = null;

    public void merge3(int[] array, int low, int middle, int high) {
        //每次填充array3都是从0开始的，反复利用这个临时表，所以灌入的时候也是从0开始到high-low结束（最后一段）

        int i = 0;

        int start1 = low,start2 = middle+1;

        while (start1<=middle && start2<=high){
            if (array[start1] <= array[start2]){
                array3[i++] = array[start1++];
            }else {
                array3[i++] = array[start2++];
            }

        }


        while (start1<=middle){
            array3[i++] = array[start1++];
        }

        while (start2<=high){
            array3[i++] = array[start2++];
        }

        System.out.println(Arrays.toString(array3));

        for (int prefix = 0; prefix < i; prefix++) {// 将排好序的数填回到array数组的对应位置
            array[low + prefix] = array3[prefix];
        }
        
    }


    //88. 合并两个有序数组
    @Test
    public void test88() {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int p = m + n -1;
        m--;n--;

        while (m >=0 && n>=0){
            if(nums1[m] < nums2[n]){
                nums1[p--] = nums2[n--];
            }else {
                nums1[p--] = nums1[m--];
            }
        }

        while (n >= 0) {//n剩余的执行剩余操作，灌入
            nums1[p--] = nums2[n--];
        }
        //m剩余的有序不需要重复剩余操作

    }



}
