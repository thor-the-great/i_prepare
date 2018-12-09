package random_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 716. Max Stack
 * Easy
 *
 *
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements,
 * only remove the top-most one.
 *
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * Note:
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 *
 */
class MaxStack {
    TreeMap<Integer, List<Node>> sortedMap;
    MyList stack;

    /**
     * initialize your data structure here.
     */
    public MaxStack() {
        stack = new MyList();
        sortedMap = new TreeMap();
    }

    public void push(int x) {
        Node newNode = stack.add(x);
        sortedMap.putIfAbsent(x, new ArrayList<Node>());
        sortedMap.get(x).add(newNode);
    }

    public int pop() {
        Node pop = stack.pop();
        List<Node> l = sortedMap.get(pop.val);
        l.remove(l.size() - 1);
        if (l.size() == 0) {
            sortedMap.remove(pop.val);
        }
        return pop.val;
    }

    public int top() {
        return stack.peek().val;
    }

    public int peekMax() {
        return sortedMap.lastKey();
    }

    public int popMax() {
        int max = sortedMap.lastKey();
        List<Node> list = sortedMap.get(max);
        Node n = list.remove(list.size() - 1);
        stack.remove(n);
        if (list.size() == 0) {
            sortedMap.remove(max);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxStack obj = new MaxStack();
        obj.push(5);
        obj.push(1);
        obj.push(5);
        System.out.println(obj.top());//5
        System.out.println(obj.popMax());//5
        System.out.println(obj.top());//1
    }
}


class MyList {
    Node head, tail;

    MyList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    Node add(int v) {
        Node n = new Node(v);
        n.next = tail;
        n.prev = tail.prev;
        tail.prev = tail.prev.next = n;
        return n;
    }

    Node pop() {
        return remove(tail.prev);
    }

    Node remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        return n;
    }

    Node peek() {
        return tail.prev;
    }
}

class Node {
    int val;
    Node prev, next;

    Node(int val) {
        this.val = val;
    }
}

