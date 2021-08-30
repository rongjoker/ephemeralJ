package org.rongjoker.contest.week256;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

public class Test2 {

    @Test
    public void test2() {
        System.out.println(kthLargestNumber(
            new String[]{"623986800", "3", "887298", "695", "794", "6888794705", "269409", "59930972", "723091307",
                "726368", "8028385786"
                , "378585"
            }, 11));
    }


    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (o1,o2)->{
            if(o1.equals(o2))return 0;
            else if(o1.length()>o2.length())return 1;
            else if(o1.length()<o2.length())return -1;
            else {
                int lens = o1.length();
                for (int i = 0; i < lens; i++) {
                    if(o1.charAt(i)>o2.charAt(i))return 1;
                    else if(o1.charAt(i)<o2.charAt(i))return -1;
                }
                return 0;
            }

        });
        int len = nums.length;
        return nums[len - k];

    }

}
