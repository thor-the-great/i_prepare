public class RecursionFibonachi {

    int getNumRecursiveSlow(int n) {
        if (n <= 1)
            return 1;
        return getNumRecursiveSlow(n - 1) + getNumRecursiveSlow(n - 2);
    }

    int getNumRecursiveFast(int n) {
        int[] res = helper(n);
        return res[1];
    }

    int[] helper(int n) {
        if (n == 0)
            return new int[]{0, 1};
        if (n == 1)
            return new int[]{1, 1};

        int[] tmp = helper(n -1);
        return new int[] {tmp[1], tmp[0] + tmp[1]};
    }

    public static void main(String[] args) {
        RecursionFibonachi obj = new RecursionFibonachi();
        //System.out.println(obj.getNumRecursiveSlow(100));
        System.out.println(obj.getNumRecursiveFast(45));
        System.out.println(obj.getNumRecursiveSlow(45));
    }
}
