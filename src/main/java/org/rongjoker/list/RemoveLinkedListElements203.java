package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 03/26/2021
 *
 * 203. 移除链表元素 https://leetcode-cn.com/problems/remove-linked-list-elements/
 *  链表递归入门
 *
 */
public class RemoveLinkedListElements203 {

    @Test
    public void test203(){

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        System.out.println(removeElements(head,4));

    }


    public ListNode removeElements(ListNode head, int val) {
        if(head==null)return null;
        if(head.val==val){
            head = removeElements(head.next,val);
        }else
            head.next = removeElements(head.next,val);

        return head;

    }
}
