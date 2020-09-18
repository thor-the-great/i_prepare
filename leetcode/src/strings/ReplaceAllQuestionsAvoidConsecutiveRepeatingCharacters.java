package strings;

public class ReplaceAllQuestionsAvoidConsecutiveRepeatingCharacters {
    
    public String modifyString(String s) {
        if (s == null || s.isEmpty())
            return s;
        
        StringBuilder sb = new StringBuilder(s);
        int N = s.length();
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != '?') {
                //get the previous character from the result string, not the original one
                //because it could be replaced on a previous step
                char l = i > 0 ? sb.charAt(i - 1) : '-';
                //next char should be from original string
                char r = i + 1 < N ? sb.charAt(i + 1) : '-';
                //finding good char to use for replacement, it must be not equal to one to the 
                //right or one to the left
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch != l && ch != r) {
                        sb.setCharAt(i, ch);
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
}
