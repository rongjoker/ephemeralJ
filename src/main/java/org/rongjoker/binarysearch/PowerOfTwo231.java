package org.rongjoker.binarysearch;

import org.junit.Test;

public class PowerOfTwo231 {


    @Test
    public void test231(){
        System.out.println(isPowerOfTwo(5));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(3));
        System.out.println(isPowerOfTwo(4));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(2147483647));
    }

    public boolean isPowerOfTwo(int n) {

        if(n<0)return false;

        int left = 0, right = 31;

        while(left<=right){
            int middle = left + ((right - left)>>1);
            long p = (long)Math.pow(2,middle);
            if(p==n)return true;
            else if(p<n){
                    left = middle + 1;
            }else right = middle - 1;
        }

        return false;




    }
}
