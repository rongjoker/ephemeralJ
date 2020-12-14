package org.rongjoker.code;

import org.junit.Test;

/**
 * 倒计时68天，解决heapsort
 * 堆排序，与冒泡、插入相比，算得上是一种精巧的、反直觉的排序，理论性能可以达到nlogn,只是堆的heapify过程中，大量交换父子节点，而这些父子节点实际上在数组中并不挨着，
 * 不符合局部性原则，故无法有效利用cpu的cache，所以实际执行速度无法与快速排序相比
 * 堆排序的精髓在于heapify，建堆要基于heapify来实现，建堆完成，进行排序，从最下方和顶端：交换-取出-heapify，由于取出即是缩小一个操作范围并不需要实际操作，
 * 故实际即为交换-heapify
 * b站一位博士的解析非常精确
 * https://www.bilibili.com/video/BV1Eb41147dK?from=search&seid=14558215028817792479
 *
 */
public class Day68HeapSort {

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
    void heapify(int[] array,int n,int i,boolean big){
        int lchild = i*2+1;
        int rchild = i*2+2;
        int max = i;

        if(n> lchild && (big?(array[max]< array[lchild]):(array[max]> array[lchild])))
            max = lchild;

        if(n> rchild && (big?(array[max]< array[rchild]):(array[max]> array[rchild])))
            max = rchild;

        if(max != i){
            swap(array,i,max);//将父节点和大于父节点的值做交换
            heapify(array,n,max,big);//对子节点继续堆化，注意，这里的max是下标，也就是被选中去做交换的那个子节点的下标
        }

    }



    /**
     *
     * 建立大(或小)顶堆
     * 从倒数第二层，也就是最后一个父节点去做堆化
     * @param array
     * @param n
     * @param big
     */
    void heap_build(int[] array,int n,boolean big){
        if(n<=1)
            return;
        int last_node = n-1;
        int last_parent = (last_node -1)/2;
        for (int i = last_parent; i >=0 ; i--) {//i>=0而不是大于0
            heapify(array,n,i,big);
        }
    }

    void heap_sort(int[] array,int n,boolean big){

        for (int i = n-1; i > 0 ; i--) {
            swap(array,i,0);//不停的交换最后一个和堆顶，堆定永远是最大或者最小
            heapify(array,i,0,big);//i即相当于下次堆化的范围减少了1个（因为1个已经有序）

        }
    }


    @Test
    public void testHeapSort(){
        int[] array = new int[]{2, 3, 8, 99,43,34,56,65,78,87,98,89,100,32,23};
        boolean big = true;
        heap_build(array,array.length,big);
        heap_sort(array,array.length,big);

        for (int j : array) {
            System.out.print(j);
            System.out.print(",");
        }



    }





}
