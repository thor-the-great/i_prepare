package queue_on_stacks;

import java.util.Stack;

/**
 * This problem was asked by Apple.
 *
 * Implement a queue using two stacks. Recall that a queue is a FIFO (first-in, first-out) data structure with the
 * following methods: enqueue, which inserts an element into the queue, and dequeue, which removes it.
 *
 * @param <T>
 */
public class QueueOnStacks<T> {
    //idea is following - make dequeue operation costly, enqueue - O(1). On enqueue we just add element to aux stack.
    //on dequeue we do following:
    //- if main stack is empty - move elements from aux to main. Because of double pop-push it will give FIFO order
    //- if main stack is not empty - means that there are elements from previous dequeue call, they are in FIFO order
    //  and we can just use that stack/order. We are not touching aux elements

    Stack<T> main;
    Stack<T> aux;

    QueueOnStacks() {
        main = new Stack<>();
        aux = new Stack<>();
    }

    public void enqueue(T newObject) {
        aux.push(newObject);
    }

    public T dequeue() {
        if (aux.isEmpty() && main.isEmpty()) {
            return null;
        }
        if (main.isEmpty()) {
            while (!aux.empty())
                main.push(aux.pop());
        }
        return main.pop();
    }

    public static void main(String[] args) {
        QueueOnStacks<Integer> queue = new QueueOnStacks<>();
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(20);
        queue.enqueue(21);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(22);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(23);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }
}
