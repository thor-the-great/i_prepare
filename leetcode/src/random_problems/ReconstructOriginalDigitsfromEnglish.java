package random_problems;

public class ReconstructOriginalDigitsfromEnglish {

    /**
     * Several digit words can be uniquely identified by one letter (e.g. z for zero),
     * so count those first. Then the rest can be identified as two or three or four. For 
     * those digits pairs identified on first step so the other one identified as sum - pair element.
     * 
     * Do it for every digits
     * 
     * O(n) time, O(1) space 
     * 
     * https://leetcode.com/problems/reconstruct-original-digits-from-english/
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        int[] digits = new int[10];
        
        for (char ch : s.toCharArray()) {
            if (ch == 'z') digits[0]++; //exact 0
            if (ch == 'w') digits[2]++; //exact 2
            if (ch == 'u') digits[4]++; //exact 4
            if (ch == 'x') digits[6]++; //exact 6
            if (ch == 'g') digits[8]++; //exact 8

            if (ch == 'r') { //zero, four or three
                digits[3]++;
            }
            if (ch == 'v') {//five or seven
                digits[7]++;
            }
            if (ch == 'f') { // four or five
                digits[5]++;
            }
            if (ch == 'i') { // five, six, eight or nine
                digits[9]++;
            }
            if (ch == 'o') {  //zero, one, two or four
                digits[1]++;   
            }
        }
        digits[3]-=(digits[4] + digits[0]);
        digits[5]-=digits[4];
        digits[7]-=digits[5];
        digits[9]-=(digits[5] + digits[6] + digits[8]);
        digits[1]-=(digits[0] + digits[2] + digits[4]);
    
        StringBuilder sb = new StringBuilder();
        for (int d = 0; d <= 9; d++) {
            for (int i = 0; i < digits[d]; i++) {
                sb.append(d);
            }
        }
        return sb.toString();
    }    
}
