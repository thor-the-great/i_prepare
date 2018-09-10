package cracking.recursion_dp;

public class Coins {

    long coins(int n) {
        int[] noms = new int[] {25, 10 , 5, 1};
        long[][] map = new long[n + 1][noms.length];
        return countCounts(noms, 0, n, map);
    }

    long countCounts(int[] noms, int index, int remainder, long[][] map) {
        if (map[remainder][index] > 0)
            return map[remainder][index];

        if (index == noms.length - 1)
            return remainder > 0 ? 1 : 0;

        int i = remainder / noms[index];
        long count = 0;
        for(int j = 0; j <= i; j++) {
            int b = remainder - (j * noms[index]);
            if (b > 0)
                count += countCounts(noms, index + 1, b, map);
            else
                count++;
        }
        map[remainder][index] = count;
        return count;
    }

    public static void main(String[] args) {
        Coins obj = new Coins();
        long start = System.currentTimeMillis();
        System.out.println(obj.coins(50000));
        System.out.println("time elapsed " + (System.currentTimeMillis() - start) + " mlsec");
    }
}
