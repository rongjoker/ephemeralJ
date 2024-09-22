package org.rongjoker.binarytree;

import org.junit.Test;

public class GeekTest {

    @Test
    public void test1(){

        int a = 3;
        int b = 1;

        int[] aa = getBitReverse(a);
        int[] bb = getBitReverse(b);

        String[] setNames = new String[]{"前排主驾","前排副驾","后排左","后排右"};

        for (int i = 0; i < 4; i++) {
            if(aa[i] != bb[i]){
                if(aa[i]==0){
                    System.out.printf("%s 增人%n",setNames[i]);
                }else {
                    System.out.printf("%s 减人%n",setNames[i]);
                }
            }
        }

    }


    /**
     * 3:[1, 1, 0, 0]
     * 6:[0, 1, 1, 0]
     */
    public int[] getBitReverse(int x) {
        int[] cc = new int[4];
        String binaryString = Integer.toBinaryString(x);
        for (int i = binaryString.length()-1; i >=0 ; i--) {
            char c = binaryString.charAt(i);
            int index = (binaryString.length()-1) -i ;
            cc[index] = c == '1' ? 1 : 0;
        }
        return cc;

    }



    public int[] getBit(int x) {
        int[] cc = new int[4];
        String binaryString = Integer.toBinaryString(x);
        System.out.println(binaryString);
        for (int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            cc[i] = c == '1' ? 1 : 0;
        }
        return cc;

    }


}
