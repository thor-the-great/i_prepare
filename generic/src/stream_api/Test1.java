package stream_api;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test1 {

    void doThing1() {
        List<Ship> list = DataFactory.getCollection1();

        System.out.println("List of names of fighter ships: ");

        List<String> fighterShipNames = list.stream()
                .filter(s->s.getType() == Type.FIGHTER)
                .map(Ship::getName)
                .collect(Collectors.toList());

        fighterShipNames.forEach(n->System.out.print(n + " "));

        System.out.println("\nList of ships with speed >= 100: ");
        Map<String, Integer> shipNameSpeed = list.stream()
                .filter(s->s.getSpeed() >= 100)
                .collect(Collectors.toMap(Ship::getName, Ship::getSpeed));

        for (String name : shipNameSpeed.keySet()) {
            System.out.println(name + " " + shipNameSpeed.get(name));
        }

        System.out.println("\nAverage speed for every type : ");
        Map<Type, Double> shipTypeAvgSpeed = list.stream()
                .collect(Collectors.groupingBy(
                        Ship::getType,
                        Collectors.averagingInt(Ship::getSpeed)
                ));
        for (Type type : shipTypeAvgSpeed.keySet()) {
            System.out.println(type.name() + " " + Math.round(shipTypeAvgSpeed.get(type)));
        }

        System.out.println("\nAverage speed between all ships : ");
        int[] speeds = list.stream()
                .mapToInt(Ship::getSpeed)
                .toArray();
        long avgSpeed = Math.round(Arrays.stream(speeds).average().getAsDouble());
        System.out.println(avgSpeed);

        int[] randArray = new Random().ints(10, 0, 1000)
                .toArray();
        System.out.println(Arrays.toString(randArray));
        System.out.println(Arrays.stream(randArray)
            .sum());
        System.out.println(Arrays.stream(randArray)
                .max().getAsInt());
        System.out.println(Arrays.stream(randArray)
                .average().getAsDouble());
        System.out.println(Arrays.toString(
                Arrays.stream(randArray)
                    .sorted()
                    .toArray()));
    }

    public static void main(String[] args) {
        Test1 obj = new Test1();
        obj.doThing1();
    }
}
