public class Problems_0930 {

    int splitArrays(int arr[]) {
        int l = arr[0];

        int[] rToLArray = new int[arr.length];
        rToLArray[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >=0; i--) {
            rToLArray[i] = Math.min(rToLArray[i + 1], arr[i]);
        }

        for (int i = 1; i < arr.length; i++) {
            if (l <= rToLArray[i])
                return i - 1;
            l = Math.max(l, arr[i]);
        }
        return 0;
    }

    public static void main(String[] args) {
        Problems_0930 obj = new Problems_0930();
        System.out.println(obj.splitArrays(new int[]{5, 0, 3, 8, 6}));
    }
}
