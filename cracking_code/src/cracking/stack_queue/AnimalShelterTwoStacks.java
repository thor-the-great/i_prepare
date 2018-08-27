package cracking.stack_queue;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by thor on 1/5/17.
 */
public class AnimalShelterTwoStacks {
    int orderCount = 0;
    LinkedList<Animal> dogQueue = new LinkedList<>();
    LinkedList<Animal> catQueue = new LinkedList<>();

    void enqueue(Animal animal) {
        animal.order = orderCount;
        if (animal instanceof Dog) {
            dogQueue.add(animal);
        } else {
            catQueue.add(animal);
        }
        orderCount++;
    }

    Animal dequeueAny() {
        Animal oldestCat = catQueue.peekFirst();
        Animal oldestDog = dogQueue.peekFirst();

        if (oldestCat.order < oldestDog.order) {
            catQueue.removeFirst();
            return oldestCat;
        }
        else {
            dogQueue.removeFirst();
            return oldestDog;
        }
    }

    Animal dequeueDog() {
        if (!dogQueue.isEmpty())
            return dogQueue.poll();
        else
            return null;
    }

    Animal dequeueCat() {
        if (!catQueue.isEmpty())
            return catQueue.poll();
        else
            return null;
    }

    static abstract class Animal {
        int order;

        Animal(int order) {
            this.order = order;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Animal ").append(this.getClass().getSimpleName()).append(" with order ").append(order);
            return sb.toString();
        }
    }

    static class Dog extends Animal {
        Dog(int order) {
            super(order);
        }
    }

    static class Cat extends Animal {
        Cat(int order) {
            super(order);
        }
    }

    public static void main(String[] args) {
        AnimalShelterTwoStacks shelter = new AnimalShelterTwoStacks();
        shelter.enqueue(new Dog(0));
        shelter.enqueue(new Dog(1));
        shelter.enqueue(new Cat(2));
        shelter.enqueue(new Cat(3));
        shelter.enqueue(new Dog(4));
        shelter.enqueue(new Cat(5));
        shelter.enqueue(new Cat(6));
        shelter.enqueue(new Cat(7));
        shelter.enqueue(new Dog(8));
        shelter.enqueue(new Cat(9));
        shelter.enqueue(new Dog(10));


        System.out.println("Dequeue any is " + shelter.dequeueAny());
        System.out.println("Dequeue dog is " + shelter.dequeueDog());
        System.out.println("Dequeue dog is " + shelter.dequeueDog());
        System.out.println("Dequeue any is " + shelter.dequeueAny());
        System.out.println("Dequeue cat is " + shelter.dequeueCat());
        System.out.println("Dequeue dog is " + shelter.dequeueDog());
    }
}