package org.rongjoker.dp.decide;

import org.junit.Test;

import java.util.*;

/**
 * @date 03/26/2021
 * 139. 单词拆分 https://leetcode-cn.com/problems/word-break/
 * 动态规划题目
 * 将字典转换为资源，就成了充分背包问题
 * 比如字典被分为长度为3、4、5的三种资源，每种有若干个单词
 * 字符串依次前进，按照能够满足填充则继续向前(比如填充4位，4的资源有"leet"/"joke"，先检查[当前位置-4]的位置是否填充，若填充则检查当前位置向后+4组成的单词是否在4的这2个资源里，没有则跳过)
 * 实际上可以再优化，如果最后一个有效位置在循环完一遍后无法向前，则直接跳出
 * 不过这种优化，能产生的效果不大
 *
 */
public class WordBreak139 {

    @Test
    public void test139(){

        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add( "code");
        System.out.println(wordBreak(s,wordDict));
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        int[] dp = new int[len];
        Map<Integer, Set<String>> wh = new HashMap<>();
        Set<Integer> keyset = wh.keySet();
        for(String word:wordDict){
            Set<String> set = wh.get(word.length());
            if(null==set){
                set=new HashSet<>();
                set.add(word);
                wh.put(word.length(),set);
            }else
                set.add(word);
        }
        int max = 0;

        for (Integer integer : keyset) {
            max = Math.max(max,integer);
        }

        int flag = 0;

        for(int i=1;i<=len;++i){
            if(i-flag >max+1){
                break;
            }
            for(int index:keyset){

                if(i-index==0 || (i-index>0 && dp[i-index-1]==1)){
                    if(wh.get(index).contains(s.substring(i-index,i))){
                        dp[i-1]=1;
                        flag = i-1;
                    }

                }
            }
        }

        return dp[len-1]==1;

    }
}
