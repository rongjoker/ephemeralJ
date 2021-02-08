package org.rongjoker.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 02/03/2021
 *
 *  剑指 Offer 62. 圆圈中最后剩下的数字 https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *  实际就是约瑟夫环，可以用循环链表，也可以用数学的方法
 *  @date 02/03/2021 用循环链表会超时
 *
 */
public class LastRemainingNumber {


    @Test
    public void test62(){



        System.out.println(lastRemaining(10,17));


    }


    /**
     * @date 02/04/2021  用线性表来做，用取余的方式模拟循环链表
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index=0;
        while (n>1){
            index = (index+m-1)%n;
            list.remove(index);//需要根据下标来删除，故用线性表
            //按照下标应该向前移动1位，继续循环，由于删除了一个坐标，相当于实现了移动1位，故不需要再移动
            --n;
        }

        return list.get(0);
    }


    /**
     * 模拟链表来做，复杂度非常高，超时
     * @date 02/03/2021
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining_link(int n, int m) {

        if(m==1)return n-1;

        //初始化循环链表
        ListNode head=null,tail=null,node;
        for (int i = 0; i < n; i++) {
            node = new ListNode(i);
            if(i==0){
                head = node;
                tail=head;
            }else {
                tail.next = node;
                tail=node;
            }
        }
        tail.next=head;


        //打印
//        node = head;
//        while (node.next!=head){
//            System.out.println(node.val);
//            node = node.next;
//        }

        node = head;
        int count=0;
        while (node.next!=node){
            if(count == 0){
                for (int i = 0; i < m-2; i++) {
                    node = node.next;
                }
            }else {
                for (int i = 0; i < m-1; i++) {
                    node = node.next;
                }
            }
            count++;


            node.next = node.next.next;//断开命中的那个节点

        }

        return node.val;

    }



}
