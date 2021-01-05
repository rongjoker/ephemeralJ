package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 倒计时32天，写选择排序
 *
 * @date 10/29/2020
 */
public class Day32selectSort {


    @Test
    public void testSlectSort() {

        int[] array = {8,9,1,7,2,3,5,4,6,0};

        this.selectSort(array);

        System.out.println(Arrays.toString(array));


    }



    public void swap(int[] array, int low, int high) {
        if(low==high)
            return;

        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;

    }

    /**
     * 选择排序
     * @param array
     */
    public void selectSort(int[] array){

        int n = array.length;
        int i,j,k;
        int temp;
        for (i = 0;  i< n-1; ++i) {
            k = i;
            for (j = i+1;  j< n; ++j) {
                if(array[j]< array[k])
                    k = j;
            }
            System.out.println("i:"+i+";k:"+k);
            temp = array[i];
            array[i] = array[k];
            array[k] = temp;

        }

    }





}
