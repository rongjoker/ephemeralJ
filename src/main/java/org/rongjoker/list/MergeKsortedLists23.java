package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 04/13/2021
 * 23. 合并K个升序链表 https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 */
public class MergeKsortedLists23 {

    @Test
    public void test23() {

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

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);
        head3.next.next = new ListNode(7);

        ListNode[] listNodes = new ListNode[]{head,head2,head3};

        System.out.println(mergeKLists(listNodes));

    }


    /**
     * 笨方法
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length==0)return null;

        ListNode node1 = null;
        for (int i = 0; i < lists.length; i++) {
            node1 = merge(node1,lists[i]);
        }

        return node1;

    }

    public ListNode merge(ListNode node1,ListNode node2){

        if(node1==null ||node2==null)return node1!=null?node1:node2;

        ListNode node = new ListNode();
        ListNode temp=node;

        while (node1!=null && node2!=null){
            if(node1.val<=node2.val){
                temp.next = node1;
                node1 = node1.next;
            }else {
                temp.next = node2;
                node2 = node2.next;
            }
            temp = temp.next;

        }

        temp.next = node1!=null?node1:node2;

        return node.next;
    }

}
