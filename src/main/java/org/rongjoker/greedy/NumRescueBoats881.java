package org.rongjoker.greedy;

import java.util.Arrays;
import org.junit.Test;

/**
 *   @date 08/26/2021
 *   881. 救生艇
 *
 *   第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 *
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 *
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/boats-to-save-people
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class NumRescueBoats881 {


    @Test
    public void test881(){
        System.out.println(numRescueBoats(new int[]{1,2},3));
        System.out.println(numRescueBoats(new int[]{3,2,2,1},3));
        System.out.println(numRescueBoats(new int[]{3,5,3,4},5));

    }


    public int numRescueBoats(int[] people, int limit) {
        int len = people.length;
        if(len==1)return 1;
        Arrays.sort(people);
        int ans = 0;
        int left = 0,right =len-1;
        while (left<=right){
            if(people[right]<limit && people[left]<=(limit-people[right])){
                left++;
            }
            right--;
            ans++;
        }

        return ans;




    }

}
