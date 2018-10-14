package cracking.moderate;

import java.util.Arrays;

public class MostLivingPeople {

    int mostLiving(Person[] people) {
        int[] birthSorted = getSortedYears(people, true);
        int[] deathSorted = getSortedYears(people, false);

        int year = birthSorted[0];
        int maxYear = 0;
        int runMax = 0;
        int b = 0, d =0;

        while (b < birthSorted.length) {
            if (birthSorted[b] <= deathSorted[d]) {
                runMax++;
                if (runMax > maxYear) {
                    maxYear = runMax;
                    year = birthSorted[b];
                }
                b++;
            } else {
                runMax--;
                d++;
            }
        }
        return year;
    }

    int[] getSortedYears(Person[] people, boolean isBirth) {
        int[] years = new int[people.length];
        for(int i = 0; i < people.length; i++) {
            years[i] = isBirth ? people[i].birth : people[i].death;
        }
        Arrays.sort(years);
        return years;
    }

    public static void main(String[] args) {
        MostLivingPeople obj = new MostLivingPeople();
        Person[] p = new Person[] {
                new Person(1905, 1920),
                new Person(1910, 1940),
                new Person(1915, 1970),
                new Person(1940, 1995),
                new Person(1941, 1999),
                new Person(1912, 1998),
                new Person(1901, 1912),
                new Person(1902, 1902),
                new Person(1910, 1990),
                new Person(1960, 1999),
                new Person(1962, 1990),
                new Person(1970, 1990),
                new Person(1969, 1991),
                new Person(1972, 1999),
                new Person(1974, 1980),
                new Person(1950, 1972),
                new Person(1965, 1970),
                new Person(1960, 1968),
                new Person(1955, 1967)
        };
        System.out.println(obj.mostLiving(p));
    }

    static class Person {
        int birth;
        int death;

        Person(int b, int d) {
            this.birth = b;
            this.death = d;
        }
    }
}
