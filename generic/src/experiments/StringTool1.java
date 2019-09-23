package experiments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StringTool1 {

  private void parseFileToJavaCode() {
    String file = "/Users/i057833/dev/tmp/an_service_model.txt";
    try {
      Scanner reader = new Scanner(new File(file));
      while(reader.hasNextLine()) {
        String s = reader.nextLine();
        String pref = "  public static final String ";
        String sub1 = s.substring(pref.length(), s.length() - 1);
        String[] parts = sub1.split("=");
        parts[0] = parts[0].trim();
        parts[1] = parts[1].trim();
        //System.out.println(Arrays.toString(parts));
        StringBuilder sb = new StringBuilder();
        sb.append(getName(parts[0]))
            .append(" (")
            .append(parts[1])
            .append("),");
        System.out.println(sb.toString());
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private String getName(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (i == 0) {
        sb.append(Character.toUpperCase(ch));
        continue;
      }
      if (Character.isLowerCase(ch)) {
        sb.append(Character.toUpperCase(ch));
        if (i == s.length() - 1)
          continue;
        else {
          if (Character.isUpperCase(s.charAt(i + 1)))
            sb.append("_");
        }
      } else
        sb.append(ch);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    StringTool1 obj = new StringTool1();
    obj.parseFileToJavaCode();
  }
}
