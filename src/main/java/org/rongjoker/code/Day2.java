package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 向量-即线性表
 *
 * @date 2020.sep.8
 */
public class Day2 {


    @Test
    public void testBinSearch() {

//        int[] array = {18, 2, 41, 33, 98, 6, 26, 7, 3, 88};
        int[] array = {1, 2, 3, 5, 8, 16, 26, 37, 43, 88};


//        System.out.println(binSearchA(array,99,0,array.length-1));
        System.out.println(fibSearch(array, 3, 0, array.length - 1));

    }

    /**
     * 就像微分方程，不是给你一个答案，而是给你一个方法
     * 二分查找版本A，每次比较，数据规模会减半，故为O(logn)
     * 平均复杂度O(1.5logn)
     * 可以看作向左进行1次比较（《），向右进行2次比较(》=)
     * 注意，这种二分查找只能命中，不能返回不明中的情况下合适的位置（理论上不命中应该返回适合插入的位置）
     *
     * @param A
     * @param low
     * @param high
     */
    public int binSearchA(int[] A, int target, int low, int high) {

        System.out.println("low:" + low + ";high:" + high);

        if (low > high)
            return -1;

        int middle = (low + high) >> 1;

        if (A[middle] < target)//在右侧
            return binSearchA(A, target, middle + 1, high);
        else if (target < A[middle])//在左侧侧
            return binSearchA(A, target, low, middle - 1);
        else return middle;
    }

    /**
     * 斐波那契数查找平均复杂度O(1.44logn)
     * 对二分查找进行优化，由于左转只需要比较一次，所以左侧增加，右侧相较更少
     *
     * @param A
     * @param target
     * @param low
     * @param high
     * @return
     */
    public int fibSearch(int[] A, int target, int low, int high) {

        if (low > high)
            return -1;

        int middle = dynamicProgramming(low, high);

        System.out.println("low:" + low + ";middle:" + middle + ";high:" + high);

        if (A[middle] < target)//在右侧
            return fibSearch(A, target, middle + 1, high);
        else if (target < A[middle])//在左侧侧
            return fibSearch(A, target, low, middle - 1);
        else return middle;
    }

    public int dynamicProgramming(int low, int high) {

        int f = 0, g = 1;

        while (g < high - low) {
            g = g + f;
            f = g - f;
        }

        return low + f;

    }

    /**
     * 起泡排序和相关的复杂度
     * 冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，交换也发生在这两个元素之间。
     * 所以，如果两个元素相等，是不会再交换的；如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，
     * 所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
     * O(n^2)的复杂度
     */
    @Test
    public void bubbleSort() {
        int sequence = 0;
        int[] array = {2, 18, 7, 6, 3, 33, 41, 26, 27, 88, 98};

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {//关键是array.length - i，每一趟都会减少一个index，即每一趟都会确定一个最大值
                sequence++;
                if (array[j] > array[j + 1])
                    swap(array, i, i + 1);
            }
        }

        System.out.println(Arrays.toString(array));
        System.out.println("趟数:" + sequence);

    }


    /**
     * 优化版本的起泡排序,用sorted 标志来确定是否可以提前退出
     * 复杂度O(n^1.5)
     */
    @Test
    public void bubbleSort2() {

        int sequence = 0;
        int[] array = {2, 18, 7, 6, 3, 33, 41, 26, 27, 88, 98};

        boolean sorted = false;
        int high = array.length - 1;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < high; i++) {
                sequence++;

                if (array[i] > array[i + 1]) {
                    sorted = false;
                    swap(array, i, i + 1);
                }
            }
            high--;
        }
        System.out.println(Arrays.toString(array));
        System.out.println("趟数:" + sequence);
    }

    /**
     * 优化版本的起泡排序,用sorted 标志来确定是否可以提前退出,并且用index标志减少扫描的长度
     * 针对局部比较有次序的情况比较有利,如果顺序很乱，依然没有用处
     */
    @Test
    public void bubbleSort3() {

        int sequence = 0;
        int[] array = {3,2, 18, 7, 6, 3, 33, 41, 26, 27, 88, 98};

        boolean sorted = false;
        int high = array.length - 1, index = 1;


        while (!sorted) {
            sorted = true;

            for (int i = 0; i < high; i++) {
                sequence++;
                System.out.println("i:"+i);

                if (array[i] > array[i + 1]) {
                    System.out.println("i:" + i+";"+array[i ]+";"+array[i + 1]);
                    sorted = false;
                    index = i;
                    swap(array, i, i + 1);
                }
            }
            System.out.println(Arrays.toString(array));
            System.out.println("index:" + index+";high:"+high);
            high = index;
            index = 0;

        }
//        System.out.println(Arrays.toString(array));
        System.out.println("趟数:" + sequence);
    }


    public void swap(int[] array, int low, int high) {

        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;

    }


    /**
     * 归并排序,分治策略,向下拆分各自排序，然后向上合并排序,复杂度O(nlogn)
     * 尝试用左闭右开来优化程序
     */
    public int[] mergeSort(int[] array,int low, int high){

//        System.out.println("low:"+low+";high:"+high);

        if(low==high)
            return new int[]{array[low]};

//        int middle = low+ (high - low) >> 1;//关键步骤
        int middle = (high + low) >> 1;//关键步骤
        int[] left = mergeSort(array,low,middle);
        int[] right =  mergeSort(array,middle+1,high);

        int[] merge =new int[left.length + right.length];

        int left_index=0,right_index=0,index=0;
        while (left_index<left.length || right_index<right.length){

            if(  left_index<left.length && ( right_index>= right.length || left[left_index]<=right[right_index] ) ){
                merge[index++] = left[left_index++];
            }

            if(right_index< right.length && (  left_index>=left.length || right[right_index] <left[left_index] )){
                merge[index++] = right[right_index++];
            }
        }

        System.out.println(Arrays.toString(merge));

        return merge;

    }

    @Test
    public void testMerge(){
        int[] array = {3,2, 18, 7, 6, 3, 33, 41, 26, 27, 88, 98};
//        int[] array = {3,2};
        mergeSort(array,0,array.length-1);
    }


}


