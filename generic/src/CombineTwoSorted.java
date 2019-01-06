
public class CombineTwoSorted {

    int[] combine(int[] a, int[] b) {
        int aPointer=0;
        int bPointer=0;
        int[] c = new int[a.length + b.length];

        for (int cPointer=0; cPointer < c.length; cPointer++) {
            if (aPointer == a.length){
                c[cPointer] = b[bPointer];
                bPointer++;
            }
            else if (bPointer == b.length) {
                c[cPointer] = a[aPointer];
                aPointer++;
            }
            else if (a[aPointer] <= b[bPointer]) {
                c[cPointer] = a[aPointer];
                aPointer++;
            } else {
                c[cPointer] = b[bPointer];
                bPointer++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        CombineTwoSorted obj = new CombineTwoSorted();
        int[] result = obj.combine(new int[]{3, 5, 20}, new int[]{1, 15, 16, 19});
        for (int i: result
             ) {
            System.out.println(i + ", ");
        }

    }
}
