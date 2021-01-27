package org.rongjoker.list;


import org.junit.Test;

/**
 * @date 01/26/2021
 * <p>
 * 92. 反转链表 II https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * <p>
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 标记前后的四个位置，置换完中间，再置换这四个即可
 */
public class ReverseLinkedList92 {


    @Test
    public void test92() {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = reverseBetween(head, 3, 4);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }


    }


    /**
     * 迭代，2个节点依次向前移动，向右的箭头依次转变为向左
     *
     * @param head
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode previous = null, current = head, temp, left1 = null, left2 = null, right2 = null;
        int i = 1;

        while (null != current && i <= n + 1) {
            if (i < m) {

                if (i == m - 1)
                    left1 = current;

                current = current.next;

            } else if (i >= m && i <= n) {

                if (i == m)
                    left2 = current;

                temp = current.next;
                current.next = previous;
                previous = current;
                current = temp;//最后一个为null。故返回previous为head,也即null循环前的current

            } else {
                right2 = current;
            }

            i++;
        }

        if (null != left1) left1.next = previous;
        if (null != right2) left2.next = right2;

        if (m > 1) return head;
        else return previous;

    }


}
