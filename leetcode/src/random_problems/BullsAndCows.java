package random_problems;

/**
 * 299. Bulls and Cows
 * Easy
 *
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to
 * guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits
 * in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits
 * match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses
 * and hints to eventually derive the secret number.
 *
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls
 * and B to indicate the cows.
 *
 * Please note that both secret number and friend's guess may contain duplicate digits.
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 *
 * Output: "1A3B"
 *
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 * Example 2:
 *
 * Input: secret = "1123", guess = "0111"
 *
 * Output: "1A1B"
 *
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are
 * always equal.
 */
public class BullsAndCows {

    /**
     * Scan both strings once, if same char is on the same position - add it to bulls. If not - add it's count to
     * array of secrets amd guesses respectively. After that iterate over secrets amd guesses array and get the min
     * from both for every index
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int[] secrets = new int[10];
        int[] guesses = new int[10];
        int bulls = 0, cows = 0;

        for (int i = 0; i < secret.length(); i++) {
            int sD = secret.charAt(i) - '0', gD = guess.charAt(i) - '0';
            if (sD == gD) {
                ++bulls;
                continue;
            }
            ++secrets[sD]; ++guesses[gD];
        }

        for (int i = 0; i < 10; i++) {
            cows += Math.min(secrets[i], guesses[i]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(bulls).append('A');
        sb.append(cows).append('B');
        return sb.toString();
    }
}
