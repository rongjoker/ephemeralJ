package org.rongjoker.contest.biweekly54;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class Test1 {


    @Test
    public void test1() {



    }


    public boolean isCovered(int[][] ranges, int left, int right) {

        Arrays.sort(ranges,(Comparator.comparingInt(o -> o[0])));

        int index = left;

        for (int[] range : ranges) {

            if (range[0] > index) {
                return false;
            }


            if (range[1] < index) {
                continue;
            }


            while (range[0] <= index && range[1] >= index) {
                if (index == right) return true;
                index++;
            }


        }

        return false;






    }



}
