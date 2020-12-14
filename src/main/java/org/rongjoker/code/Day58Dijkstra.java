package org.rongjoker.code;


import org.junit.Test;

import java.util.*;

/**
 * 倒计时58天，解决dijkstra，本质是贪心算法，每一次都把当前拿到的路径和之前已经保存的做比较，如果更小，则更新
 *
 * @date 10/29/2020
 */
public class Day58Dijkstra {

    /**
     * 743. 网络延迟时间
     */
    @Test
    public void testNetworkDelayTime() {

        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4, K = 2;

        System.out.println(this.networkDelayTime(times, N, K));


    }

    Map<Integer, Integer> dist;

    /**
     * 743. 网络延迟时间
     * 有 N 个网络节点，标记为 1 到 N。
     * <p>
     * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
     * <p>
     * 现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/network-delay-time
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());//创建点-边的集合
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});//每个点的边的集合
        }
        dist = new HashMap();//记录最短路径，默认都是最大值
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);

        dist.put(K, 0);//起始点为0
        boolean[] seen = new boolean[N + 1];//已经访问过

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; ++i) {
                if (!seen[i] && dist.get(i) < candDist) {//还未访问过
                    candDist = dist.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0) break;
            seen[candNode] = true;
            //关键的步骤，记录这个点连接的点以及起始点-这个点+这个点到连接的点的边长，如果比已经记录的还短，则更新
            if (graph.containsKey(candNode))
                for (int[] info : graph.get(candNode))
                    dist.put(info[0],
                            Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
        }

        int ans = 0;
        for (int cand : dist.values()) {//如果有未抵达的点，则返回-1，否则返回dist的最大值，即整个图的最短路径
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    /**
     * 使用堆优化过的dijkstra算法
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTimeWithHeap(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        //优先级队列-堆
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }



}
