package cracking.moderate;

public class SubSort {

    int subSort(int[] arr) {
        if (arr.length <= 1)
            return 0;

        int midR = -1, midL = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                midL = i;
                break;
            }
        }
        if (midL == -1)
            return 0;
        int min = arr[midL], max = arr[midL];

        for (int i = midL; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
            if (arr[i] > max)
                max = arr[i];

            if (arr[i] > arr[i + 1]) {
                midR = i;
                break;
            }
        }

        for (midL -= 1; midL >= 0; midL--) {
            if (arr[midL] <= min) {
                break;
            }
        }
        for (; midR < arr.length - 1; midR++) {
            if (arr[midR + 1] >= max) {
                break;
            }
        }

        return midR - midL;
    }

    public static void main(String[] args) {
        SubSort obj = new SubSort();
        int[] arr = new int[] {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        System.out.println(obj.subSort(arr));
        arr = new int[] {1, 2, 4, 7, 10, 11, 8, 12, 5, 6, 16, 18, 19};
        System.out.println(obj.subSort(arr));
    }
}
