package org.rongjoker.stack;

import org.junit.Test;

import java.util.*;

/**
 * @date 05/20/2021
 * 692. 前K个高频单词 https://leetcode-cn.com/problems/top-k-frequent-words/
 *  可以优化的优先级队列
 *
 *
 */
public class TopKFrequent692 {

    @Test
    public void test692() {

        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequentOptimize(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
//        System.out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"},2));

    }


    public List<String> topKFrequentOptimize(String[] words, int k) {

        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> map.get(o1).equals(map.get(o2)) ? o2.compareTo(o1) : map.get(o1).compareTo(map.get(o2)));

        map.forEach((kk, v) -> {
            queue.offer(kk);
            if (queue.size() > k) queue.poll();

        });


        int size = Math.min(k, queue.size());
        String[] ss = new String[size];
        for (int i = 0; i < size; i++) {
            ss[size - i - 1] = queue.poll();
        }
        return Arrays.asList(ss);


    }

    public List<String> topKFrequent(String[] words, int k) {
        PriorityQueue<NumberNode> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.frequent != o2.frequent) return o1.frequent - o2.frequent;
            else return o2.val.compareTo(o1.val);

        });
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        map.forEach((kk, v) -> {
            if (queue.size() < k) {
                queue.offer(new NumberNode(kk, v));
            } else {
                NumberNode temp = queue.peek();
                if (temp.frequent < v || (temp.frequent == v && temp.val.compareTo(kk) > 0)) {
                    queue.poll();
                    queue.offer(new NumberNode(kk, v));
                }

            }
        });


        int size = Math.min(k, queue.size());
        String[] ss = new String[size];
        for (int i = 0; i < size; i++) {
            ss[size - i - 1] = queue.poll().val;
        }
        return Arrays.asList(ss);


    }

    /**
     * 记录频率的类
     */
    static class NumberNode {

        public NumberNode(String val, int frequent) {
            this.val = val;
            this.frequent = frequent;
        }

        String val;
        int frequent;
    }
}
