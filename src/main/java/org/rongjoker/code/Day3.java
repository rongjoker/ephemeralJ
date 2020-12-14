package org.rongjoker.code;

import org.junit.Test;

import java.util.Arrays;

/**
 * 链表/列表-List
 * 删除，最方便，复杂度为常数时间
 * 唯一化
 * 链表的缺点是只能循秩查找，这是弱于二叉树的一点
 */
public class Day3 {


    /**
     * 选择排序，
     * 起泡排序某种意义上也是选择排序，但是效率太低，主要是因为移动元素是小步快跑，挨个元素的移动
     * 找到最大的，放到最右边
     * 复杂度也非常高：O(n^2)；但是可以减少交换位置的频率，更多的是比较
     */
    @Test
    public void selectSort(){

        int sequence = 0;
        int[] array = {2, 18, 7, 6, 3, 33, 41, 26, 27, 88, 98};

        for (int i = 0; i < array.length; i++) {
            int max_index = 0;
            for (int j = 0; j < array.length - i; j++) {//关键是array.length - i，每一趟都会减少一个index，即每一趟都会确定一个最大值
                sequence++;
                if (array[j] > array[max_index])//找到最大的
                    max_index = j;
            }

            swap(array,max_index,array.length - i-1);


        }

        System.out.println(Arrays.toString(array));
        System.out.println("趟数:" + sequence);



    }

    public void swap(int[] array, int low, int high) {
        System.out.println("low:"+low+"-"+array[low]+";high:"+high+"-"+array[high]);

        if(high==low)
            return;

        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;

    }

    /**
     * 扑克牌
     * 插入排序
     * 选择排序每次都取出最大值，相当于对无序部分要限制范围
     * 插入排序，对无序部分没有要求
     * 插入排序，适合链表，属于in place就地算法
     * @todo 用链表实现
     * 数学期望：O(n^2)
     * 逆序对(inversion)
     * 算法实现的原理是，向前比较（前面是已经有序的），挨个比较，找到比自己小的即合适的位置，每一次向前比较，如果比较成功，都会交换一次位置，
     * 所以如果逆序对比较多，则交换的次数非常多
     */
    @Test
    public void insertSort(){

        int sequence = 0;
        int[] array = {2, 1, 7, 6, 3, 33, 41, 26, 27, 88, 98};

        int j = 0;
        int times =0;
        for (int i = 1; i < array.length; i++) {//第0个肯定是有序的，所以从第1个【向前】去比较
            int temp = array[i];//暂时取出
            j = i-1;
            while (j>=0 && array[j] > temp){
                times++;
                System.out.println("i:"+i+";j:"+j);
//                swap(array,j,j+1);//浪费空间，去掉这一步换成更简单的
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;//j向前推进后，遇到合适的位置，结束，j永远是空位置的前面那个，所以空位置是j+1，放入拿出来的temp

        }
        System.out.println("times:"+times);

        System.out.println(Arrays.toString(array));
        System.out.println("趟数:" + sequence);

    }




}
