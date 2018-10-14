package cracking.moderate;

import java.util.Stack;

public class EnglishNums {

    String englishInt(int num) {
        String[] ones = new String[] {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = new String[] {"ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "8ty", "9ty"};
        String hundred = "hundred";
        String[] bigs = new String[] {"thousand", "million", "billion"};
        String negative = "negative";

        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            sb.append(ones[0]);
            return sb.toString();
        }

        if (num < 0)
            sb.append(negative).append(" ");

        Stack<String> stack = new Stack<>();

        int bigCounter = -1;
        while( num > 0) {
            int num1 = num % 1000;
            if (num1 != 0) {
                if (num1 / 100 > 0) {
                    sb.append(ones[num1 / 100]).append(" ").append(hundred).append(" ");
                }
                if (num1 % 100 > 0) {
                    if (num1 % 100 > 0 && num1 % 100 < 20) {
                        sb.append(ones[num1 % 100]).append(" ");
                    } else {
                        int ten = (num1 % 100) / 10;
                        sb.append(tens[ten - 1]).append(" ");
                        if (num1 % 10 > 0)
                            sb.append(ones[num1 % 10]).append(" ");
                    }
                }
                if (bigCounter >= 0) {
                    sb.append(bigs[bigCounter]).append(" ");
                }
            }
            if(sb.length() > 0)
                stack.push(sb.toString());
            num = num / 1000;
            bigCounter++;
            sb.setLength(0);
        }
        while(!stack.isEmpty())
            sb.append(stack.pop());

        return sb.toString();
    }

    public static void main(String[] args) {
        EnglishNums obj = new EnglishNums();
        System.out.println("---- num to english --------");
        System.out.println(obj.englishInt(1234));
        System.out.println(obj.englishInt(124000));
        System.out.println(obj.englishInt(19567234));
        System.out.println(obj.englishInt(1000000000));
    }
}
