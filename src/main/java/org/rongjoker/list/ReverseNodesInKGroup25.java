package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 04/15/2021
 * 25. K 个一组翻转链表 https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * 较为困难的题目
 *
 *
 */
public class ReverseNodesInKGroup25 {



    @Test
    public void test25(){

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);


        System.out.println(reverseKGroup(head,3));

    }




    public ListNode reverseKGroup(ListNode head, int k) {

        if(k==1)return head;

        int right=k;

        ListNode right_node = head,left_node = head;

        while(right>1){
            right_node = right_node.next;
            if(right_node==null)break;
            --right;
        }

        //未足一轮
        if(right>1) return head;

        ListNode current = right_node.next;
        right_node.next = null;
        head = reverse(head);
//        left_node.next = current;//left_node为重新组建的右节点

        right_node=current;
        right =k;

        ListNode left_temp;

        while (right_node!=null){



            while(right>1){
                right_node = right_node.next;
                if(right_node==null)break;
                --right;
            }

            //未足一轮
            if(right>1)break;



            left_temp = current;//其实是重新组建后的最后一个
            current = right_node.next;//下一批第一个
            right_node.next = null;//砍断

            left_node.next = reverse(left_temp);//left_node为新的右节点,连上新的右边;之前的left_node下的节点已经切割断
            left_node = left_temp;

            right_node=current;
            right =k;


        }

        if(right>1){
            left_node.next = current;
        }

        return head;

    }


    //关键是previous=null，才能保证不是死循环
    public ListNode reverse(ListNode head){

        ListNode current=head,previous=null,temp;

        //三者一起向右推进
        while(current!=null){
            temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }

        return previous;

    }



}
