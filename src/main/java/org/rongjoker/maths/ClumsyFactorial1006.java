package org.rongjoker.maths;

import org.junit.Test;

/**
 * @date 01/01/2021
 * 1006. 笨阶乘 https://leetcode-cn.com/problems/clumsy-factorial/
 * 我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)
 * 4月1日的每日一题
 *
 */
public class ClumsyFactorial1006 {

    @Test
    public void test1006(){
        System.out.println(clumsy(4));
    }

    public int clumsy(int N) {

        if(N==1)return N;

        int max=0, temp=N,index=1;
        --N;
        while(N>0){
            if(index%4==1)
                temp*=N;
            else if(index%4==2)
                temp/=N;
            else if(index%4==3){
                max+=temp;
                temp=N;
            }else {
                max+=temp;
                temp=-N;
            }

            if(index==4)
                index=1;
            else  ++index;

            --N;

        }

        if(temp!=0)
            max+=temp;


        return max;

    }
}
