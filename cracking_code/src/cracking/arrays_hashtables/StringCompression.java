package cracking.arrays_hashtables;

/**
 * Created by thor on 12/7/16.
 */
public class StringCompression {
    public static void main(String[] args) {
        StringCompression obj = new StringCompression();

        String toCompress = "aassb";
        System.out.println(obj.compress(toCompress));

        toCompress = "abbbbbbcccc";
        System.out.println(obj.compress(toCompress));
    }

    String compress(String stringToCompress) {
        if (stringToCompress == null || stringToCompress.length() == 0)
            return stringToCompress;
        StringBuilder sBuilder = new StringBuilder();
        char lastRepeatChar = stringToCompress.charAt(0);
        int repeatCount = 1;
        for (int i = 1; i < stringToCompress.length(); i++) {
            char nextChar = stringToCompress.charAt(i);
            if (nextChar != lastRepeatChar) {
                addCharacter(sBuilder, lastRepeatChar, repeatCount);
                lastRepeatChar = nextChar;
                repeatCount = 1;
            }
            else {
                repeatCount++;
            }
        }
        addCharacter(sBuilder, lastRepeatChar, repeatCount);
        return sBuilder.toString();
    }

    private void addCharacter(StringBuilder sBuilder, char lastRepeatChar, int repeatCount) {
        sBuilder.append(lastRepeatChar);
        if (repeatCount > 1) {
            sBuilder.append(repeatCount);
        }
    }
}