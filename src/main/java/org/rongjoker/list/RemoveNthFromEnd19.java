package org.rongjoker.list;

import org.rongjoker.datastructure.ListNode;

/**
 * @date 03/27/2021
 * 19. 删除链表的倒数第 N 个结点 https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * 利用双指针进行链表下标操作
 *
 */
public class RemoveNthFromEnd19 {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null)return null;
        int right=0;
        ListNode node_left=head,node_right=head;

        while(right<n){
            node_right = node_right.next;
            ++right;
        }
        //左边没有移动，也就是删除第一个
        if(null==node_right)
            return node_left.next;

        //这一步是关键，根据node_right.next来向前移动，而不是node_right
        while(node_right.next!=null){
            node_left = node_left.next;
            node_right = node_right.next;
        }

        node_left.next = node_left.next.next;

        return head;

    }
}
