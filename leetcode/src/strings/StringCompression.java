package strings;

public class StringCompression {

    public int compress(char[] chars) {
        int N = chars.length;
        //current count of continues characters
        int c = 1;
        //pointer for the in-place replacements
        int j = 0;
        //current character
        char ch = chars[0];
        for (int i = 1; i <= N; i++) {
            //in case character is different or we reached the end of the array
            if (i == N || chars[i] != ch) {
                chars[j++] = ch;
                //if we need to return compressed expression
                if (c > 1) {
                    //saving count of characters
                    int beg = j;
                    while (c > 0) {
                        chars[j++] = (char) ((c % 10) + '0');
                        c /= 10;
                    }
                    //reverse number if needed (it's > 9)
                    if (j - 1 - beg > 0) {
                        int k1 = beg, k2 = j - 1;
                        while (k1 < k2) {
                            char t = chars[k1];
                            chars[k1] = chars[k2];
                            chars[k2] = t;
                            k1++; k2--;
                        }
                    }
                }
                //reset for next character if we haven't reached the end of array
                if (i < N) {
                    c = 1;
                    ch = chars[i];
                }
            } else
                //if we have met same character before - just increase the counter
                c++;
        }
        return j;
    }

    public static void main(String[] args) {
        StringCompression obj = new StringCompression();
        char[] arr;
        System.out.print("[");
        arr = new char[]{'a'};
        int res = obj.compress(arr);
        for (int i = 0; i < res; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print("]\n");
    }
}
