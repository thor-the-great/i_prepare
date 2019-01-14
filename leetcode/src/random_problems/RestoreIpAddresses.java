package random_problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * Medium
 *
 * Share
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIpAddresses {

    String s;
    List<String> res;
    int[] parts;

    public List<String> restoreIpAddresses(String s) {
        res = new LinkedList();
        this.s = s;
        parts = new int[4];
        helper(-1, 0, 0);
        return res;
    }

    void helper(int num, int nextIdx, int partIdx) {
        //base case, wraping up
        if (partIdx >= 4) {
            return;
        }
        if (nextIdx >= s.length()) {
            if (partIdx == 3 && num != -1) {
                parts[partIdx] = num;
                parseIp();
            }
            return;
        }
        int nextDigit = s.charAt(nextIdx) - '0';
        int possibleNum = num == -1 ? nextDigit : num * 10 + nextDigit;
        if (possibleNum <= 255 ) {
            //trying increase this position next (if it's not 0)
            if (possibleNum > 0 || (possibleNum == 0 && nextIdx == s.length() - 1))
                helper(possibleNum, nextIdx + 1, partIdx);
            //save for current position and try for next one from 0
            parts[partIdx] = possibleNum;
            helper(-1, nextIdx + 1, partIdx + 1);
            parts[partIdx] = -1;
        }
    }
    private void parseIp() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            if ( i < parts.length - 1)
                sb.append('.');
        }
        res.add(sb.toString());
    }

    public static void main(String[] args) {
        RestoreIpAddresses obj = new RestoreIpAddresses();
        List<String> res = obj.restoreIpAddresses("010010");
        res.forEach(ad->System.out.print(ad + ", "));
        res = obj.restoreIpAddresses("0000");
        res.forEach(ad->System.out.print(ad + ", "));
        res = obj.restoreIpAddresses("1111");
        res.forEach(ad->System.out.print(ad + ", "));
        res = obj.restoreIpAddresses("25525511135");
        res.forEach(ad->System.out.print(ad + ", "));
    }
}
