package org.rongjoker.concurrent;

import java.util.concurrent.CountDownLatch;


/**
 *
 * @date 08/02/2021
 *  1114. 按序打印 https://leetcode-cn.com/problems/print-in-order/
 *
 *
 *
 *
 *
 */
public class Foo {


    public Foo() {

    }

    CountDownLatch countDownLatch1 = new CountDownLatch(1);
    CountDownLatch countDownLatch2 = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        countDownLatch1.countDown();

    }

    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatch1.await();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatch2.countDown();

    }

    public void third(Runnable printThird) throws InterruptedException {

        countDownLatch2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();


    }


    public static void main(String[] args) {

        Foo f = new Foo();

        Thread s2 = new Thread(() -> {
            try {
                f.second(() -> System.out.println(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread s1 = new Thread(() -> {
            try {
                f.first(() -> System.out.println(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread s3 = new Thread(() -> {
            try {
                f.third(() -> System.out.println(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        s1.start();
        s2.start();
        s3.start();


    }
}
