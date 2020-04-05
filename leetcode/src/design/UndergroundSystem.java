package design;

import java.util.HashMap;
import java.util.Map;

/**
 * 1396. Design Underground System
 * Medium
 *
 * Implement the class UndergroundSystem that supports three methods:
 *
 * 1. checkIn(int id, string stationName, int t)
 *
 * A customer with id card equal to id, gets in the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * 2. checkOut(int id, string stationName, int t)
 *
 * A customer with id card equal to id, gets out from the station stationName at time t.
 * 3. getAverageTime(string startStation, string endStation)
 *
 * Returns the average time to travel between the startStation and the endStation.
 * The average time is computed from all the previous traveling from startStation to endStation that
 * happened directly.
 * Call to getAverageTime is always valid.
 * You can assume all calls to checkIn and checkOut methods are consistent. That is, if a customer gets
 * in at time t1 at some station, then it gets out at time t2 with t2 > t1. All events happen in
 * chronological order.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime",
 * "getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,
 * "Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],
 * [10,"Waterloo",38],["Leyton","Waterloo"]]
 *
 * Output
 * [null,null,null,null,null,null,null,14.0,11.0,null,11.0,null,12.0]
 *
 * Explanation
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(45, "Leyton", 3);
 * undergroundSystem.checkIn(32, "Paradise", 8);
 * undergroundSystem.checkIn(27, "Leyton", 10);
 * undergroundSystem.checkOut(45, "Waterloo", 15);
 * undergroundSystem.checkOut(27, "Waterloo", 20);
 * undergroundSystem.checkOut(32, "Cambridge", 22);
 * undergroundSystem.getAverageTime("Paradise", "Cambridge");       // return 14.0. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.0. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.0
 * undergroundSystem.checkIn(10, "Leyton", 24);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.0
 * undergroundSystem.checkOut(10, "Waterloo", 38);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 12.0
 *
 *
 * Constraints:
 *
 * There will be at most 20000 operations.
 * 1 <= id, t <= 10^6
 * All strings consist of uppercase, lowercase English letters and digits.
 * 1 <= stationName.length <= 10
 * Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class UndergroundSystem {

  Map<Integer, CheckIn> custIdCheckIn = new HashMap();

  Map<String, Map<String, double[]>> stationCheckedOut = new HashMap();

  public UndergroundSystem() {

  }

  /**
   * Create two maps - id->Checkin object (id, from, time) and
   * checkoutMap -> fromStation->Map<toStation, [sum_time,num_of_customers]
   * @param id
   * @param stationName
   * @param t
   */
  public void checkIn(int id, String stationName, int t) {
    custIdCheckIn.put(id, new CheckIn(stationName, t));
  }

  public void checkOut(int id, String stationName, int t) {
    CheckIn checkin = custIdCheckIn.remove(id);

    Map<String, double[]> toMap;
    if (!stationCheckedOut.containsKey(checkin.from)) {
      toMap = new HashMap();
      stationCheckedOut.put(checkin.from, toMap);
    } else
      toMap = stationCheckedOut.get(checkin.from);

    double[] nums;
    if (!toMap.containsKey(stationName)) {
      nums = new double[] { 0.0, 0.0};
      toMap.put(stationName,  nums);
    } else {
      nums = toMap.get(stationName);
    }

    nums[1] += t - checkin.time;
    nums[0] += 1.0;
  }

  public double getAverageTime(String startStation, String endStation) {
    Map<String, double[]> toMap = stationCheckedOut.get(startStation);
    double[] pair = toMap.get(endStation);
    return (double)pair[1]/pair[0];
  }
}

class CheckIn {
  String from;
  int time;

  CheckIn(String from, int time) {
    this.from = from;
    this.time = time;
  }
}
