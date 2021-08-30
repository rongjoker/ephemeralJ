package org.rongjoker.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 08/28/2021
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class SortList148 {

    public ListNode sortList(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while(cur!=null){
            nodes.add(cur);
            cur = cur.next;
        }
        int len = nodes.size();
        if(len==0)return null;
        nodes.sort((Comparator.comparingInt(o -> o.val)));

        ListNode h = nodes.get(0);
        ListNode temp = h;
        for (int i = 1; i < len; i++) {
            temp.next = nodes.get(i);
            temp = temp.next;
        }
        temp.next = null;

        return h;


    }

}
