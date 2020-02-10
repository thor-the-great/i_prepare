package random_problems;

/**
 * 1344. Angle Between Hands of a Clock
 * Difficulty: Medium
 * Given two numbers, hour and minutes. Return the smaller angle (in sexagesimal units) formed between the hour and
 * the minute hand.
 *
 * Example 1:
 *
 * Input: hour = 12, minutes = 30
 * Output: 165
 * Example 2:
 *
 * Input: hour = 3, minutes = 30
 * Output: 75
 * Example 3:
 *
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 * Example 4:
 *
 * Input: hour = 4, minutes = 50
 * Output: 155
 * Example 5:
 *
 * Input: hour = 12, minutes = 0
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= hour <= 12
 * 0 <= minutes <= 59
 * Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class AngleBetweenHandsOfClock {

    double hourDeg = 30.0, minDeg = 6.0, hourMidDeg = 0.5;

    /**
     * It has 30 for every hour, 6 for every minute, 0.5 for every minute hour hand moved between hours
     * @param hour
     * @param minutes
     * @return
     */
    public double angleClock(int hour, int minutes) {
        //angle for hour hand moved whole number of passed hours + part of the hour passed
        double hourHand = hourDeg*(hour) + (minutes*hourMidDeg);
        //angle for num of minutes hand moved
        double minHand = minDeg * minutes;
        //one possible angle - between greater and smaller angles
        double angle = Math.max(minHand, hourHand) - Math.min(minHand, hourHand);
        //pick between this angle or 360 - angle
        return Math.min(angle, 360 - angle);
    }
}
