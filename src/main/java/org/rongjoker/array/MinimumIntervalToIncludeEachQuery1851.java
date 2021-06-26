package org.rongjoker.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @date 05/18/2021
 * 1851. 包含每个查询的最小区间 https://leetcode-cn.com/problems/minimum-interval-to-include-each-query/
 * 有离线算法和在线算法两种
 * 离线算法是把所有数据和结果算好，然后去查询,可以直接将查询条件打散，和区间数据放在一起进行控制,顺序处理所有的数据（包含插入+删除+查询）
 * 在线则是实时处理已有数据（线段树之类）
 */
public class MinimumIntervalToIncludeEachQuery1851 {


    @Test
    public void test1851() {
        System.out.println(Arrays.toString(minInterval(new int[][]{{2, 3}, {2, 5}, {1, 8}, {20, 25}}
                , new int[]{2, 19, 5, 22})));
    }


    public int[] minInterval(int[][] intervals, int[] queries) {
        List<int[]> ready = new ArrayList<>();
        for (int[] interval : intervals) {
            ready.add(new int[]{0, interval[0], interval[1]});//开始
            ready.add(new int[]{2, interval[1], interval[0]});//结束
        }
        for (int i = 0; i < queries.length; i++) {
            ready.add(new int[]{1, queries[i], i});//查询
        }
        ready.sort(((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            } else return o1[0] - o2[0];
        }));


        int len = queries.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();//维持长度的排序
        int[] ans = new int[len];
        int sw;

        for (int[] r : ready) {
            if (r[0] == 0) {//开始事件-开始的时候就把长度记录下来，查询的时候不需要知道具体是哪个窗口，只要知道最短的窗口的大小即可
                sw = r[2] - r[1] + 1;
                map.put(sw, map.getOrDefault(sw, 0) + 1);//这个宽度有几个区间
            } else if (r[0] == 2) {//结束事件,删除对应长度
                sw = r[1] - r[2] + 1;
                int available = map.get(sw);
                if (available > 1) {
                    map.put(sw, available - 1);//这个宽度有几个区间,减去一个
                } else map.remove(sw);//空了就删除

            } else {//r[0] == 1 查询
                if (map.isEmpty()) ans[r[2]] = -1;
                else ans[r[2]] = map.firstKey();
            }
        }

        return ans;

    }
}
