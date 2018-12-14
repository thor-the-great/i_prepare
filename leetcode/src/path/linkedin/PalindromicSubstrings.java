package path.linkedin;

public class PalindromicSubstrings {

    String s;
    public int countSubstrings(String s) {
        int c = 0;
        int N = s.length();
        this.s = s;
        for (int i = 0; i < N; i++) {
            c += check(i, i);
            if (i > 0 )
                c+=check(i-1, i);
        }
        return c;
    }

    int check(int n1, int n2) {
        int c = 0;
        while (n1 >= 0 && n2 < s.length()) {
            if (s.charAt(n1) == s.charAt(n2))
                c++;
            else {
                return c;
            }
            n1--;
            n2++;
        }
        return c;
    }

    public static void main(String[] args) {
        PalindromicSubstrings obj = new PalindromicSubstrings();
        //System.out.println(obj.countSubstrings("aaab"));

        System.out.println(obj.countSubstrings("abc"));

        System.out.println(obj.countSubstrings("aaa"));
    }
}
