public class ArraysTest {

    void equals() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{1, 2 ,3};

        System.out.println(arr1 == arr2);
        System.out.println(java.util.Arrays.equals(arr1, arr2));

        int[][] arr2d1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] arr2d2 = new int[][]{
                {1, 2 ,3},
                {4, 5, 6}
        };

        System.out.println(arr2d1 == arr2d2);
        System.out.println(java.util.Arrays.equals(arr2d1, arr2d2));
        System.out.println(java.util.Arrays.deepEquals(arr2d1, arr2d2));

    }

    public static void main(String[] args) {
        ArraysTest obj = new ArraysTest();
        obj.equals();
    }
}
