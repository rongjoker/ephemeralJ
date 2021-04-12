package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 *  @date 04/12/2021
 *  143. 重排链表 https://leetcode-cn.com/problems/reorder-list/
 *  需要以876和206为基础
 *  链表是运行指针最明显的题目，较难理解
 *
 */
public class ReorderList143 {


    @Test
    public void test143(){

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reorderList(head);
    }

    public void reorderList(ListNode head) {

        ListNode middle = middle(head);
        ListNode l2 = reverse(middle.next);
        middle.next = null;//砍断，避免死循环
        mergeList(head, l2);

        System.out.println(head.next.val);


    }

    //交替合并2和链表
    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }



    public ListNode reverse(ListNode head) {

        ListNode current=head,temp,previous=null;
        while(current!=null){
            temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }

        return previous;
    }


    public ListNode middle(ListNode head) {

        ListNode node1=head,node2=head;
        while(node2!=null && node2.next!=null){
            node1 = node1.next;
            node2 = node2.next.next;
        }

        return node1;
    }
}
