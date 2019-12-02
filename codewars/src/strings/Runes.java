package strings;

import org.testng.annotations.AfterTest;

import static org.testng.AssertJUnit.assertEquals;

/**
 * To give credit where credit is due: This problem was taken from the ACMICPC-Northwest Regional Programming Contest.
 * Thank you problem writers.
 *
 * You are helping an archaeologist decipher some runes. He knows that this ancient society used a Base 10 system, and
 * that they never start a number with a leading zero. He's figured out most of the digits as well as a few operators,
 * but he needs your help to figure out the rest.
 *
 * The professor will give you a simple math expression, of the form
 *
 * [number][op][number]=[number]
 * He has converted all of the runes he knows into digits. The only operators he knows are addition (+),subtraction(-),
 * and multiplication (*), so those are the only ones that will appear. Each number will be in the range from -1000000
 * to 1000000, and will consist of only the digits 0-9, possibly a leading -, and maybe a few ?s. If there are ?s in
 * an expression, they represent a digit rune that the professor doesn't know (never an operator, and never a
 * leading -). All of the ?s in an expression will represent the same digit (0-9), and it won't be one of the other
 * given digits in the expression. No number will begin with a 0 unless the number itself is 0, therefore 00 would
 * not be a valid number.
 *
 * Given an expression, figure out the value of the rune represented by the question mark. If more than one digit
 * works, give the lowest one. If no digit works, well, that's bad news for the professor - it means that he's got
 * some of his runes wrong. output -1 in that case.
 *
 * Complete the method to solve the expression to find the value of the unknown rune. The method takes a string as a
 * paramater repressenting the expression and will return an int value representing the unknown rune or -1 if no such rune exists.
 */
public class Runes {

    public static int solveExpression( final String expression ) {
        int missingDigit = -1;

        boolean[] digs = new boolean[10];
        String op1 = "", op2 = "", res = "";
        char oper = '+';
        int operIdx = -1;
        //get 1 and 2 operands, result and operation. Also mark used digits
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch))
                digs[ch - '0'] = true;
            if (operIdx == -1 && i != 0 && (ch == '+' || ch == '-' || ch == '*')) {
                oper = ch;
                operIdx = i;
                op1 = expression.substring(0, i);
            } else if (ch == '=') {
                op2 = expression.substring(operIdx + 1, i);
                res = expression.substring(i + 1, expression.length());
            }
        }

        //now check every digit sarting from 0
        for (int i = 0; i <= 9; i++) {
            if (digs[i])
                continue;
            //zero needs special treatement
            if ( i == 0 && (!checkZero(op1) || !checkZero(op2) || !checkZero(res))) {
                continue;
            }
            //replace '?' with current digit and check numeric result
            int op1Int = Integer.parseInt(op1.replace('?', (char)('0' + i)));
            int op2Int = Integer.parseInt(op2.replace('?', (char)('0' + i)));
            int resInt = Integer.parseInt(res.replace('?', (char)('0' + i)));
            //if numeric result matches one after replacement - we found a solution
            if (resInt == calc(op1Int, op2Int, oper))
                return i;
        }

        return missingDigit;
    }

    static int calc(int i1, int i2, char op) {
        int res = 0;
        switch(op) {
            case '+':
                return i1 + i2;
            case '-':
                return i1 - i2;
            case '*':
                return i1 * i2;
        }

        return res;
    }

    static boolean checkZero(String num) {
        return !(num.charAt(0) == '?' && num.length() > 1);
    }
}

class RunesTest {

    @AfterTest
    public void testSample() {
        assertEquals( "Answer for expression '1+1=?' " , 2 , Runes.solveExpression("1+1=?") );
        assertEquals( "Answer for expression '123*45?=5?088' " , 6 , Runes.solveExpression("123*45?=5?088") );
        assertEquals( "Answer for expression '-5?*-1=5?' " , 0 , Runes.solveExpression("-5?*-1=5?") );
        assertEquals( "Answer for expression '19--45=5?' " , -1 , Runes.solveExpression("19--45=5?") );
        assertEquals( "Answer for expression '??*??=302?' " , 5 , Runes.solveExpression("??*??=302?") );
        assertEquals( "Answer for expression '?*11=??' " , 2 , Runes.solveExpression("?*11=??") );
        assertEquals( "Answer for expression '??*1=??' " , 2 , Runes.solveExpression("??*1=??") );
        assertEquals( "Answer for expression '??+??=??' " , -1 , Runes.solveExpression("??+??=??") );
    }

}
