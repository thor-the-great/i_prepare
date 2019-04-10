import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Thingy {

    Thingy() {

    }

    public String doThing() {
        long shift = 32;
        long one = 1;
        //long res = ;
        System.out.println(Long.toBinaryString(1l << 32));

        int a = 5;
        System.out.println(1 + (a >> 31));
        a = 45;
        System.out.println(1 + (a >> 31));
        a = Integer.MAX_VALUE;
        System.out.println(1 + (a >> 31));
        a = 18;
        System.out.println(1 + (a >> 31));
        a = -25;
        System.out.println(1 + (a >> 31));

        Calendar cal = new GregorianCalendar();
        cal.set(2019, 9, 1);

        //get day of week name, cause number is not good - week may start from sunday
        //get to the first Tuesday of October
        while (!"Tuesday".equalsIgnoreCase(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()))) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        //get to the Second Tuesday
        cal.add(Calendar.DAY_OF_MONTH, 7);

        System.out.println(cal.get(Calendar.DAY_OF_MONTH));


        //System.out.println(SimpleDateFormat.getDateInstance().format(cal.getTime()));


        return "result";
    }

    public static void main(String[] args) {
        Thingy obj = new Thingy();
        System.out.println(obj.doThing());
    }
}
