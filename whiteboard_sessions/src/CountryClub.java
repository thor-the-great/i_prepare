import java.util.*;

public class CountryClub {

    int maxTeams(int K, String[] countries) {
        Comparator<Key> keyComparator = (k1, k2) -> {
            if (k2.count != k1.count)
                return Integer.compare(k2.count, k1.count);
            else
                return k2.country.compareTo(k1.country);
        };
        Map<Key, Key> keys = new TreeMap<>(keyComparator);
        //Map<Key, Key> keys = new TreeMap<>();

        Map<String, Integer> m = new HashMap();
        for (String c : countries) {
            if (m.containsKey(c)) {
                m.put(c, m.get(c) + 1);
            } else {
                m.put(c, 1);
            }
        }
        for (String country : m.keySet()) {
            Key newKey = new Key(country, m.get(country));
            keys.put(newKey, newKey);
        }

        int res = 0;
        while(m.size() >= K) {
            res++;
            Iterator<Key> highKeysIt = keys.keySet().iterator();
            Set<String> team = new HashSet();
            for (int i = 0; i < K; i++) {
                team.add(highKeysIt.next().country);
            }

            for (String t : team) {
                if (m.get(t) == 1)
                    m.remove(t);
                else
                    m.put(t, m.get(t) - 1);
            }

            highKeysIt = keys.keySet().iterator();
            for (int i = 0; i < K; i++) {
                Key k = highKeysIt.next();
                if (k.count > 1) {
                    k.count--;
                    //keys.put(k, k);
                }
                else
                    highKeysIt.remove();
            }
        }
        return res;
    }

    class Key {
        String country;
        int count;

        Key(String c, int i) {
            country = c;
            count = i;
        }

        @Override
        public int hashCode() {
            return country.hashCode() ^ 31 + count;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                return ((Key) obj).count == this.count && ((Key) obj).country.equals(this.country);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        CountryClub obj = new CountryClub();

        String[] countries;
        int K;

        countries = new String[] {"UA", "RU", "US", "UA", "RU", "RU", "RU"};
        K = 2;
        System.out.println(obj.maxTeams(K, countries));

        countries = new String[] {"UA", "RU", "US", "UA", "RU", "RU", "US"};
        K = 2;
        System.out.println(obj.maxTeams(K, countries));

        countries = new String[] {"UA", "RU", "US", "UA", "RU", "RU", "US", "RU"};
        K = 2;
        System.out.println(obj.maxTeams(K, countries));

        countries = new String[] {"UA", "RU", "US", "UA", "RU", "RU", "RU"};
        K = 3;
        System.out.println(obj.maxTeams(K, countries));
    }
}
