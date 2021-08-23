package org.rongjoker.array;

import org.junit.Test;

public class GetMaximumGenerated1646 {

    @Test
    public void test1646(){
        System.out.println(getMaximumGenerated(0));
        System.out.println(getMaximumGenerated(1));
        System.out.println(getMaximumGenerated(7));
        System.out.println(getMaximumGenerated(99));
    }


    public int getMaximumGenerated(int n) {
        if(n==0)return 0;
        int[] nums = new int[n+1];
        nums[1]=1;
        int ans = 1;
        for(int i=0;i<=n/2;++i){
            int j = i*2;

            if(j>=2 && j<=n){
                nums[j]=nums[i];
                ans = Math.max(ans,nums[j]);

            }

            if (j>=1 && j<=n-1){
                nums[j+1]=nums[i]+nums[i+1];
                ans = Math.max(ans,nums[j+1]);

            }

        }
        return ans;

    }

}
