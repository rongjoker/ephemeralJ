package org.rongjoker.contest.week245;

import org.junit.Test;

public class Test3 {

    @Test
    public void test3() {

    }


    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean[] bs = new boolean[3];

        for (int[] triplet : triplets) {
            if (triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]) {

                if (triplet[0] == target[0]) {
                    bs[0] = true;
                }

                if (triplet[1] == target[1]) {
                    bs[1] = true;
                }

                if (triplet[2] == target[2]) {
                    bs[2] = true;
                }


                if (bs[0] && bs[1] && bs[2]) return true;
            }
        }

        return false;


    }


}
