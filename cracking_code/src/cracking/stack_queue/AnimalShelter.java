package cracking.stack_queue;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by thor on 1/5/17.
 */
public class AnimalShelter {

    LinkedList<Animal> queue = new LinkedList<>();

    void enqueue(Animal animal) {
        queue.add(animal);
    }

    Animal dequeueAny() {
        if (!queue.isEmpty()) {
            Animal oldestAnimal = queue.removeFirst();
            return  oldestAnimal;
        } else
            return null;
    }

    Animal dequeueDog() {
        return getAnimal(true);
    }

    private Animal getAnimal(boolean keyAttribute) {
        if (queue.isEmpty())
            return null;
        Iterator<Animal> it = queue.iterator();
        boolean isElementFound = false;
        while(it.hasNext() && !isElementFound) {
            Animal nextAnimal = it.next();
            if (keyAttribute == nextAnimal.isDog) {
                it.remove();
                return nextAnimal;
            }
        }
        return null;
    }

    Animal dequeueCat() {
        return getAnimal(false);
    }

    static class Animal {
        int index;
        boolean isDog;

        Animal(int index, boolean isDog) {
            this.index = index;
            this.isDog = isDog;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Animal ").append(isDog ? "dog" : "cat").append(" with index ").append(index);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(new Animal(0,true));
        shelter.enqueue(new Animal(1, true));
        shelter.enqueue(new Animal(2, false));
        shelter.enqueue(new Animal(3, false));
        shelter.enqueue(new Animal(4, true));
        shelter.enqueue(new Animal(5, false));
        shelter.enqueue(new Animal(6, false));
        shelter.enqueue(new Animal(7, false));
        shelter.enqueue(new Animal(8, true));
        shelter.enqueue(new Animal(9, false));
        shelter.enqueue(new Animal(10, true));


        System.out.println("Dequeue any is " + shelter.dequeueAny());
        System.out.println("Dequeue dog is " + shelter.dequeueDog());
        System.out.println("Dequeue dog is " + shelter.dequeueDog());
        System.out.println("Dequeue any is " + shelter.dequeueAny());
        System.out.println("Dequeue cat is " + shelter.dequeueCat());
        System.out.println("Dequeue dog is " + shelter.dequeueDog());
    }
}
