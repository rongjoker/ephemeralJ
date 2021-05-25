package org.rongjoker.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 03/07/2021
 * 22. 括号生成 https://leetcode-cn.com/problems/generate-parentheses/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 回溯算法的应用题目(字符串回溯算法)
 * 这个题目需要重点理解，有递归的时候不需要while或者for，会自动进行循环迭代，故只需要判断条件即可
 *
 *
 */
public class GenerateParenthesis22 {


    @Test
    public void test22(){

        System.out.println(generateParenthesis(3));

    }

    List<String> permute = new ArrayList<>();



    public List<String> generateParenthesis(int n) {

        generate(n,0,0,new StringBuilder());

        return permute;

    }



    public void  generate(int size,int left,int right,StringBuilder path){

        if(path.length() == size*2){
            permute.add(path.toString());
            return;
        }

        if (left <size){

            path.append("(");
            generate(size,left+1,right,path);
            path.deleteCharAt(path.length()-1);

        }


        if (right <left){
            path.append(")");
            generate(size,left,right+1,path);
            path.deleteCharAt(path.length()-1);


        }

    }



}
