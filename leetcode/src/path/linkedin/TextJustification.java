package path.linkedin;

import linked_list.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList();
        int N = words.length;
        if (N == 0)
            return res;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        while (i < N - 1) {
            sb.setLength(0);
            int pos = 0;
            int initI = i;
            int countOfSpaces = 0;
            int space = 0;
            while (i + 1 < N && pos + countOfSpaces + space + words[i + 1].length() <= maxWidth) {
                i++;
                pos += words[i].length();
                //this is for space between words
                if (i - 1 > initI) {
                    countOfSpaces++;
                } else
                    space = 1;
            }
            boolean isLastLine = i == N - 1;
            int countOfWords = i - initI;
            int extraEveryWord = (countOfWords - 1 == 0) ?  0 : (1 + ( (maxWidth - (pos + countOfSpaces)) / (countOfWords - 1)));
            int extraExtraSpaces = (maxWidth - pos) - (countOfWords-1)*extraEveryWord;
            for (int w = initI+ 1; w <= i; w++) {
                sb.append(words[w]);
                if (isLastLine) {
                    if (w != i) {
                        sb.append(" ");
                    }
                    else
                        addSpaces(sb, (maxWidth - pos - (countOfWords - 1))); //this is for left justification in the last line
                }
                else {
                    if (w != i) {
                        addSpaces(sb, extraEveryWord);
                        if (extraExtraSpaces > 0) {
                            sb.append(" ");
                            extraExtraSpaces--;
                        }
                    } else if (countOfWords == 1)
                        addSpaces(sb, extraExtraSpaces);
                }
            }
            res.add(sb.toString());
        }
        return res;
    }

    void addSpaces(StringBuilder sb, int spacesCount) {
        for (int s = 0; s < spacesCount; s++)
            sb.append(" ");
    }

    public static void main(String[] args) {
        TextJustification obj = new TextJustification();
        String[] s;
        List<String> res;

        s = new String[] {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.",
                "Art","is","everything","else","we","do"};
        res = obj.fullJustify(s, 20);
        System.out.println(StringUtils.listStringsToString(res));

        s = new String[] {
                "This", "is", "an", "example", "of", "text", "justification."
        };
        res = obj.fullJustify(s, 16);
        System.out.println(StringUtils.listStringsToString(res));

        s = new String[] {"What","must","be","acknowledgment","shall","be"};
        res = obj.fullJustify(s, 16);
        System.out.println(StringUtils.listStringsToString(res));
    }
}
