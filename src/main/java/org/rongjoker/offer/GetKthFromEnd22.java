package org.rongjoker.offer;


import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 01/26/2021
 * <p>
 * 剑指 Offer 22. 链表中倒数第k个节点 https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * <p>
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 典型的双指针题目，或者可以理解为滑动窗口,将窗口固定为倒数的数字，然后一次向前推进，推进到右侧为null结束
 */
public class GetKthFromEnd22 {


    @Test
    public void test22() {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = getKthFromEnd(head, 4);

        System.out.println(listNode.val);



    }


    public ListNode getKthFromEnd(ListNode head, int k) {

        int left=0,right=0;

        ListNode nl = head,nr=head;
        while(right -left<k){
            nr = nr.next;
            ++right;
        }

        while(nr!=null){
            nr = nr.next;
            nl = nl.next;
        }

        return nl;

    }


}
