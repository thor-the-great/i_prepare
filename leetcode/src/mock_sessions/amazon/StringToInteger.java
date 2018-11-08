package mock_sessions.amazon;

public class StringToInteger {
    public int myAtoi(String str) {
        int start = 0;
        int N = str.length();
        if (N == 0) return 0;
        while(start < N && str.charAt(start) == ' ') {
            start++;
        }
        if ( start == N || !Character.isDigit(str.charAt(start)) &&
                '-' != str.charAt(start) && '+' != str.charAt(start))
            return 0;

        char firstChar = str.charAt(start);
        boolean isNegative = false;
        if (!Character.isDigit(firstChar)) {
            isNegative = '-' == str.charAt(start);
            start++;
        }

        long num = 0;
        while (start < N && Character.isDigit(str.charAt(start))){
            num = (10 * num) + (str.charAt(start) - '0');
            if (num > Integer.MAX_VALUE)
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            start++;
        }
        return isNegative ? - (int) num : (int) num;
    }

    public static void main(String[] args) {
        StringToInteger obj = new StringToInteger();
        System.out.println(obj.myAtoi("-91283472332"));
    }
}
