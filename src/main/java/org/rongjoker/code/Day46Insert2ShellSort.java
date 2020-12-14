package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 倒计时46天，写插入排序/希尔排序
 *
 * @date 10/29/2020
 */
public class Day46Insert2ShellSort {


    @Test
    public void testInsertSort() {

        int[] array = {8,9,1,7,2,3,5,4,6,0};

        this.insertSortOptimize(array);

        System.out.println(Arrays.toString(array));


    }

    @Test
    public void testShellOneSort() {

        int[] array = {8,9,1,7,2,3,5,4,6,0};

        System.out.println(Arrays.toString(array));

        this.shellSortOne(array);

        System.out.println(Arrays.toString(array));


    }

    @Test
    public void testShellSort() {

        int[] array = {8,9,1,7,2,3,5,4,6,0};

        System.out.println(Arrays.toString(array));

        this.shellOptimizeSort(array);

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
     * 插入排序即扑克牌
     * 左侧有序，右侧无序
     *
     * @param array
     */
    public void insertSort(int[] array) {

        int i = 1;
        int j;

        int times = 0;
        for (; i < array.length; i++) {

            //内循环的部门，改为while效率更高
//            for (j=i-1;j>=0;j--){
//                if(array[j+1]<array[j]){
//                    System.out.println("i:"+i+";j:"+j);times++;
//                    swap(array,j+1,j);//这一步是关键，像扑克牌一样不停的向前替换，但是最外层的遍历的轴点是不变的，所以不是j和i的比较
//                }else break;
//            }
            j = i - 1;
            while (j >= 0 && array[j + 1] < array[j]) {
                System.out.println("i:" + i + ";j:" + j);
                times++;
                swap(array, j + 1, j);//这一步是关键，像扑克牌一样不停的向前替换，但是最外层的遍历的轴点是不变的，所以不是j和i的比较
                j--;
            }


        }
        System.out.println("times:" + times);

    }


    /**
     * 插入排序做优化【移位】
     * @param array
     */
    public void insertSortOptimize(int[] array) {

        int i = 1;
        int j;

        int times = 0;
        for (; i < array.length; i++) {

            //内循环的部门，改为while效率更高
            j = i - 1;
            int temp = array[i];
            while (j >= 0 && temp < array[j]) {//此处比较后进行移位而不是交换，一直和待排序的这个数据进行比较
                System.out.println("i:" + i + ";j:" + j);
                array[j + 1] = array[j];
                times++;
                j--;
            }

            if(i !=j + 1){
                array[j+1] = temp;
            }
        }
        System.out.println("times:" + times);

    }


    /**
     * 插入排序容易引发两个相距很远的字段进行交换，可以先将数组分割预排序为相对有序的数组再进行排序
     * 测试第一轮排序
     * @param array
     */
    public void shellSortOne(int[] array){
        int temp = 0;
        for (int i = 5; i < array.length; i++) {
            for (int j = 0; j >=0; j-=5) {
                if(array[j]>array[j+5]){
                    temp = array[j];
                    array[j] = array[j+5];
                    array[j+5] = temp;
                }
            }
        }
    }

    /**
     * 交换类型的希尔排序
     * @param array
     */
    public void shellSort(int[] array){

        int temp = 0;
        for (int gap = array.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i-gap; j >=0; j-=gap) {
                    if(array[j]>array[j+gap]){
                        temp = array[j];
                        array[j] = array[j+gap];
                        array[j+gap] = temp;
                    }
                }
            }

        }
    }


    /**
     * 位移优化的希尔排序
     * @param array
     */
    public void shellOptimizeSort(int[] array){

        for (int gap = array.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[j];
                if(array[j]<array[j-gap]){
                    System.out.println("i:"+i+";j:"+j+";array[j]:"+array[j]+";array[j-gap]:"+array[j-gap]+";gap:"+gap);
                    while (j-gap>=0 && temp<array[j-gap]){
                        //参考插入排序进行移位优化
                        array[j] = array[j-gap];
                        j-=gap;

                        System.out.println(Arrays.toString(array));
                    }
                }

                if(j<i)
                    array[j] = temp;
            }
        }
    }



}
