package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1115. Print FooBar Alternately
 * Medium
 *
 * Suppose you are given the following code:
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will
 * call bar(). Modify the given program to output "foobar" n times.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "foobar"
 * Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls
 * bar(). "foobar" is being output 1 time.
 * Example 2:
 *
 * Input: n = 2
 * Output: "foobarfoobar"
 * Explanation: "foobar" is being output 2 times.
 */
public class PrintFooBarAlternatively {

    private int n;

    Lock lock = new ReentrantLock();
    Condition fooPrinted = lock.newCondition();
    Condition barPrinted = lock.newCondition();
    boolean printFooFlag = true;

    public PrintFooBarAlternatively(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while (!printFooFlag)
                barPrinted.await();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            printFooFlag = false;
            fooPrinted.signal();
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while(printFooFlag)
                fooPrinted.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            printFooFlag = true;
            barPrinted.signal();
            lock.unlock();
        }
    }
}
