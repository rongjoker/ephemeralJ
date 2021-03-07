package org.rongjoker.bt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 03/05/2021
 * 93. 复原 IP 地址 https://leetcode-cn.com/problems/restore-ip-addresses/
 * 回溯法应用题，虽然是medium，但是较组合数之类要难
 * 回溯法应用题也需要找到类似dp的状态转移方程
 */
public class RestoreIpAddresses93 {

    @Test
    public void test93() {

//        System.out.println("abc".substring(0, 3));


        List<String> strings = restoreIpAddresses("101023");

        strings.forEach(System.out::println);


    }


    List<String> permute = new ArrayList<>();
    List<String> path = new ArrayList<>();
    int len;


    public List<String> restoreIpAddresses(String s) {

        len = s.length();

        if (len < 4)
            return permute;


        this.restore(s, 0, 4);


        return permute;
    }


    public void restore(String code, int start, int limit) {

        if ((len - start) > limit * 3) return;

        if (limit == 0) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                stringBuilder.append(path.get(i));
                if (i < path.size() - 1)
                    stringBuilder.append(".");
            }

            permute.add(stringBuilder.toString());

            return;
        }


        if (start + 1 > len) return;

        if (code.charAt(start) == '0') {

            String temp = code.substring(start, start + 1);
            path.add(temp);
            restore(code, start + 1, limit - 1);
            path.remove(path.size() - 1);

        } else {

            for (int i = 1; i <= 3; i++) {
                if (start + i > len) break;

                String temp = code.substring(start, start + i);
                int integer = Integer.parseInt(temp);
                if (integer <= 255) {
                    path.add(temp);
                    restore(code, start + i, limit - 1);
                    path.remove(path.size() - 1);

                } else break;


            }

        }


    }


}
