package org.rongjoker.code;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 倒计时59天，解决Kruskal
 *
 * @date 10/28/2020
 */
public class Day59Kruskal {

    @Test
    public void testFindJudge() {

        int N = 3;
        int[][] trust = {{1, 3}, {2, 3}};

        System.out.println(findJudge(N, trust));


    }


    /**
     * leetcode 997. 找到小镇的法官,非常简单，是统计入度和出度
     * 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
     * <p>
     * 如果小镇的法官真的存在，那么：
     * <p>
     * 小镇的法官不相信任何人。
     * 每个人（除了小镇法官外）都信任小镇的法官。
     * 只有一个人同时满足属性 1 和属性 2 。
     * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
     * <p>
     * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-town-judge
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param N
     * @param trust
     * @return
     */
    public int findJudge(int N, int[][] trust) {
        int[][] degree = new int[N][2];//0为入度，1为出度

        int i = 0;
        for (; i < trust.length; i++) {

            int out = trust[i][0];
            int in = trust[i][1];

            degree[in - 1][0]++;//入度+1
            degree[out - 1][1]++;//出度+1
        }

        int j = 0;
        for (; j < N; j++) {
            if (degree[j][1] == 0 && degree[j][0] == N - 1)
                return j + 1;
        }


        return -1;

    }


    /**
     * 1584. 连接所有点的最小费用
     */
    @Test
    public void testminCostConnectPoints() {

        int[][] points = {{0, 0}, {1, 1}, {1, 0}, {-1, 1}};

//        System.out.println(this.minCostConnectPointsWithPrim(points));
        System.out.println(this.minCostConnectPointsWithKruskal(points));

    }

    /**
     * 用prim算法实现的最小生成树，还可以利用优先级队列继续优化
     *
     * @param points
     * @return
     */
    public int minCostConnectPointsWithPrim(int[][] points) {
        int n = points.length;
        int[][] edges = new int[n][n];//每个点到其他点的边的长度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edges[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }
        int start = 0;//从0点出发
        int res = 0;//最小权值
        int[] cost = new int[n];
        int[] include = new int[n];//已经选中的点
        int tree_size = 1;//当前树的顶点个数
        for (int i = 0; i < n; i++) {//当前选中的点只有1个，只能和这一个点比较
            cost[i] = edges[start][i];
            include[i] = (i == start) ? -1 : start;//选中的标记为-1,未选中的标记为距离选中里最短的那个节点
        }
        while (tree_size < n) {
            int vertex = -1;
            int mindistance = Integer.MAX_VALUE;//这些变量不能放在while外面
            for (int i = 0; i < n; i++) {
                if (include[i] != -1) {//未被选中的点
                    if (cost[i] < mindistance) {
                        mindistance = cost[i];
                        vertex = i;
                    }
                }
            }
            if (vertex != -1) {
                tree_size++;
                res += mindistance;
                include[vertex] = -1;//选中的标记为-1
                for (int i = 0; i < n; i++) {//当前选中的点只有1个，只能和这一个点比较
                    int edge_current = edges[vertex][i];
                    if (include[i] != -1 && edge_current < cost[i]) {
                        cost[i] = edge_current;
                        include[i] = vertex;
                    }
                }
            }
        }
        return res;
    }

    /**
     * kruskal + 并查集+优先级队列,利用优先级队列每次选择最短的边，利用并查集避免环路,选出边的数量为点的数量-1说明选择完毕，结束
     *
     * @param points
     * @return
     */
    public int minCostConnectPointsWithKruskal(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));//第3个字段即边的长度
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                pq.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});//点-点-边长
            }
        }
        int ans = 0;
        int n = points.length;
        initialize(n);//初始化并查集
        int i = 0;
        while (i != n - 1) {
            int[] tmp = pq.poll();//取出最小的边
            int jj = root(tmp[0]);
            int kk = root(tmp[1]);
            if (jj != kk) {
                ++i;
                ans += tmp[2];
                union(jj, kk);
            }
        }
        return ans;

    }

    //并查集
    int[] parent;

    void initialize(int n ){
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int root(int p) {
        while (p != parent[p]) {
            p = parent[p];
            parent[p] = parent[parent[p]];
        }
        return p;
    }

    void union(int i, int j) {
        parent[root(i)] = root(j);
    }


    /**
     * 1584. 连接所有点的最小费用,可以用prim或者Kruskal
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {//计算顶点i到其他顶点之间的费用,两次循环，建立邻接矩阵
            for (int j = 0; j < n; j++) {
                edges[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }
        return prim(edges, 0);

    }

    public int prim(int[][] edge, int start) {//用邻接矩阵表示的无向图，从start开始prim
        int n = edge.length;//顶点个数
        int res = 0;//最小权值
        int tree_size = 1;//当前树的顶点个数
        int[] lowcost = new int[n];//lowcost[i]表示T1中的顶点i到集合T内某个顶点的最小权值
        int[] nerv = new int[n];//nerv[i]表示T1中的顶点i到集合T内的哪个顶点最近，如果i在T内，则nerv=-1

        //根据e初始化lowcost和nerv
        for (int i = 0; i < n; i++) {
            lowcost[i] = edge[start][i];
            nerv[i] = i == start ? -1 : start;
        }
        while (tree_size < n) {
            int min_dis = Integer.MAX_VALUE;
            int v = -1;
            for (int i = 0; i < n; i++) {//从T1中寻找一个顶点，使之是到T中节点的最小距离
                if (nerv[i] != -1 && lowcost[i] < min_dis) {
                    min_dis = lowcost[i];//不停的更新，直到循环完，找到最短的一条
                    v = i;
                }
            }
            if (v != -1) {
                nerv[v] = -1;
                tree_size++;
                res += min_dis;
                //由于集合T新加入了顶点v，所以要更新lowcost中顶点v与T1集合中各顶点的距离
                for (int j = 0; j < n; j++) {
                    if (nerv[j] != -1 && edge[v][j] < lowcost[j]) {
                        lowcost[j] = edge[v][j];//顶点j和刚加入的顶点v的距离更短
                        nerv[j] = v;//更换顶点j在T中的最近节点
                    }
                }
            }
        }

        return res;
    }


}
