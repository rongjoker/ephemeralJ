package org.rongjoker.array;


import org.junit.Test;

/**
 * @date 06/29/2021
 * 168. Excel表列名称 https://leetcode-cn.com/problems/excel-sheet-column-title/
 *
 *
 */
public class ConvertToTitle168 {


    @Test
    public void test168(){

//        System.out.println(convertToTitle(26));
//        System.out.println(convertToTitle(28));
        System.out.println(convertToTitle(89991));
    }



    public String convertToTitle(int columnNumber) {

        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        return sb.toString();


    }


}
