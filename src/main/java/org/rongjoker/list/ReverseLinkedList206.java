package org.rongjoker.list;


import org.junit.Test;

/**
 * @date 01/26/2021
 *
 * 206. 反转链表 https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 多种方法
 *
 */
public class ReverseLinkedList206 {



    @Test
    public void test206(){

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reverseList2(head);

        while (head!=null){
            System.out.println(head.val);head=head.next;
        }




    }



    /**
     * 迭代，2个节点依次向前移动，向右的箭头依次转变为向左,有点类似挨着的双指针，依次向前迭代
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode previous = null,current=head,temp;

        while (null!=current){
            temp= current.next;
            current.next = previous;
            previous = current;
            current = temp;//最后一个为null。故返回previous为head,也即null循环前的current
        }

        return previous;

    }


    /**
     * 递归，找到递归基
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {

        if(head==null || head.next==null)
            return head;

        ListNode head2 = reverseList2(head.next);

        head.next.next = head;//父子置换指针step1
        head.next = null;//父子置换指针step2,每一次都切断重连

        return head2;

    }


}
