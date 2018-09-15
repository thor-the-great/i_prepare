package cracking.moderate;

import java.util.Arrays;

public class SmallestDifference {

    int[] smallestDiff(int[] arr1, int[] arr2) {
        int[] s = arr1, b = arr2, tmp, res = new int[2];
        Arrays.sort(s);
        Arrays.sort(b);
        int ps = 0, pb = 0, ptmp;
        int dif = Integer.MAX_VALUE;
        while(ps < s.length) {
            if (s[ps] > b[pb]) { //flip
                tmp = s;
                s = b;
                b = tmp;
                ptmp = ps;
                ps = pb;
                pb = ptmp;
            }
            int newDif = b[pb] - s[ps];
            if(dif > newDif) {
                dif = newDif;
                res[0] = s[ps];
                res[1] = b[pb];
            }
            ps++;
        }
        return res;
    }

    public static void main(String[] args) {
        SmallestDifference obj = new SmallestDifference();

        int[] arr1, arr2, res;

        arr1 = new int[] {1, 3, 15, 11, 2};
        arr2 = new int[] {23, 127, 235, 19, 8};

        res = obj.smallestDiff(arr1, arr2);
        System.out.println("[ " + res[0] +", " + res[1] + " ]");

        arr1 = new int[] {5, 80, 15, 11, 3, 40, 67};
        arr2 = new int[] {2, 45, 20, 19, 8};

        res = obj.smallestDiff(arr1, arr2);
        System.out.println("[ " + res[0] +", " + res[1] + " ]");
    }
}
