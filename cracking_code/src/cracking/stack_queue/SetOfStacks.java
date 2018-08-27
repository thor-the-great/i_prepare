package cracking.stack_queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thor on 1/4/17.
 */
public class SetOfStacks {

    int capacityLimit = 5;
    List<Node> set = new ArrayList();

    void push(int value)
    {
        int stackIndex = set.size();
        Node nextNode = new Node();
        nextNode.value = value;
        if (stackIndex == 0) {
            set.add(nextNode);
        } else {
            Node nextStackNode = set.get(stackIndex - 1);
            int count = 1;
            while (nextStackNode.nextNode != null) {
                nextStackNode = nextStackNode.nextNode;
                count++;
            }
            if (count < capacityLimit) {
                nextStackNode.nextNode = nextNode;
            } else {
                set.add(nextNode);
            }
        }
    }

    int pop() {
        int stackIndex = set.size();
        if (stackIndex == 0)
            throw new RuntimeException("Stack is empty");
        Node lastStack = set.get(set.size() - 1);
        Node prevStack = null;
        int count = 1;
        while (lastStack.nextNode != null) {
            prevStack = lastStack;
            lastStack = lastStack.nextNode;
            count++;
        }
        int returnValue = lastStack.value;
        if (count == 1)
            set.remove(set.size() - 1);
        else if (prevStack != null) {
            prevStack.nextNode = null;
        }
        return returnValue;
    }

    int popAt(int stackIndex) {
        if (set.size() == 0)
            throw new RuntimeException("Stack is empty");
        if (set.size() - 1 < stackIndex)
            throw new RuntimeException("Wrong stack index");
        Node lastStack = set.get(stackIndex);
        if (lastStack == null)
            throw new RuntimeException("Stack is empty");
        Node prevStack = null;
        int count = 1;
        while (lastStack.nextNode != null) {
            prevStack = lastStack;
            lastStack = lastStack.nextNode;
            count++;
        }
        int returnValue = lastStack.value;
        if (count == 1)
            set.remove(set.size() - 1);
        else if (prevStack != null) {
            prevStack.nextNode = null;
        }
        return returnValue;
    }

    class Node {
        int value;
        Node nextNode;
    }

    public static void main(String[] args) {
        SetOfStacks set = new SetOfStacks();
        set.push(1);
        set.push(2);
        set.push(3);
        set.push(4);
        set.push(5);
        set.push(6);
        set.push(7);

        System.out.println("Set is full");

        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());

        set.push(8);
        set.push(9);

        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());
        System.out.println("Pop is " + set.pop());
        System.out.println("-------------------");
        set = new SetOfStacks();
        set.push(1);
        set.push(2);
        set.push(3);
        set.push(4);
        set.push(5);
        set.push(6);
        set.push(7);

        System.out.println("Pop 0 is " + set.popAt(0));
        System.out.println("Pop 0 is " + set.popAt(0));
        System.out.println("Pop 1 is " + set.popAt(1));
        System.out.println("Pop 1 is " + set.popAt(1));


        set.push(8);
        set.push(9);
        set.push(10);
        set.push(11);

        System.out.println("Pop 1 is " + set.popAt(1));
        System.out.println("Pop 0 is " + set.popAt(0));
        System.out.println("Pop 1 is " + set.popAt(1));
    }
}
