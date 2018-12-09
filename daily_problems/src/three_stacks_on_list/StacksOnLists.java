package three_stacks_on_list;

/**
 * This problem was asked by Microsoft.
 *
 * Implement 3 stacks using a single list:
 *
 * class Stack:
 *     def __init__(self):
 *         self.list = []
 *
 *     def pop(self, stack_number):
 *         pass
 *
 *     def push(self, item, stack_number):
 *         pass
 */
public class StacksOnLists {
    Node[][] headsTails;
    StacksOnLists() {

        headsTails = new Node[2][3];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                headsTails[i][j] = new Node(Integer.MIN_VALUE);
            }
        }

        for (int j = 0; j < 3; j++) {
            headsTails[0][j].next = headsTails[1][j];
            headsTails[1][j].prev = headsTails[0][j];
            if (j != 2)
                headsTails[1][j].next = headsTails[0][j + 1];
        }
    }

    int pop(int stack) {
        Node tail = headsTails[1][stack];
        if (tail.prev.val == Integer.MIN_VALUE)
            return -1;
        Node pop = tail.prev;
        tail.prev = pop.prev;
        pop.prev.next = tail;
        return pop.val;
    }

    void push(int stack, int el) {
        Node tail = headsTails[1][stack];
        Node newNode = new Node(el);
        tail.prev.next = newNode;
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev = newNode;
    }

    public static void main(String[] args) {
        StacksOnLists obj = new StacksOnLists();
        System.out.println(obj.pop(0));//-1
        System.out.println(obj.pop(2));//-1
        obj.push(1, 11);
        obj.push(1, 12);
        obj.push(2, 21);
        System.out.println(obj.pop(2));//21
        System.out.println(obj.pop(2));//-1
        obj.push(0, 1);
        obj.push(0, 2);
        obj.push(0, 3);
        obj.push(2, 22);
        obj.push(2, 23);
        obj.push(2, 24);
        System.out.println(obj.pop(1));//12
        System.out.println(obj.pop(2));//24
        System.out.println(obj.pop(2));//23
        System.out.println(obj.pop(1));//11
        System.out.println(obj.pop(1));//-1
        System.out.println(obj.pop(1));//-1
    }
}

class Node {
    Node prev, next;
    int val;

    Node (int val) {
        this.val = val;
    }
}
