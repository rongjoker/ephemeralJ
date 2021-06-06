package org.rongjoker.contest.week244;

import org.junit.Test;

import java.util.Arrays;

public class Test4 {

    @Test
    public void test4(){

    }


    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        long ans = Long.MAX_VALUE;
        int len = packages.length;

        for (int[] box : boxes) {
            long w = 0;
            int m = Arrays.stream(box).max().getAsInt();
            Arrays.sort(box);
            if(m<packages[len-1])continue;
            int start = 0;
            for (int aPackage : packages) {
                while (box[start]<aPackage)++start;
                w+= (box[start] - aPackage);
            }
            ans = Math.min(ans,w);

        }


        return ans== Long.MAX_VALUE ? -1:(int) (ans % 1000000007);
    }
}
