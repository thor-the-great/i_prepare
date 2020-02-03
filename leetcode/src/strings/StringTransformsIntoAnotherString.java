package strings;

import java.util.Arrays;

public class StringTransformsIntoAnotherString {

    /**
     * Special case - both strings are equal - don't need to continue.
     * Key thing is how to transform. Cases:
     * - if same char from str1 mapped to two different ones in str2 - this is false
     * - chain as a->b->c. Do it like b->c then a->b
     * - chain like a->b->c->a - need a temporary char to save mapping.
     * In case of possible true we need at least one unused char from str2 to finish mapping.
     * Count the mapping as we go, count all mapped char from str2. In case there are 2 mappings from the same
     * str1 char to different str2 chars - return false. Otherwise count every used char from str2, then if there are
     * no unused chars in str2 - we can't finish mapping, true otherwise.
     * @param str1
     * @param str2
     * @return
     */
    public boolean canConvert(String str1, String str2) {
        //corner case if both strings are the same
        if (str1.equals(str2))
            return true;
        //track used chars from str2
        boolean[] used = new boolean[26];
        //save mapping str1->str2 chars
        int[] mapping = new int[26];
        int countUsed = 0;
        //init mapping with -1 so we now if it's not yet taken
        Arrays.fill(mapping, -1);

        for (int i = 0; i < str1.length(); i++) {
            int idx1 = str1.charAt(i) - 'a';
            int idx2 = str2.charAt(i) - 'a';
            //check if this mapping is taken but now we're trying to map it to another char from str1
            if (mapping[idx1] != -1 && mapping[idx1] != idx2)
                return false;
            //save mapping
            mapping[idx1] = idx2;
            //if we haven't marked this char as used do it and increment count of used ones
            if (!used[idx2]) {
                used[idx2] = true;
                ++countUsed;
            }
        }
        //we need at least one unused char from str2
        return countUsed < 26;
    }
}
