package org.rongjoker.list;

import org.junit.Test;
import org.rongjoker.datastructure.ListNode;

/**
 * @date 03/26/2021
 * 2. 两数相加 https://leetcode-cn.com/problems/add-two-numbers/
 *
 * 本质是递归,核心是思考递归的转移形式
 */
public class AddTwoNumbers2 {


    @Test
    public void test2(){


    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return add(l1,l2,null);

    }

    public ListNode add(ListNode l1, ListNode l2,ListNode root) {
        if(l1==null && l2==null && root==null){
            return null;
        }

        int temp = (root!=null?root.val:0) + (l1!=null?l1.val:0)+ (l2!=null?l2.val:0);

        if(l1!=null || l2!=null){
            if(temp <10){
                root =  new ListNode(temp);
                root.next=add(null!=l1?l1.next:null,null!=l2?l2.next:null,null);
            }else{
                root =  new ListNode(temp/10);//余数放在这里
                root.next=add(null!=l1?l1.next:null,null!=l2?l2.next:null,new ListNode(temp/10));
            }

        }else{
            root =  new ListNode(temp);
        }
        return root;





    }


}
