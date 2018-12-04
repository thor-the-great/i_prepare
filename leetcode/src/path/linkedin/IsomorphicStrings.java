package path.linkedin;

public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        int radix = 128;
        int[] c1 = new int[radix];
        int[] c2 = new int[radix];
        for (int i = 0; i < s.length(); i++) {
            if (c1[s.charAt(i)] != c2[t.charAt(i)]) return false;
            c1[s.charAt(i)] = i + 1;
            c2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings obj = new IsomorphicStrings();
        System.out.println(obj.isIsomorphic("ab", "aa"));
    }
}
