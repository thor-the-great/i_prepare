import java.util.ArrayList;
import java.util.List;

/**
 * Recovering IPv4 Addresses New
 *     DFS
 * You are given a String containing at least 4 numbers that represented an IPv4 Address, but the separator data - i.e.
 * the dots that separate each Byte in a 4 Byte Ipv4 address, has been lost. Write a method - generateIPAddrs that
 * takes in this String and returns an ArrayList of Strings containing all possible IPv4 Addresses that can be
 * generated from the given sequence of decimal integers.
 *
 *
 * Note:
 *
 * - The IP Addresses for this problem are written in the decimal dot notation.
 *  - You must use all the digits in the input String
 *  - The order in which the IP Addresses are returned does not matter
 *  - 0.0.0.1 and 0.0.0.01 may be considered 2 distinct possibilities. i.e. do not ignore leading or trailing 0s.
 *
 * Examples:
 *
 * generateIPAddrs("1001") ==> {"1.0.0.1"}
 *
 * generateIPAddrs("1010") ==> {"1.0.1.0"}
 *
 * generateIPAddrs("25525511135") ==> {"255.255.11.135", "255.255.111.35"}
 */
public class RestoreIPAddress {

    public static ArrayList<String> generateIPAddrs(String s) {
        ArrayList<String> res = new ArrayList();

        helper(res, 0, s, new ArrayList());

        return res;
    }

    static void helper(ArrayList<String> res, int idx, String s, List<String> add) {
        if (idx >= s.length() || add.size() > 4) {
            if (add.size() == 4) {
                StringBuilder sb = new StringBuilder();
                sb.append(add.get(0)).append('.')
                        .append(add.get(1)).append('.')
                        .append(add.get(2)).append('.')
                        .append(add.get(3));
                res.add(sb.toString());
            }
            return;
        }

        char nextD = s.charAt(idx);
        //if (nextD > 255)
        //    return;
        if (add.isEmpty()) {
            add.add(""+nextD);
            helper(res, idx + 1, s, add);
        } else {
            String lastNum = add.get(add.size() - 1);
            //adding it as a new num
            add.add(""+nextD);
            helper(res, idx + 1, s, add);
            add.remove(add.size() - 1);
            //or adding it to existing num
            if (lastNum.length() < 3) {
                if (Integer.parseInt(lastNum + nextD) <= 255) {
                    String t = lastNum;
                    add.set(add.size() - 1, lastNum + nextD);
                    helper(res, idx + 1, s, add);
                    add.set(add.size() - 1, t);
                }
            }
        }
    }

    public static void main(String[] args) {

        //System.out.println((generateIPAddrs("25525511135")).toString());

        //System.out.println(generateIPAddrs("2551025510").toString());

        System.out.println(generateIPAddrs("1921682040").toString());
    }
}
