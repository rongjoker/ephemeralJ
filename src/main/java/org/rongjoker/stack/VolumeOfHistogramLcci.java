package org.rongjoker.stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 04/02/2021
 *
 * 面试题 17.21. 直方图的水量 https://leetcode-cn.com/problems/volume-of-histogram-lcci/
 * 利用栈加双指针从左向右，再从右向左逼近，从左侧开始找到比左侧高的，计算面积，删掉中间的数据；一直到最右边，如果没有找到比左侧更高的，则从右侧向左重复上述操作
 * 4月2日 每日一题
 *
 */
public class VolumeOfHistogramLcci {

    @Test
    public void testVolumeOfHistogramLcci(){

        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));

    }

    public int trap(int[] height) {
        if(height.length<3)return 0;

        List<Integer> stack = new ArrayList<>();
        stack.add(0);
        int left = 0,sum=0,temp,right=height.length-1;
        for(int i=1;i<height.length;++i){
            if(height[i]>=height[left]){
                temp = height[left] * (i-left-1);
                while(stack.get(stack.size()-1)>left){
                    temp-= height[stack.get(stack.size()-1)];
                    stack.remove(stack.size()-1);
                }
                stack.remove(stack.size()-1);//删除左侧
                left = i;
                sum+=temp;
            }
            stack.add(i);
        }

        if(right>left){
            stack.remove(stack.size()-1);//删除右侧
        }

        int node_l;
        while (right>left){
            node_l = stack.get(stack.size()-1);
            if(height[node_l]>=height[right]){
                temp = height[right] * (right-node_l-1);
                for (int i = node_l+1; i <right; i++) {//这里注意，是从左侧的右边开始计算
                    temp-= height[i];
                }
                right = node_l;
                sum+=temp;
            }

            stack.remove(stack.size()-1);
        }

        return sum;

    }
}
