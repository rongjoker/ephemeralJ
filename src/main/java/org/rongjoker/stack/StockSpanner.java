package org.rongjoker.stack;

import java.util.ArrayList;
import java.util.List;

/**
 *  04/09/2021
 *  901. 股票价格跨度  https://leetcode-cn.com/problems/online-stock-span/
 *  利用数组记录下标和偏移量，跳跃式找数据
 *
 */
public class StockSpanner {

    List<int[]> list;

    public StockSpanner() {
        list = new ArrayList<>();

    }

    public int next(int price) {

        if(list.size()==0) {
            list.add(new int[]{price,1});
            return 1;
        }else{
            int index = list.size()-1,score=1;
            while(index>-1 && list.get(index)[0]<=price){
                score+= list.get(index)[1];
                index-= list.get(index)[1];
            }
            list.add(new int[]{price,score});
            return score;
        }

    }
}
