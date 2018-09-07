package cracking.recursion_dp;

import java.util.Stack;

/**
 * Towers of Hanoi
 */
public class TowersOfHanoi {

    public static void main(String[] args) {
        Tower[] towers = new Tower[3];
        for (int i = 0; i < towers.length; i++) {
            towers[i] = new Tower(i);
        }
        int N = 3;
        for (int i = N - 1; i >= 0; i--) {
            towers[0].add(i);
        }
        System.out.println("--- start game -----");
        towers[0].moveDisks(N, towers[2], towers[1]);
    }
}
class Tower {
    Stack<Integer> disks;
    int index;

    Tower(int index) {
        this.index = index;
        disks = new Stack<>();
    }

    void add(int disk) {
        if (!disks.isEmpty() && disk > disks.peek()) {
            throw new RuntimeException("Disk must be smaller then the current top");
        }
        disks.push(disk);
    }

    void moveTopToOtherTower(Tower t) {
        if (!disks.isEmpty()) {
            int disk = this.disks.pop();
            t.add(disk);
            System.out.println("moveTopToOtherTower " + disk + " from " + this.index + " to " + t.index);
        }
    }

    void moveDisks(int diskCount, Tower destination, Tower buffer) {
        if (diskCount > 0) {
            moveDisks(diskCount - 1, buffer, destination);
            moveTopToOtherTower(destination);
            buffer.moveDisks(diskCount - 1, destination, this);
        }
    }
}
