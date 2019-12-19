/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package challenges;

/**
 * https://www.hackerrank.com/challenges/time-conversion/problem
 *
 * Given a time in -hour AM/PM format, convert it to military (24-hour) time.
 *
 * Note: Midnight is 12:00:00AM on a 12-hour clock, and 00:00:00 on a 24-hour clock. Noon is
 * 12:00:00PM on a 12-hour clock, and 12:00:00 on a 24-hour clock.
 *
 * Function Description
 *
 * Complete the timeConversion function in the editor below. It should return a new string
 * representing the input time in 24 hour format.
 *
 * timeConversion has the following parameter(s):
 *
 * s: a string representing time in  hour format
 * Input Format
 *
 * A single string  containing a time in -hour clock format (i.e.:  or ), where  and .
 *
 * Constraints
 *
 * All input times are valid
 * Output Format
 *
 * Convert and print the given time in -hour format, where .
 *
 * Sample Input 0
 *
 * 07:05:45PM
 * Sample Output 0
 *
 * 19:05:45
 */
public class TimeConversion {

    static String timeConversion(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isPM = s.charAt(8) == 'P';
        Integer hours = Integer.parseInt(s.substring(0, 2));
        if (!isPM && hours == 12)
            hours = 0;
        else if (isPM && hours == 12)
            hours = 12;
        else if (isPM)
            hours+=12;

        if (hours < 10)
            sb.append('0');

        sb.append(hours).append(':');
        sb.append(s.substring(3, 5)).append(':').append(s.substring(6, 8));
        return sb.toString();
    }
}
