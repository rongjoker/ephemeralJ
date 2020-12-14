package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 倒计时30天，写归并排序
 *
 * @date 11/26/2020
 */
public class Day30MergeSort {


    @Test
    public void testSlectSort() {

        int[] array = {8,9,1,7,2,3,5,4,6,0};

        array2 = new int[array.length+1];

        this.mergeSort2(array,0,array.length-1);

        System.out.println(Arrays.toString(array));


    }




    /**
     * 归并排序(天勤)
     * @param array
     */
    public void mergeSort2(int[] array,int low,int high){
        if(high > low){
            int middle = (low+high)/2;
            mergeSort2(array,low,middle);
            mergeSort2(array,middle+1,high);
            merge(array,low,middle,high);
        }
    }

    public int[] array2 = null;

    public void merge(int[] array,int low,int middle,int high){

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
        for (i = 0; i < k; i++) {// 将排好序的数填回到array数组的对应位置
            array[low + i] = array2[i];
        }
    }



    public void swap(int[] array, int low, int high) {
        if(low==high)
            return;

        int array2 = array[low];
        array[low] = array[high];
        array[high] = array2;

    }


    /**
     * 归并排序
     * @param array
     */
    public void mergeSort(int[] array){

        int n = array.length;
        int i,j,k;
        int array2;
        for (i = 0;  i< n-1; ++i) {
            k = i;
            for (j = i+1;  j< n; ++j) {
                if(array[j]< array[k])
                    k = j;
            }
            array2 = array[i];
            array[i] = array[k];
            array[k] = array2;

        }


    }





}
