package org.rongjoker.list;

import org.rongjoker.datastructure.ListNode;

/**
 *
 * @date 07/14/2021
 * 面试题 02.01. 移除重复节点 https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
 *
 *
 */
public class RemoveDuplicateNodes {


    public ListNode removeDuplicateNodes(ListNode head) {
        int mask =0;
        ListNode pre = head;
        mask |= (1>>pre.val);
        while(pre.next!=null){
            ListNode temp = pre.next;
            if((mask >> temp.val)==1)pre.next = temp.next;
            else {
                mask |= (1>>pre.val);
                pre = temp;
            }
        }
        return head;


    }
}
