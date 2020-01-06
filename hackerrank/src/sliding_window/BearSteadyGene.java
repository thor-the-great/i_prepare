package sliding_window;

public class BearSteadyGene {

    static int steadyGene(String gene) {
        //A,C,T,G
        int N = gene.length();
        int n = N / 4;
        int[] count = new int[26];
        for (char ch : gene.toCharArray()) {
            count[ch - 'A']++;
        }
        int c = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > n) {
                count[i] -= n;
                c++;
            }
            else
                count[i] = 0;
        }

        if (c == 0)
            return 0;

        int cc = 0, res = Integer.MAX_VALUE, l = 0;
        int[] ccount = new int[26];
        for (int i = 0; i < N; i++) {
            int idx = gene.charAt(i) - 'A';
            ccount[idx]++;
            if (ccount[idx] == count[idx])
                ++cc;
            while (l < i && cc == c) {
                res = Math.min(res, i - l + 1);
                int idx2 = gene.charAt(l) - 'A';
                ccount[idx2]--;
                if (count[idx2] > 0  && ccount[idx2] < count[idx2])
                    --cc;
                ++l;
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
