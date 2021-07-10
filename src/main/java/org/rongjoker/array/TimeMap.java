package org.rongjoker.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 07/10/2021
 * 981. 基于时间的键值存储 https://leetcode-cn.com/problems/time-based-key-value-store/
 */
public class TimeMap {


    /**
     * Initialize your data structure here.
     */
    public TimeMap() {
        time = new HashMap<>();
        map = new HashMap<>();

    }

    Map<String, List<Integer>> time;
    Map<String, Map<Integer, String>> map;

    public void set(String key, String value, int timestamp) {
        if (!time.containsKey(key)) time.put(key, new ArrayList<>());
        time.get(key).add(timestamp);

        if (!map.containsKey(key)) map.put(key, new HashMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {

        if (!time.containsKey(key)) return "";
        List<Integer> temp = time.get(key);
        int left = 0, right = temp.size() - 1, target = -1;
        if (temp.get(left) > timestamp) return "";

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (temp.get(mid) > timestamp) {
                target = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        if (target == -1) return map.get(key).get(temp.get(temp.size() - 1));
        else return map.get(key).get(temp.get(target - 1));

    }

    public static void main(String[] args) {

        TimeMap kv = new TimeMap();
        kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1 
        System.out.println(kv.get("foo", 1));
        ;  // 输出 "bar"
        System.out.println(kv.get("foo", 3));
        ; // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）
        kv.set("foo", "bar2", 4);
        System.out.println(kv.get("foo", 4));
        ; // 输出 "bar2"
        System.out.println(kv.get("foo", 5));
        ; // 输出 "bar2"


    }


}
