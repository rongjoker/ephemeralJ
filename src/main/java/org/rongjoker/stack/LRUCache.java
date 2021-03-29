package org.rongjoker.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 03/29/2021
 * 146. LRU 缓存机制 https://leetcode-cn.com/problems/lru-cache/
 * lru本质上就是把用到/插入的数据放到表头，表超过容量后删除尾部数据
 *
 *
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(2));    // 返回 1
        lRUCache.put(1, 1); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(2));;    // 返回 -1 (未找到)
    }





    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
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
        if(size==0){
            tail = dLinkedNode;

        }else if(size==capacity){
            --size;
            deleteTail();

        }

        move2Head(dLinkedNode);

        ++size;



    }

    private void delete(DLinkedNode element){
        cache.remove(element.key);
        if(tail==element) {
            tail = element.prev;
            return;
        }
        if(head==element) {
            head = element.next;
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
            cache.remove(tail.key);
            tail = tail.prev;
            tail.next=null;
        }

    }

    public void move2Head(DLinkedNode dLinkedNode){

        if(dLinkedNode==tail){
            tail = dLinkedNode.prev;
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

    }




}
