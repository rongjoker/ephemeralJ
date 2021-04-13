package org.rongjoker.contest.week236;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @date 04/13/2021
 *
 */
public class MKAverage {

    List<Integer> list = new ArrayList<>();

    int left,space,del,mother;long sum;
    int [] array;

    public MKAverage(int m, int k) {
        space=m;del=k;
        array = new int[space];
        mother = m - k*2;
        left = -m;
    }

    public void addElement(int num) {
        list.add(num);
        ++left;
    }

    public int calculateMKAverage() {
        if(left<0)return -1;
        for(int index=0;index<space;++index){
            array[index] = list.get(left+index);
        }
        Arrays.sort(array);
        sum =0;
        for(int temp=del;temp< space - del;++temp){
            sum+=array[temp];
        }
        return (int)  (sum/mother);


    }
}
