package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 03/25/2021
 *  82. 删除排序链表中的重复元素 II https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 *  3月25每日一题
 *
 */
public class DeleteDuplicates82 {



    @Test
    public void test82(){

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
        if(null==head) return null;
        if(null==head.next) return head;

        ListNode next = head.next;//取一个中间变量来移动指针

        if(next.val == head.val){//head重复
            while(next!=null && head.val==next.val)
                next = next.next;

            //循环的情况下就是head = next;也就是连head本身也丢弃，递归情况下加上deleteDuplicates，也就是把next后面的也处理掉
            head = deleteDuplicates(next);

        }else{//head不重复，处理head后面的重复的
            head.next = deleteDuplicates(next);
        }

        return head;
    }


}
