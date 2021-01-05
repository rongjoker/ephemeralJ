package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * @date 2020-12-23
 *  * 堆排序的精髓在于heapify，建堆要基于heapify来实现，建堆完成，进行排序，从最下方和顶端：交换-取出-heapify，由于取出即是缩小一个操作范围并不需要实际操作，
 *  * 故实际即为交换-heapify
 *  * b站一位博士的解析非常精确
 *  * https://www.bilibili.com/video/BV1Eb41147dK?from=search&seid=14558215028817792479
 *  *
 *  */

public class Day3HeapSort2ShellSort {

    public void swap(int[] array, int low, int high) {

        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;

    }


    /**&&
     *
     * @param array
     * @param n 数组长度
     * @param i 指定要堆化的节点
     */
    void heapify(int[] array,int n,int i){
        int lchild = i*2+1;//左孩子
        int rchild = i*2+2;//右孩子
        int max = i;

        if(n> lchild && (array[max]< array[lchild]))
            max = lchild;

        if(n> rchild && (array[max]< array[rchild]))
            max = rchild;

        if(max != i){
            swap(array,i,max);//将父节点和大于父节点的值做交换
            heapify(array,n,max);//对子节点继续堆化，注意，这里的max是下标，也就是被选中去做交换的那个子节点的下标；
            // 是从下向上递归循环的，所以如果孩子节点和父亲节点交换了，新放入孩子节点的数字可能不满足之前的大顶堆的要求，所以要向下重新递归堆化一次
        }

    }



    /**
     *
     * 建立大(或小)顶堆
     * 从倒数第二层，也就是最后一个父节点去做堆化
     * @param array
     * @param n
     */
    void heap_build(int[] array,int n){
        if(n<=1)
            return;
        int last_node = n-1;//最后一个的节点
        int last_parent = (last_node -1)/2;//最后一个节点的父亲，完全二叉树，所以正好是1/2
        for (int i = last_parent; i >=0 ; i--) {//i>=0而不是大于0
            heapify(array,n,i);
        }
    }

    void heap_sort(int[] array,int n){

        for (int i = n-1; i > 0 ; i--) {
            swap(array,i,0);//不停的交换最后一个和堆顶，堆定永远是最大或者最小
            heapify(array,i,0);//i即相当于下次堆化的范围减少了1个（因为1个已经有序）

        }
    }


    @Test
    public void testHeapSort(){
        int[] array = new int[]{2, 3, 8, 99,43,34,56,65,78,87,98,89,100,32,23};
        heap_build(array,array.length);
        heap_sort(array,array.length);

        for (int j : array) {
            System.out.print(j);
            System.out.print(",");
        }

    }

    @Test
    public void testShellSort(){
        int[] array = new int[]{2, 3, 8, 99,43,34,56,65,78,87,98,89,100,32,23};
        shellOptimizeSort(array);
        System.out.println(Arrays.toString(array));


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
