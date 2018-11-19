package random_problems;

public class JewelsStones {
    public int numJewelsInStones(String J, String S) {
        long flag = 0;
        for (char c : J.toCharArray()) {
            int i = charToIndex(c);
            flag |= (1L << i);
        }

        int s = 0;
        for (char c : S.toCharArray()) {
            int i = charToIndex(c);
            if ((flag & (1L << i)) > 0 ) {
                s++;
            }
        }
        return s;
    }

    int charToIndex(char c) {
        if ('Z' >= c && c >= 'A')
            return c - 'A';
        else
            return c - 'a' + 26;
    }

    public static void main(String[] args) {
        JewelsStones obj = new JewelsStones();
        System.out.println(obj.numJewelsInStones("nEB", "jHMo"));
    }
}
