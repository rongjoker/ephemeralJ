package org.rongjoker.list;

import org.rongjoker.datastructure.ListNode;

/**
 * @date 04/12/2021
 * 876. 链表的中间结点 https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * 快慢指针，快的走到尽头，慢的刚好过半
 *
 *
 */
public class MiddleOfTheLinkedList876 {

    public ListNode middleNode(ListNode head) {

        ListNode node1= head,node2= head;

        while(node2!=null && node2.next!=null){
            node1 = node1.next;
            node2 = node2.next.next;
        }

        return node1;

    }


}
