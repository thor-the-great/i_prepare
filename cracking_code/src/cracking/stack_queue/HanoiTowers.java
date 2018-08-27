package cracking.stack_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by I057833 on 1/5/2017.
 */
public class HanoiTowers {


    static class Tower {

        int index;
        Stack<Integer> disks;

        Tower(int index) {
            this.index = index;
            disks = new Stack<>();
        }

        void addDisk(int i) {
            if (!disks.isEmpty() && disks.peek() <= i)
                throw new RuntimeException("Can't place disk " + i + " at tower " + index);
            disks.push(i);
        }

        void moveTopToTower( Tower dest) {
            int top = this.disks.pop();
            dest.addDisk(top);
            System.out.println( "Move disk " + top + " from tower " + this.index + " to tower " + dest.index);
        }

        void moveDisks(int numberOfDisks, Tower destination, Tower buffer) {
            if (numberOfDisks > 0) {
                moveDisks(numberOfDisks - 1, buffer, destination);
                moveTopToTower(destination);
                buffer.moveDisks( numberOfDisks - 1, destination, this);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Tower disks are : ");
            sb.append(disks.toString());
            return sb.toString();
        }
    }

    public static  void main(String[] args) {
        int N = 5;
        List<Tower> towers = new ArrayList();
        for (int i = 0; i < 3; i++) {
            towers.add(new Tower(i));
        }

        for (int i = N - 1; i >= 0 ; i--) {
            towers.get(0).addDisk(i);
        }
        System.out.println("Initial state");
        towers.forEach(tower -> System.out.println(tower.toString()));

        towers.get(0).moveDisks(N, towers.get(2), towers.get(1));

        System.out.println("Final state");
        towers.forEach(tower -> System.out.println(tower.toString()));    }
}
