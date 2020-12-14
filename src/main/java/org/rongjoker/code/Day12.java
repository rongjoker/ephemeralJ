package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 排序
 * 高级排序算法
 */
public class Day12 {


    /**
     * 快速排序
     * 归并排序麻烦在合并；快速排序麻烦在划分；归并排序划分很均衡，快排取决于选取的轴点，最好的情况，o(nlogn)即排序的下界，最坏的情况是o(n^2),平均情况下o(nlogn)，常系数大约1.39
     * 核心：快速确定轴点pivot和partition算法
     * 本质是将每个元素都转为轴点
     */
    @Test
    public void testQuickSort() {

        int sequence = 0;
        int[] array = {2, 18, 7, 6, 3, 33, 41, 26, 27, 88, 98, 29};


        quickSort2(array, 0, array.length - 1);


        System.out.println(Arrays.toString(array));
        System.out.println("趟数:" + sequence);


    }

    /**
     * 轴点左小右大，相当于轴点本身有序，后续递归本质是将所有的元素变成轴点
     *
     * @param array
     * @param lo
     * @param hi
     */
    public void quickSort(int[] array, int lo, int hi) {

        if (lo >= hi) return;

        int pivot = array[lo];

        int i = lo, j = hi;

        while (i < j) {//while是关键，只要i<j，循环就会走下去，知道i和j相遇，相遇点即数据中点，左边小右边大，相当于停留在正确的位置，随后左右各自继续重复操作，但是待排序总数递减了一

            while (array[j] >= pivot && i < j)
                j--;

            while (array[i] <= pivot && i < j) {
                i++;
            }



            swap(array, i, j);

        }

        //结束后，把i点放入轴点数字，i点原来的数字比轴点小，放入low,

        array[lo] = array[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        array[i] = pivot;
        System.out.println("i:"+i+";j:"+j);

        //此时i=j=轴点，以此为分割左右
        quickSort(array, lo, j - 1);  //对左边快排
        quickSort(array, j + 1, hi); //对右边快排
    }


    /**
     * 优化了一点复杂度
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

    public void swap(int[] array, int low, int high) {

        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;

    }




    /**
     * 获取众数（占据数量超过一半的数字）
     * 差额计算器
     */
    @Test
    public void testMajorCandidate() {

        int[] array = {1, 2, 3, 4, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6, 7, 2, 2, 2};

        array = new int[]{1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 5};

        int major = 0;

        for (int c = 0, i = 0; i < array.length; i++) {
            if (c == 0) {
                major = array[i];
                c = 1;

            } else {
                if (array[i] == major) {
                    c++;
                } else {
                    c--;
                }
            }

        }

        System.out.println(major);

    }

    /**
     * 希尔排序,即切分后做插入排序
     */
    @Test
    public void testShellSort() {

        int[] array = {2, 18, 7, 6, 3, 33, 41, 26, 27, 88, 98};
        int length = array.length;

        int d = length / 2;   //设置希尔排序的增量
        int i;
        int j;
        int temp;
        while (d >= 1) {
            System.out.println("d:"+d);

            for (i = d; i < length; i++) {
                temp = array[i];
                j = i - d;
//                while(j>=0 && array[j]<temp)//根据大于小于，实现升序和降序
                while (j >= 0 && array[j] > temp) {
                    array[j + d] = array[j];
                    j = j - d;
                }
                array[j + d] = temp;
            }
            System.out.println(Arrays.toString(array));
            d = d / 2;//缩小增量
        }

        System.out.println(Arrays.toString(array));

    }







}
