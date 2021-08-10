package org.rongjoker.code;

public class VolatileTest3 {


    // b使用volatile修饰
    public static volatile long b = 0;

    //消除缓存行的影响
    // c不使用volatile修饰
//    @Contended
    public static long c = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (c == 0) {
//                long x = b;
                //这段代码注释掉，会进入死循环
                // 虽然您这个赋值操作没有任何的实际作用，但是由于变量b是一个volatile的变量，JIT编译器认为不能使用前面那种激进的优化策略，于是while中判断c的逻辑被保留了下来
                //本质上是 JIT直接把这个循环编译成了while(true)
            }
            System.out.println("c=" + c);
        }).start();

        Thread.sleep(1000);

        b = 1;
        c = 1;
    }
}
