package org.rongjoker.array;


import java.util.PriorityQueue;
import org.junit.Test;

/**
 * @date 09/09/2021
 * LCP 33. 蓄水
 *
 * 给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：
 *
 * 升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
 * 蓄水：将全部水桶接满水，倒入各自对应的水缸
 * 每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
 *
 * 注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/o8SXZn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：bucket = [1,3], vat = [6,8]
 *
 * 输出：4
 *
 * 输入：bucket = [9,0,1], vat = [0,2,2]
 *
 * 输出：3
 */
public class StoreWater {


    @Test
    public void testStoreWater(){


//        System.out.println(storeWater(new int[]{9,0,1},
//            new int[]{0,2,2}));
//
//        System.out.println(storeWater(new int[]{1,3},
//            new int[]{6,8}));

//        System.out.println(storeWater(new int[]{16,29,42,70,42,9},
//            new int[]{89,44,50,90,94,91}));

//        System.out.println(storeWater(new int[]{0,0,0,0,0,0,0,0,0,0},
//            new int[]{10000,10000,10000,10000,10000,10000,10000,10000,10000,10000}));

        System.out.println(storeWater(new int[]{9,0,1},
            new int[]{0,2,2}));

        //[16,29,42,70,42,9]
        //[89,44,50,90,94,91]

        //[0,0,0,0,0,0,0,0,0,0]
        //[10000,10000,10000,10000,10000,10000,10000,10000,10000,10000]


    }


    /**
     *
     * 用优先级队列做，速度很慢
     * @param bucket
     * @param vat
     * @return
     */
    public int storeWater(int[] bucket, int[] vat) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1,o2)->{
            int v1 = vat[o1]==0?0:((vat[o1]/bucket[o1]) + (vat[o1]%bucket[o1]>0?1:0));
            int v2 = vat[o2]==0?0:((vat[o2]/bucket[o2]) + (vat[o2]%bucket[o2]>0?1:0));

            return v2 - v1;

        });
        int cur = 0;
        int len = bucket.length;
        for (int i = 0; i < len; i++) {
            if(bucket[i]==0 && vat[i]!=0){
                bucket[i]++;
                cur++;
            }
            heap.add(i);
        }


        int p = heap.peek();
        int max = cur + (vat[p]==0?0:((vat[p]/bucket[p]) + (vat[p]%bucket[p]>0?1:0)))*len;
        int ans = cur + (vat[p]==0?0:((vat[p]/bucket[p]) + (vat[p]%bucket[p]>0?1:0)));

        int min = cur;

        while (min<=max){
            p = heap.poll();
            bucket[p] ++;
            heap.add(p);

            p = heap.peek();

            cur ++;

            min =  cur + (vat[p]==0?0: vat[p]/bucket[p] + (vat[p]%bucket[p]>0?1:0));

            ans = Math.min(ans,min);


        }

        return ans;

    }






}
