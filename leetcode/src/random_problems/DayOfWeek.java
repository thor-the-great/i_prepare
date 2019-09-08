package random_problems;

/**
 * 1185. Day of the Week
 * Easy
 *
 * Given a date, return the corresponding day of the week for that date.
 *
 * The input is given as three integers representing the day, month and year respectively.
 *
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 *
 *
 *
 * Example 1:
 *
 * Input: day = 31, month = 8, year = 2019
 * Output: "Saturday"
 * Example 2:
 *
 * Input: day = 18, month = 7, year = 1999
 * Output: "Sunday"
 * Example 3:
 *
 * Input: day = 15, month = 8, year = 1993
 * Output: "Sunday"
 *
 *
 * Constraints:
 *
 * The given dates are valid dates between the years 1971 and 2100.
 */
public class DayOfWeek {

    String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    /**
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public String dayOfTheWeek(int day, int month, int year) {
        //num of days in non-leap year
        int[] mDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //calculate the diff in years including leap years
        int yearsDif = year - 1971;
        boolean isLeap = year % 4 == 0;
        int numOfLeaps = (year/4) - (1971/4) - (isLeap ? 1 : 0);
        int dif = yearsDif * 365 + numOfLeaps;
        //if this year is a leap year - add one more day to Feb
        if (isLeap)
            mDays[1]++;
        //accumulate number of days as per month diff
        for (int m = 1; m < month; m++)
            dif+=mDays[m - 1];
        //diff in days we can add directly
        dif+=day - 1 ;
        //now calcualte the index of the day
        return days[(5 + dif) % 7];
    }
}
