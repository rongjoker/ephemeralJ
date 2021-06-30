package org.rongjoker.array;

import java.util.*;

/**
 * @date 06/30/2021
 * 380. O(1) 时间插入、删除和获取随机元素 https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 *
 *
 */
public class RandomizedSet {

    Map<Integer,Integer> index;

    List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        index = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(index.containsKey(val))return false;
        list.add(val);
        index.put(val,list.size()-1);
        return true;
    }

    /**
     *
     *
     * 删除是关键
     */
    public boolean remove(int val) {
        if(!index.containsKey(val))return false;
        int i = index.get(val),last = list.get(list.size()-1);
        list.set(i,last);
        list.remove(list.size()-1);
        index.put(last,i);

        index.remove(val);
        return true;

    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));

    }

    public static void main(String[] args) {
        // 初始化一个空的集合。
        RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        System.out.println(randomSet.insert(0));
        System.out.println(randomSet.insert(1));

// 返回 false ，表示集合中不存在 2 。
        System.out.println(randomSet.remove(0));

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        System.out.println(randomSet.insert(2));
//
//// getRandom 应随机返回 1 或 2 。
//        System.out.println(randomSet.getRandom());
//        System.out.println(randomSet.getRandom());
//        System.out.println(randomSet.getRandom());
////        System.out.println(randomSet.getRandom());

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        System.out.println(randomSet.remove(1));

// 2 已在集合中，所以返回 false 。
//        System.out.println(randomSet.insert(2));

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.getRandom());

    }
}
