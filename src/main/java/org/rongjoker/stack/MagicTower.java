package org.rongjoker.stack;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.Test;

/**
 * LCP 30. 魔塔游戏
 * 小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。
 *
 * 小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/p0NxJO
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：nums = [100,100,100,-250,-60,-140,-50,-50,100,150]
 *
 * 输出：1
 *
 * 解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/p0NxJO
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：nums = [-200,-300,400,0]
 *
 * 输出：-1
 *
 * 解释：调整访问顺序也无法完成全部房间的访问。
 */
public class MagicTower {

    @Test
    public void testMagicTower() {
        System.out.println(magicTower(new int[]{100, 100, 100, -250, -60, -140, -50, -50, 100, 150}));
        System.out.println(magicTower(new int[]{-200, -300, 400, 0}));

        Set<String > set = new HashSet<>();
        set.add(null);
        set.add("123");
        System.out.println(set.size());
        System.out.println(set);
        System.out.println(set.contains(null));

    }


    public int magicTower(int[] nums) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        long cur = 1;
        for (int num : nums) {
            cur += num;
        }
        if (cur <= 0) {
            return -1;
        }

        cur = 1;
        int ans = 0;

        for (int num : nums) {
            cur += num;

            if (num < 0) {
                queue.offer(num);
            }
            if (cur <= 0) {
                cur -= queue.poll();
                ans++;
            }
        }
        return ans;

    }

}
