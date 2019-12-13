package random_problems;

/**
 * 1271. Hexspeak
 * Difficulty: Easy
 * A decimal number can be converted to its Hexspeak representation by first converting it to an uppercase hexadecimal
 * string, then replacing all occurrences of the digit 0 with the letter O, and the digit 1 with the letter I.
 * Such a representation is valid if and only if it consists only of the letters in the set {"A", "B", "C", "D", "E", "F", "I", "O"}.
 *
 * Given a string num representing a decimal integer N, return the Hexspeak representation of N if it is valid,
 * otherwise return "ERROR".
 *
 *
 *
 * Example 1:
 *
 * Input: num = "257"
 * Output: "IOI"
 * Explanation:  257 is 101 in hexadecimal.
 * Example 2:
 *
 * Input: num = "3"
 * Output: "ERROR"
 *
 *
 * Constraints:
 *
 * 1 <= N <= 10^12
 * There are no leading zeros in the given string.
 * All answers must be in uppercase letters.
 */
public class Hexspeak {

    public String toHexspeak(String num) {
        StringBuilder sb = new StringBuilder();
        long N = Long.parseLong(num);
        while( N > 0 ) {
            int d = (int)(N % 16);
            if (d > 1 && d < 10)
                return "ERROR";

            sb.append(getChar(d));
            N/=16;
        }

        return sb.reverse().toString();
    }

    char getChar(int d) {
        switch(d) {
            case 0  : return 'O';
            case 1  : return 'I';
            case 10 : return 'A';
            case 11 : return 'B';
            case 12 : return 'C';
            case 13 : return 'D';
            case 14 : return 'E';
            case 15 : return 'F';
        }
        return '-';
    }
}
