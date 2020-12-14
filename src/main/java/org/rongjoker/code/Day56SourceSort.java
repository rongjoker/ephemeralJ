package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 倒计时56天，看java排序源码
 *
 * @date 10/29/2020
 */
public class Day56SourceSort {

    @Test
    public void testSourceSort(){

        int[] array = new int[]{5,9,1,7,2,3,8,4,6,0};
        System.out.println(Arrays.toString(array));

//        Arrays.sort(array);

        quickSort2(array,0,array.length-1);

        System.out.println(Arrays.toString(array));


    }


    /**
     *
     * @param array
     * @param left
     * @param right
     */
    public void quickSort1(int[] array, int left, int right){
        if (left>=right)
            return;

        int i = left,j = right;
        int temp = array[left];

        while (j>i){
            while (j>i && array[j] >= temp){
                j--;
            }

            while (j>i &&  array[i]<=temp){
                i++;
            }
            System.out.println("i:"+i+";j:"+j);

            if(j>i)
                swap(array,i,j);

        }
        array[left] = array[i];
        array[i] = temp;

        quickSort1(array,left,i-1);
        quickSort1(array,i+1,right);


    }

    /**
     * 天勤原版
     * @param array
     * @param left
     * @param right
     */
    public void quickSort2(int[] array, int left, int right){
        if (left>=right)
            return;

        int i = left,j = right;
        int temp = array[left];

        while (j>i){
            while (j>i && array[j] >= temp){
                j--;
            }

            if(j>i){
                array[i] = array[j];
                ++i;
            }


            while (j>i &&  array[i]<=temp){
                i++;
            }

            if(j>i){
                array[j] = array[i];
                --j;
            }

            System.out.println("i:"+i+";j:"+j);



        }

        array[i] = temp;

        quickSort1(array,left,i-1);
        quickSort1(array,i+1,right);


    }



    public void swap(int[] array, int low, int high) {
        if(low==high)
            return;

        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;

    }


}
