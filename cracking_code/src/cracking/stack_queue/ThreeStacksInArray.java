package cracking.stack_queue;

/**
 * Created on 1/4/2017.
 */
public class ThreeStacksInArray {
    /**
     * This solution assumes that each stack is of the same length
     */

    int stackSize;
    int[] array;
    int[] pointers = new int[] {-1, -1, -1};

    ThreeStacksInArray(int arraySize) {
        if (arraySize <= 0)
            throw new RuntimeException("Array size must be greater than 0");

        array = new int[arraySize];
        stackSize = arraySize/3;
        for (int i = 0; i < pointers.length; i ++) {
            pointers[i] = i * stackSize;
        }
    }

    public void push(int stackNumber, int value) {
        if (stackNumber < 0 || stackNumber > 2)
            throw new RuntimeException("Invalid stack number");
        int nextStackPointer = pointers[stackNumber];
        int maxStackPointer = ((stackNumber + 1) * stackSize) - 1;
        if (nextStackPointer > maxStackPointer)
            throw new RuntimeException("No more space for new elements in stack " + stackNumber);

        array[nextStackPointer] = value;
        pointers[stackNumber]++;
    }

    public int pop(int stackNumber) {
        if (stackNumber < 0 || stackNumber > 2)
            throw new RuntimeException("Invalid stack number");
        int minStackPointer = (stackNumber * stackSize);
        if (pointers[stackNumber] - 1 < minStackPointer)
            throw new RuntimeException("No elements in stack");
        pointers[stackNumber]--;
        int value = array[pointers[stackNumber]];
        return  value;
    }

    public int peek(int stackNumber) {
        if (stackNumber < 0 || stackNumber > 2)
            throw new RuntimeException("Invalid stack number");
        int minStackPointer = (stackNumber * stackSize);
        if (pointers[stackNumber] - 1 < minStackPointer)
            throw new RuntimeException("No elements in stack");
        return  array[pointers[stackNumber] - 1];
    }

    public static void main(String[] args) {
        ThreeStacksInArray obj = new ThreeStacksInArray(30);
        obj.push(1, 200);
        obj.push(1, 201);
        obj.push(1, 202);
        obj.push(1, 203);
        obj.push(1, 204);
        obj.push(1, 205);
        obj.push(1, 206);
        obj.push(1, 207);
        obj.push(1, 208);
        obj.push(1, 209);

        obj.push(0, 100);
        obj.push(0, 101);
        obj.push(0, 102);
        obj.push(0, 103);

        obj.push(2, 301);
        obj.push(2, 302);
        obj.push(2, 303);
        obj.push(2, 304);

        obj.push(0, 104);

        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );
        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );
        System.out.println("Pop from stack # " + 1 + " = " + obj.pop(1) );
        System.out.println("Pop from stack # " + 2 + " = " + obj.pop(2) );


        obj.push(0, 105);
        obj.push(0, 106);
        System.out.println("Peek from stack # " + 0 + " = " + obj.peek(0) );
        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );
        System.out.println("Peek from stack # " + 0 + " = " + obj.peek(0) );

        System.out.println("Pop from stack # " + 1 + " = " + obj.pop(1) );
        System.out.println("Pop from stack # " + 1 + " = " + obj.pop(1) );

        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );
        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );
        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );
        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );
        System.out.println("Pop from stack # " + 0 + " = " + obj.pop(0) );

    }
}
