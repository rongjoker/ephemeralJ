package org.rongjoker.list;

import org.junit.Test;

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


    public int lastRemaining(int n, int m) {

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
