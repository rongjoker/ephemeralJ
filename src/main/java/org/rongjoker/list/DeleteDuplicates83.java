package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 03/26/2021
 *  83. 删除排序链表中的重复元素 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 *  3月26每日一题
 *
 */
public class DeleteDuplicates83 {



    @Test
    public void test83(){

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        System.out.println(deleteDuplicates(head));






    }

    public ListNode deleteDuplicates(ListNode head) {


        return null;

    }


}
