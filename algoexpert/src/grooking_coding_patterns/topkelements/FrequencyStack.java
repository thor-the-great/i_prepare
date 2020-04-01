package grooking_coding_patterns.topkelements;

import java.util.*;

/**
 * Problem Challenge 3
 * We'll cover the following
 * Frequency Stack (hard)
 *
 * Design a class that simulates a Stack data structure, implementing the following two operations:
 *
 * push(int num): Pushes the number ‘num’ on the stack.
 * pop(): Returns the most frequent number in the stack. If there is a tie, return the number which
 * was pushed later.
 * Example:
 *
 * After following push operations: push(1), push(2), push(3), push(2), push(1), push(2), push(5)
 *
 * 1. pop() should return 2, as it is the most frequent number
 * 2. Next pop() should return 1
 * 3. Next pop() should return 2
 */
public class FrequencyStack {

  /**
   * Keep the priority queue of objects with num, sequence and frequency. Every time increment
   * sequence and insert the new object (don't update existing one).
   */
  int seqNumber = 1;
  Map<Integer, Integer> map = new HashMap<>();
  PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) ->
          (e1.freq != e2.freq)
                  ? (e2.freq - e1.freq)
                  : (e2.seq - e1.seq));

  public void push(int num) {
    map.put(num, map.getOrDefault(num, 0) + 1);
    pq.add(new Element(num, seqNumber++, map.get(num)));
  }

  public int pop() {
    Element cur = pq.poll();
    if (cur.freq == 1) {
      map.remove(cur.num);
    } else {
      map.put(cur.num, cur.freq - 1);
    }
    return cur.num;
  }

  class Element {
    int num, seq, freq;
    Element (int x, int y, int z) {
      num = x;
      seq = y;
      freq = z;
    }
  }

  public static void main(String[] args) {
    FrequencyStack frequencyStack = new FrequencyStack();
    frequencyStack.push(1);
    frequencyStack.push(2);
    frequencyStack.push(3);
    frequencyStack.push(2);
    frequencyStack.push(1);
    frequencyStack.push(2);
    frequencyStack.push(5);
    System.out.println(frequencyStack.pop());
    System.out.println(frequencyStack.pop());
    System.out.println(frequencyStack.pop());
  }
}

