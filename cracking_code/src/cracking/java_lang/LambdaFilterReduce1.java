package cracking.java_lang;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LambdaFilterReduce1 {


    int getPopulation(List<Country> countries, String continent) {
        Stream<Country> countryStream = countries.stream().filter(s->s.getContinent().equals(continent));
        Stream<Integer> populations = countryStream.map(c->c.getPopulation());
        int populationSum = populations.reduce(0, (a, b) -> a + b);
        return populationSum;
    }

    public static void main(String[] args) {
        LambdaFilterReduce1 obj = new LambdaFilterReduce1();
        List<Country> countries = Arrays.asList( new Country[] {
                new Country("america", 10),
                new Country("america", 20),
                new Country("europe", 25),
                new Country("america", 12),
                new Country("america", 5),
                new Country("europe", 14)
        });
        System.out.println(obj.getPopulation(countries, "europe"));
        System.out.println(obj.getPopulation(countries, "america"));
    }
}

class Country {
    String continent;
    int population;

    Country(String cont, int pop) {
        continent = cont;
        population = pop;
    }

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }
}