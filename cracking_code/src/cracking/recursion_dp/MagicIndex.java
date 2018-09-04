package cracking.recursion_dp;

public class MagicIndex {

    int magicIndex(int[] arr) {
        return search(arr, 0, arr.length - 1);
    }

    int search(int[] arr, int low, int high) {
        if (low > high)
            return -1;
        int m = (high + low) /2;
        if (arr[m] == m)
            return m;

       int highIndex = Math.min(m -1, arr[m]);
       int leftVal = search(arr, low, highIndex);
       if (leftVal >= 0)
           return leftVal;

       int lowIndex = Math.max(m + 1, arr[m]);
       int rightVal = search(arr, lowIndex, high);

       return rightVal;
    }

    public static void main(String[] args) {
        MagicIndex obj = new MagicIndex();

        System.out.println(obj.magicIndex(new int[] {-5, -2, 3, 3, 3, 3, 5, 8, 10, 12, 20, 21, 22, 22, 22, 22, 22, 22, 22, 22, 23, 24, 25, 26}));
    }
}
