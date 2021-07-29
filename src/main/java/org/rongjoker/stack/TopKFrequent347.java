package org.rongjoker.stack;

import org.junit.Test;

import java.util.*;

/**
 * @date 02/18/2021
 * <p>
 * 347. 前 K 个高频元素. 最小的k个数 https://leetcode-cn.com/problems/top-k-frequent-elements/
 * 求前 k 大，用小根堆，求前 k 小，用大根堆 套路题目
 * 可以做细节优化,不需要额外的记录频率的类
 * 可以再做优化，用2位的数组替代记录频率的类
 */
public class TopKFrequent347 {

    @Test
    public void test347() {
        int[] nums = {3,0,1,0};
        System.out.println(Arrays.toString(topKFrequent3(nums, 1)));


    }

    public int[] topKFrequent3(int[] nums, int k) {

        Map<Integer,Integer> dict = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(((o1, o2) -> dict.get(o2) - dict.get(o1)));

        for(int i:nums){
            if(!dict.containsKey(i)){
                dict.put(i,1);
            }else dict.put(i,dict.get(i)+1);
        }

        Set<Integer> set = dict.keySet();

        heap.addAll(set);

        int[] ans = new int[k];
        for(int i=0;i<k;++i){
            ans[i] = heap.poll();
        }

        return ans;

    }


    public int[] topKFrequentOptimize2(int[] nums, int k) {

        if (k == 0)
            return new int[0];

        int len = nums.length;
        if (len <= k) return nums;


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

        //例外情况
        if (map.size() < k) {
            int[] array = new int[map.size()];
            int index = 0;
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext())
                array[index++] = iterator.next();

            return array;
        }

        int num,count;


        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i->i[1]));//小顶堆,将堆的大小保持在k

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            num = entry.getKey(); count = entry.getValue();
            if (pq.size() == k) {
                if (pq.peek()[1] < count) {
                    pq.poll();
                    pq.offer(new int[]{num, count});
                }
            } else {
                pq.offer(new int[]{num, count});
            }


        }


        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = pq.poll()[0];
        }

        return array;
    }


    public int[] topKFrequentOptimize(int[] nums, int k) {

        if (k == 0)
            return new int[0];

        int len = nums.length;
        if (len <= k) return nums;


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.get(nums[i]) == null)
                map.put(nums[i], 1);
            else
                map.put(nums[i], map.get(nums[i]) + 1);
        }

        //例外情况
        if (map.size() < k) {
            int[] array = new int[map.size()];
            int index = 0;
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext())
                array[index++] = iterator.next();

            return array;
        }


        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));//小顶堆,将堆的大小保持在k

        map.forEach((key, v) -> {

            if (pq.size() < k) {
                pq.offer(key);
            } else {
                if (v > map.get(pq.peek())) {
                    pq.poll();
                    pq.offer(key);
                }
            }
        });

        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = pq.poll();
        }

        return array;
    }


    public int[] topKFrequent(int[] nums, int k) {

        if (k == 0)
            return new int[0];

        int len = nums.length;
        if (len <= k) return nums;


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.get(nums[i]) == null)
                map.put(nums[i], 1);
            else
                map.put(nums[i], map.get(nums[i]) + 1);
        }

        if (map.size() < k) {
            int[] array = new int[map.size()];
            int index = 0;
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext())
                array[index++] = iterator.next();

            return array;

        }


//        PriorityQueue<NumberNode> pq = new PriorityQueue<>((i1, i2) -> i2.frequent - i1.frequent);//大顶堆,将堆的大小保持在k
        PriorityQueue<NumberNode> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i.frequent));//小顶堆,将堆的大小保持在k

        map.forEach((key, v) -> {

            if (pq.size() < k) {
                pq.offer(new NumberNode(key, v));
            } else {
                if (v > pq.peek().frequent) {
                    pq.poll();
                    pq.offer(new NumberNode(key, v));
                }
            }
        });

        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = pq.poll().val;
        }

        return array;
    }

    /**
     * 记录频率的类
     */
    static class NumberNode {

        public NumberNode(int val, int frequent) {
            this.val = val;
            this.frequent = frequent;
        }

        int val;
        int frequent;
    }
}

