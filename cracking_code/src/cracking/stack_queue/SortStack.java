package cracking.stack_queue;

import java.util.Stack;

/**
 * Created by Martin G on 1/5/2017.
 */
public class SortStack {

    Stack<Integer> sortStack(Stack<Integer> stackToSort) {
        Stack<Integer> axulluryStack = new Stack<>();
        while (!stackToSort.isEmpty()) {
            int elementToCompare = stackToSort.pop();
            while (!axulluryStack.isEmpty() && axulluryStack.peek() > elementToCompare) {
                stackToSort.push(axulluryStack.pop());
            }
            axulluryStack.push(elementToCompare);
        }
        return axulluryStack;
    }

    public static void main(String[] args) {
        Stack<Integer> stackToSort = new Stack<>();
        stackToSort.push(0);
        stackToSort.push(7);
        stackToSort.push(5);
        stackToSort.push(3);
        stackToSort.push(4);
        stackToSort.push(9);
        stackToSort.push(1);

        System.out.println("Original stack : " + stackToSort.toString());

        SortStack obj = new SortStack();
        System.out.println("Sorted stack : " + obj.sortStack(stackToSort).toString());
     }
}
