package random_problems;

public class DayInYear {

  /**
   *
   * @param date
   * @return
   */
  public int dayOfYear(String date) {
    String[] parts = date.split("-");
    int year = Integer.parseInt(parts[0]), month = Integer.parseInt(parts[1]), day = Integer.parseInt(parts[2]);
    int res = 0;
    for (int m = 1; m < month; m++) {
      res += daysInMonth(m, year);
    }
    res += day;
    return res;
  }

  int daysInMonth(int month, int year) {
    int res;
    switch(month) {
      case 2:
        res = year % 4 == 0 ? 29 : 28;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        res = 30;
        break;
      default:
        res = 31;
        break;
    }
    return res;
  }

  public static void main(String[] args) {
    DayInYear obj = new DayInYear();
    System.out.println(obj.dayOfYear("1900-03-25"));
  }
}
