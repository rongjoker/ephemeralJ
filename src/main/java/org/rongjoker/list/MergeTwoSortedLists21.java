package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 04/12/2021
 * 21. 合并两个有序链表 https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 *
 */
public class MergeTwoSortedLists21 {

    @Test
    public void test21() {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);


        ListNode head2 = new ListNode(11);
        head2.next = new ListNode(12);
        head2.next.next = new ListNode(13);
        head2.next.next.next = new ListNode(14);
        head2.next.next.next.next = new ListNode(15);

        mergeTwoLists(head, head2);

        System.out.println(head);

    }


    //合并链表，局部变量偏移，不影响本身，两条链表一路向前迭代
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        merge(l1,l2,node);
        return node.next;
    }


    public void merge(ListNode l1, ListNode l2,ListNode node) {
        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                node.next = l1;
                l1=l1.next;
            }else{
                node.next = l2;
                l2=l2.next;
            }

            node = node.next;

        }

        if(l1!=null)node.next = l1;
        if(l2!=null)node.next = l2;
    }

}
