package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 */
public class Day1 {


    /**
     * 起泡排序和相关的复杂度
     * 冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，交换也发生在这两个元素之间。
     * 所以，如果两个元素相等，是不会再交换的；如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，
     * 所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
     * O(n^2)的复杂度
     */
    @Test
    public void bubbleSort() {

        int[] array = {18, 2, 41, 33, 98, 6, 26, 7, 3, 88};

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                int temp = array[j];
                if (temp > array[j + 1]) {
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(array));


    }

    @Test
    public void testSum() {

        int[] array = {1, 29, 33, 27, 12, 20, 99, 57};
//        System.out.println(sumArray1(array,array.length));

//        reverseArray1(array,0,array.length-1);
//        System.out.println(Arrays.toString(array));

//        System.out.println(sumArray2(array,0,array.length-1));

//        max2Array1(array, 0, 1);
        max2Array2(array, new Code(0, 0), 0, array.length - 1);


    }

    /**
     * recursion trace
     * 数组求和-线性递归，类似微分方程
     * 先写 递归基 ：数组下标小于1，递归结束
     * 递归条件：取下标值 + 剩余数字
     * 减而知之
     */
    public int sumArray1(int[] A, int n) {

        return (n < 1) ? 0 : sumArray1(A, n - 1) + A[n - 1];

    }

    /**
     * 数组求和，分而治之
     *
     * @param A
     * @return
     */
    public int sumArray2(int[] A, int low, int high) {

        if (low == high) return A[low];

        int middle = (low + high) >> 1;

        System.out.println("middle:" + middle + ";low:" + low + ";high:" + high);

        return sumArray2(A, low, middle) + sumArray2(A, middle + 1, high);
    }


    /**
     * 数组转换，递归解决
     *
     * @param A
     * @param low
     * @param high
     */
    public void reverseArray1(int[] A, int low, int high) {

        if (low < high) {
            int temp = A[low];
            A[low] = A[high];
            A[high] = temp;
            reverseArray1(A, ++low, --high);
        }

    }

    static class Code {

        public Code(int max1, int max2) {
            this.max1 = max1;
            this.max2 = max2;
        }

        int max1, max2;

    }

    public void max2Array1(int[] A, int max1, int max2) {

        if (A[max1] < A[max2]) {
            int temp = max1;
            max1 = max2;
            max2 = temp;
        }

        for (int i = 2; i < A.length; i++) {//循环之最大值会小于A.length
            System.out.println(i + ":" + A[i] + ";" + A.length);

            if (A[i] > A[max2]) {//每次都会进行一次比较
                if (A[max1] < A[max2 = i]) {//最坏的情况下每次都要执行这里，再次比较
                    int temp = max1;
                    max1 = max2;
                    max2 = temp;
                }
            }
        }//1+(n-1)*2次最坏的复杂度


        System.out.println("max1:" + max1 + ";max2:" + max2);

    }


    /**
     * 用分治递归的方法查找最大的2个数字
     * 递归基有问题，需要优化
     *
     * @param A
     * @param code
     * @param low
     * @param high
     * @todo
     */
    public void max2Array2(int[] A, Code code, int low, int high) {
        System.out.println("low:" + low + ";high:" + high);

        if (high == low + 1) {
            if (A[low] > A[high]) {
                code.max1 = low;
                code.max2 = high;
            } else {
                code.max1 = high;
                code.max2 = low;
            }
            return;
        }

        int mid = (low + high) >> 1;
        Code leftCode = new Code(0, 0);
        Code rightCode = new Code(0, 0);
        max2Array2(A, leftCode, low, mid);
        max2Array2(A, rightCode, mid + 1, high);

        if (leftCode.max1 > rightCode.max1) {
            code.max1 = leftCode.max1;
            if (A[leftCode.max2] > A[rightCode.max1]) {
                code.max2 = leftCode.max2;
            } else {
                code.max2 = rightCode.max1;
            }
        } else {
            code.max1 = rightCode.max1;
            if (A[rightCode.max2] > A[leftCode.max1]) {
                code.max2 = rightCode.max2;
            } else {
                code.max2 = leftCode.max1;
            }
        }


        System.out.println("max1:" + code.max1 + ";max2:" + code.max2);

    }

    @Test
    public void testFib() {

//        System.out.println(fib(6));
        System.out.println(dynamicProgramming(6));

    }


    /**
     * 斐波那契数0，1，1，2，3，5
     * 复杂度已经到达了指数级别
     *
     * @param n
     * @return
     */
    public int fib(int n) {//
        if (n < 2) return n;

        return fib(n - 1) + fib(n - 2);//复杂度T(n) =T(n-1) + T(n-2)  +1 ;1对应的加法，递归方程式分析=》O(2^n)
    }


    /**
     * 动态规划和相关的复杂度
     * 原地置换
     */
    public int dynamicProgramming(int n) {

        int f = 0, g = 1;

        while (0 < n--) {
            g = g + f;
            f = g - f;
        }

        return g;


    }


    /**
     * lcs和相关的复杂度
     * 利用动态规划，用迭代取代递归，将复杂度由2^n降低到n*m
     */
    public void lcs() {


    }


    /**
     * 复杂度,有陷井的
     */
    @Test
    public void complex() {

        int n = 16;
        int k = 0;

        int i = 0, sum = 0;
        while (sum < n) {
            sum += ++i;
            k++;
        }
        ;


        System.out.println("sum:" + sum + ";;k:" + k);


    }


}
