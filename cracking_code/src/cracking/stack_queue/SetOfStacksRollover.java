package cracking.stack_queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I057833 on 1/5/2017.
 */
public class SetOfStacksRollover {
    int capacityLimit;
    List<MyStack> set = new ArrayList();

    SetOfStacksRollover(int capacityLimit) {
        this.capacityLimit = capacityLimit;
    }

    void push(int value)
    {
        MyStack stack = getLastStack();
        if (stack != null && !stack.isFull()) {
            stack.push(value);
        } else {
            stack = new MyStack(capacityLimit);
            stack.push(value);
            set.add(stack);
        }
    }

    private MyStack getLastStack() {
        int stackIndex = set.size();
        if (stackIndex == 0)
            return null;
        else
            return set.get(set.size() - 1);
    }

    private void removeLastStack() {
        int stackIndex = set.size();
        if (stackIndex > 0)
            set.remove(stackIndex - 1);
    }

    int pop() {
        MyStack stack = getLastStack();
        if (stack == null)
            throw new RuntimeException("No elements in stack");
        int returnValue = stack.pop();
        if (stack.isEmpty())
            removeLastStack();
        return returnValue;
    }

    int popAt(int stackIndex) {
        if (stackIndex + 1 > set.size() )
            throw new RuntimeException("There is no such stack index");
        return removeAndShift(stackIndex, true);
    }

    int removeAndShift(int stackIndex, boolean removeTop) {
        MyStack stack = set.get(stackIndex);
        int removedElement;
        if (removeTop) {
            removedElement = stack.pop();
        } else {
            removedElement = stack.removeButtom();
        }
        if (stack.isEmpty()) {
            set.remove(stackIndex);
        } else if (stackIndex + 1 < set.size()){
            int shiftedValue = removeAndShift(stackIndex + 1, false);
            stack.push(shiftedValue);
        }
        return removedElement;
    }

    class MyStack {

        int capacityLimit;
        Node top, bottom;
        int size = 0;

        MyStack(int capacityLimit) {
            this.capacityLimit = capacityLimit;
        }

        void push(int value) {
            if (size >= capacityLimit)
                throw new RuntimeException("Capacity limit reached");
            Node newNode = new Node();
            newNode.value = value;
            size++;
            if (top == null) {
                top = newNode;
                bottom = newNode;
            } else {
                top.next = newNode;
                newNode.before = top;
                top = newNode;
            }
        }

        int pop() {
            if (top == null)
                throw new RuntimeException("No elements in stack");
            int valueToPop = top.value;
            top = top.before;
            size--;
            if (size == 0)
                bottom = null;
            return valueToPop;
        }

        boolean isFull() {
            return  size >= capacityLimit;
        }

        boolean isEmpty() {
            return size == 0;
        }

        int removeButtom() {
            if (bottom == null)
                throw new RuntimeException("No bottom element");
            Node b = bottom;
            bottom = bottom.next;
            if (bottom != null)
                bottom.before = null;
            size--;
            return b.value;
        }

        class Node {
            int value;
            Node next, before;

        }
    }

    public static void main(String[] args) {
        SetOfStacksRollover set = new SetOfStacksRollover(3);
        for (int i = 0; i < 10; i++)
            set.push(i);

        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());

        set.push(10);
        set.push(11);
        set.push(12);
        set.push(13);
        set.push(14);
        set.push(15);
        set.push(16);

        System.out.println("Pop 0 is " + set.popAt(0));
        System.out.println("Pop 0 is " + set.popAt(0));
        System.out.println("Pop 1 is " + set.popAt(1));
        System.out.println("Pop 2 is " + set.popAt(2));
        System.out.println("Pop 2 is " + set.popAt(2));
        System.out.println("Pop 1 is " + set.popAt(1));
    }
}
