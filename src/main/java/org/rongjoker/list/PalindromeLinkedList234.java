package org.rongjoker.list;

import org.rongjoker.datastructure.ListNode;


/**
 * @date 05/24/2021
 * 234. 回文链表 https://leetcode-cn.com/problems/palindrome-linked-list/
 *  利用反转局部链表的解法，难度大很多
 *  类似143. 重排链表
 *  876. 链表的中间结点
 *  先找到中间，再反转后半段，然后遍历前半段和后半段
 *
 *
 */
public class PalindromeLinkedList234 {





    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode node1 = head,node2 = head;
        while(node2!=null && node2.next!=null){
            node1 = node1.next;
            node2 = node2.next.next;
        }

        node1 = reverse(node1);
        node2 = head;
        while(node1!=null){
            if(node2.val!=node1.val)return false;
            node1 = node1.next;
            node2 = node2.next;
        }


        return true;
    }

    public ListNode reverse(ListNode head){
        ListNode current = head,prev = null,temp;
        while(current!=null){
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;

        }
        return prev;

    }
}
