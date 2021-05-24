package org.rongjoker.list;


import org.rongjoker.datastructure.ListNode;

/**
 * @date 05/24/2021
 * 160. 相交链表 II https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * a + all + b = b+ all + a
 * 很巧妙的题目
 * 巧妙的解法
 */
public class IntersectionOfTwoLinkedLists160 {


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp1 = headA, temp2 = headB;
        while (temp1 != temp2) {
            if (temp1 != null) temp1 = temp1.next;//注意这里，要判断temp1,而不是temp1.next，否则可能会死循环
            else temp1 = headB;
            if (temp2 != null) temp2 = temp2.next;
            else temp2 = headA;
        }
        return temp1;


    }

}
