package org.rongjoker.contest.week256;

import java.util.Arrays;
import org.junit.Test;


/**
 *
 * @date 08/29/2021
 * 5856. 完成任务的最少工作时间段
 *
 *
 * 你被安排了 n 个任务。任务需要花费的时间用长度为 n 的整数数组 tasks 表示，第 i 个任务需要花费 tasks[i] 小时完成。一个 工作时间段 中，你可以 至多 连续工作 sessionTime 个小时，然后休息一会儿。
 *
 * 你需要按照如下条件完成给定任务:
 *
 * 如果你在某一个时间段开始一个任务，你需要在 同一个 时间段完成它。
 * 完成一个任务后，你可以 立马 开始一个新的任务。
 * 你可以按 任意顺序 完成任务。
 * 给你 tasks 和 sessionTime ，请你按照上述要求，返回完成所有任务所需要的 最少 数目的 工作时间段 。
 *
 * 测试数据保证 sessionTime 大于等于 tasks[i] 中的 最大值 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
public class Test3 {


    /**
     *
     * 枚举二进制子集
     */
    @Test
    public void testSubBinary() {

        int i = 3;
        // 枚举状态 i 的二进制子集
        for (int j = i; j > 0; j = (j - 1) & i) {
            System.out.println(j+":"+Integer.toBinaryString(j));
        }

        System.out.println("--------");

        System.out.println(3 & 1);

    }


    @Test
    public void test3(){
        System.out.println(minSessions(new int[]{1, 2, 3},3));
        System.out.println(minSessions2(new int[]{1, 2, 3},3));

    }

    public int minSessions2(int[] tasks, int sessionTime) {

        int len = tasks.length;
        int max = 1<<len;
        int[] dp = initialize(tasks,sessionTime);
        for (int i = 1; i < max; i++) {
            if(dp[i]==1)continue;
            //一个周期无法覆盖的情况
            for (int j =i;j >0;j=i&(j-1)){
                dp[i] = Math.min(dp[i],dp[j] + dp[j^i]);
                //比如dp[1111] = dp[1001] + dp[0110],通过互补，全部选中
            }
        }
        return dp[max-1];//即1111

    }


    /**
     * 初始化
     * tasks是穷举所有可能的情况
     * 比如[1,2,3],可能是[1,0,0][1,1,0],[1,0,1]----[1,1,1]等7种情况的数组，压缩到二进制里就是7个数字，
     * 数组的长度可以直接用1>>3(即是8)来表示
     * @param tasks
     */
    public int[]  initialize(int[] tasks,int sessionTime){
        int len = tasks.length;
        int max = 1<<len;
        int[] dp = new int[max];
        final int INF = 20;//最大长度为14，故设置为20即可
        Arrays.fill(dp, INF);

        for (int i = 1; i < max; i++) {//比如i=2，为011
            int spend =0;
            int cur = i;int cur_index = 0;
            while (cur>0 && spend<=sessionTime){
                int temp_bit = cur &1;//取当前位置，如果是1，表示选中
                if(temp_bit==1)spend+=tasks[cur_index];
                cur>>=1;//查看下一个位置
                cur_index++;
            }
            if(spend<=sessionTime)dp[i] = 1;//一个时间周期可以做完,比如011，是第一个和第二个任务这种的，可以做完
        }

        return dp;


    }

    public int minSessions(int[] tasks, int sessionTime) {

        int n = tasks.length, m = 1 << n;
        final int INF = 20;
        int[] dp = new int[m];
        Arrays.fill(dp, INF);

        // 预处理每个状态，合法状态预设为 1，预处理这一步是最难理解的，也可以说是状态压缩dp的一个关键
        for (int i = 1; i < m; i++) {
            int state = i, idx = 0;
            int spend = 0;
            while (state > 0) {
                int bit = state & 1;
                if (bit == 1) {
                    spend += tasks[idx];
                }
                state >>= 1;
                idx++;
            }
            if (spend <= sessionTime) {
                dp[i] = 1;
            }
        }

        // 对每个状态枚举子集，跳过已经有最优解的状态
        for (int i = 1; i < m; i++) {
            if (dp[i] == 1) {
                continue;
            }
            for (int j = i; j > 0; j = (j - 1) & i) {
                // i 状态的最优解可能由当前子集 j 与子集 j 的补集得来
                dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
            }
        }

        return dp[m - 1];

    }


}
