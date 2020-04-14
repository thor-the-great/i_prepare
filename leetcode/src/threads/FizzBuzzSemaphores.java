package threads;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzzSemaphores {
  private int n;
  Semaphore sem1 = new Semaphore(1), sem3 = new Semaphore(0);
  Semaphore sem5 = new Semaphore(0), sem15 = new Semaphore(0);

  public FizzBuzzSemaphores(int n) {
    this.n = n;
  }

  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {
    for (int val = 3; val <= n; val+= 3) {
      if (val % 15 == 0) {
        continue;
      }
      sem3.acquire();
      printFizz.run();
      sem1.release();
    }
  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {
    for (int val = 5; val <=n; val+= 5) {
      if (val % 15 == 0) {
        continue;
      }
      sem5.acquire();
      printBuzz.run();
      sem1.release();
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
    for (int val = 15; val <=n; val+= 15) {
      sem15.acquire();
      printFizzBuzz.run();
      sem1.release();
    }
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {
    for (int val = 1; val <=n; val++) {
      sem1.acquire();
      if (val % 15 == 0)
        sem15.release();
      else if (val % 5 == 0)
        sem5.release();
      else if (val % 3 == 0)
        sem3.release();
      else {
        printNumber.accept(val);
        sem1.release();
      }
    }
  }
}