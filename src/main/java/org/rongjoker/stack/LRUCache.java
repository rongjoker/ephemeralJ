package org.rongjoker.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 03/29/2021
 * 146. LRU 缓存机制 https://leetcode-cn.com/problems/lru-cache/
 * lru本质上就是把用到/插入的数据放到表头，表超过容量后删除尾部数据
 * 本题是自己设计一个循环链表，也就是双向链表，每个node都要维护prev和next
 * 因为数据会不停的变更位置，所以一定要控制删除缓存的入口
 * （只有cache容量满了和需要替换的时候才要删除，故删除的操作放在最前端，不要放在下面，因为下面的方法可能会被来回调用，造成多次删除和不必要的删除）
 *
 *
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {2=1}
        lRUCache.put(1, 1); // 缓存是 {2=2}
        System.out.println(lRUCache.get(2));    // 返回 2
        lRUCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=1, 1=1}
        System.out.println(lRUCache.get(1));;    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(2));;    // 返回 1


//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        System.out.println(lRUCache.get(1));;    // 返回 1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        System.out.println(lRUCache.get(2));;    // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        System.out.println(lRUCache.get(1));;    // 返回 -1 (未找到)
//        System.out.println(lRUCache.get(3));;    // 返回 3
//        System.out.println(lRUCache.get(4));;    // 返回 4

    }





    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private final Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private final int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;

    }

    public int get(int key) {
        if(!cache.containsKey(key))return -1;
        DLinkedNode dLinkedNode = cache.get(key);
        move2Head(dLinkedNode);
        return dLinkedNode.value;

    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            delete(cache.get(key));
            --size;
        }

        DLinkedNode dLinkedNode = new DLinkedNode(key, value);
        cache.put(key,dLinkedNode);

        if(size==capacity){
            cache.remove(tail.key);
            --size;
            deleteTail();

        }

        move2Head(dLinkedNode);

        ++size;



    }

    private void delete(DLinkedNode element){
        cache.remove(element.key);
        if(tail==element) {
            this.deleteTail();
            return;
        }
        if(head==element) {
            this.deleteHead();
            return;
        }

        if(element.next!=null){
            element.next.prev = element.prev;
        }

        if(element.prev!=null){
            element.prev.next = element.next;
        }

    }

    public void deleteTail(){
        if(null!=tail){
            tail = tail.prev;
            if(null!=tail)
                tail.next=null;
        }

    }

    public void deleteHead(){
        if(null!=head){
            head = head.next;
            if(null!=head)
                head.prev=null;
        }

    }

    public void move2Head(DLinkedNode dLinkedNode){

        if(head==dLinkedNode) {
            return;
        }

        if(tail==dLinkedNode) {
            this.deleteTail();
        }

        if(dLinkedNode.next!=null){
            dLinkedNode.next.prev = dLinkedNode.prev;
        }

        if(dLinkedNode.prev!=null){
            dLinkedNode.prev.next = dLinkedNode.next;
        }


        if(head!=null){
            dLinkedNode.next = head;
            head.prev = dLinkedNode;
        }

        head = dLinkedNode;
        head.prev = null;

        if(tail==null){
            tail = dLinkedNode;
            tail.prev=null;
            tail.next=null;
        }



    }




}
