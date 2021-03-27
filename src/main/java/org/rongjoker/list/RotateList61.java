package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 03/27/2021
 * 61. 旋转链表 https://leetcode-cn.com/problems/rotate-list/
 * 双指针操作下标，注意临界条件
 *
 */
public class RotateList61 {


    @Test
    public void test61(){

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
        System.out.println(rotateRight(head,2));
    }


    public ListNode rotateRight(ListNode head, int k) {
        if(null==head)return null;
        if(k<=0) return head;

        int total=1;
        ListNode temp =head;
        while(temp.next!=null){
            temp = temp.next;
            ++total;
        }
        if(total==1)return head;

        int avail =k%total;//真正要移动的位置
        if(avail==0)return head;//相当于没有旋转

        avail=total-avail;
        int start = 0;
        ListNode current =head;
        while(start<avail-1){
            ++start;
            current = current.next;
        }
        ListNode next = current.next;
        current.next= null;
        temp.next = head;
        return next;

    }
}
