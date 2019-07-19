/**
 * Check balanced parenth RECURSIVELY !!!!! :(
 */
public class CheckBalancedParenthesis {

    /**
     *
     * @param input
     * @return
     */
    public static boolean isBalanced(String input) {

        if (input == null || input.length() == 0)
            return true;

        char s = input.charAt(0);
        if (s == '(' || s== '[' || s == '{' ) {
            char e = getPair(s);
            int idx = -1;
            for (int i = input.length() - 1; i > 0; i--) {
                if (input.charAt(i) == e) {
                    idx = i;
                    break;
                }
            }
            if (idx == -1)
                return false;

            return isBalanced(input.substring(1, idx)) && isBalanced(input.substring(idx + 1, input.length()));
        }
        return false;
    }

    static char getPair(char b) {
        if (b == '(')
            return ')';
        if (b == '[')
            return ']';
        if (b == '{')
            return '}';
        return '*';
    }
}
