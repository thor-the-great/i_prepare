package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOrderLockCondition {

    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition(), c2 = lock.newCondition();
    int num = 0;

    public PrintOrderLockCondition() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
      lock.lock();
      printFirst.run();
      num = 1;
      c1.signal();
      lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
      lock.lock();
      while(num < 1) {
        c1.await();
      }
      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();
      num = 2;
      c2.signal();
      lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
      lock.lock();
      while(num < 2) {
        c2.await();
      }
      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
      lock.unlock();
    }
  }
