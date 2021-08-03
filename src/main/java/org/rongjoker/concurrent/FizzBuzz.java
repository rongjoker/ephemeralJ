package org.rongjoker.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

/**
 * @date 08/03/2021
 * <p>
 * 1195. 交替打印字符串 https://leetcode-cn.com/problems/fizz-buzz-multithreaded/
 * <p>
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FizzBuzz {


    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    CountDownLatch countDownLatch1 = new CountDownLatch(1);
    CountDownLatch countDownLatch2 = new CountDownLatch(1);
    CountDownLatch countDownLatch3 = new CountDownLatch(1);


    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        countDownLatch1.await();
        printFizz.run();

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

        countDownLatch2.await();
        printBuzz.run();

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

        countDownLatch3.await();
        printFizzBuzz.run();

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <= n; i++) {

            if (i % 3 == 0 && i % 5 == 0) countDownLatch3.countDown();
            else if (i % 3 == 0) countDownLatch1.countDown();
            else if (i % 5 == 0) countDownLatch2.countDown();
            else printNumber.accept(i);

        }

    }

    public static void main(String[] args) {

        FizzBuzz f = new FizzBuzz(15);

        Thread A = new Thread(() -> {
            try {
                f.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread B = new Thread(() -> {
            try {
                f.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread C = new Thread(() -> {
            try {
                f.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread D = new Thread(() -> {
            try {
                f.number(p -> System.out.println("xxx"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        B.start();
        A.start();
        C.start();
        D.start();

    }


}
