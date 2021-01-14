package org.rongjoker.sw;

import org.junit.Test;

/**
 * @date 01/14/2021
 * 141. 环形链表 https://leetcode-cn.com/problems/linked-list-cycle/
 * 快慢指针入门
 * 这个问题你可以用数学归纳法来思考。首先，由于链表是个环，所以相遇的过程可以看作是快指针从后边追赶慢指针的过程。那么做如下思考：1：快指针与慢指针之间差一步。此时继续往后走，慢指针前进一步，快指针前进两步，两者相遇。2：快指针与慢指针之间差两步。此时唏嘘往后走，慢指针前进一步，快指针前进两步，两者之间相差一步，转化为第一种情况。3：快指针与慢指针之间差N步。此时继续往后走，慢指针前进一步，快指针前进两步，两者之间相差(N+1-2)-> N-1步。因此，此题得证。所以快指针必然与慢指针相遇。又因为快指针速度是慢指针的两倍，所以相遇时必然只绕了一圈。
 *
 * 作者：冯昱尧
 * 链接：https://www.zhihu.com/question/23208893/answer/117115415
 * 来源：知乎
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LinkedListCycle {


    @Test
    public void test141(){

        ListNode head = new ListNode(1);
        ListNode next2 = new ListNode(2);
        head.next = next2;next2.next=head;
//        next2.next = new ListNode(0);
//        next2.next.next = new ListNode(-4);
//        next2.next.next.next = next2;

        System.out.println(hasCycle(head));


    }

    public boolean hasCycle(ListNode head) {

        ListNode slow=head,fast=head;
        while (slow!=null&&slow.next!=null){//防止链表为空
            slow=slow.next;
            if(fast!=null&&fast.next!=null)
                fast=fast.next.next;
            else return false;

            if(slow==fast){
//                System.out.println(slow.val+";"+fast.val);
                return true;
            }

        }
        return false;
    }









}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}