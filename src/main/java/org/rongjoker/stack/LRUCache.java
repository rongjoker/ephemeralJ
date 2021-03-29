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

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

    }

    public int get(int key) {
        if(!cache.containsKey(key))return -1;
        DLinkedNode dLinkedNode = cache.get(key);



        return key;

    }

    public void put(int key, int value) {

    }

    public void deleteHead(){}
    public void deleteTail(){}

    public void move2Head(){}




}
