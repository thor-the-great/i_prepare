package segment_tree;

public class MutableArrayImpl {

    static class Naive implements MutableArray {
        int[] arr;

        Naive(int[] arr) {
            this.arr = arr;
        }

        @Override
        public void update(int idx, int val) {
            arr[idx] = val;
        }

        @Override
        public int range(int left, int right) {
            int sum = 0;
            if (left != right) {
                for (int i = left; i <= right; i++) {
                    sum += arr[i];
                }
            }
            return sum;
        }
    }

    static class CummulativeSum implements MutableArray {
        int[] cumSum;
        int[] arr;

        CummulativeSum(int[] arr) {
            this.arr = arr;
            cumSum = new int[arr.length + 1];
            for (int i = 0; i < arr.length; i++) {
                cumSum[i + 1] = cumSum[i] + arr[i];
            }
        }

        @Override
        public void update(int idx, int val) {
            int d = val - arr[idx];
            for (int i = idx + 1; i < cumSum.length; i++) {
                cumSum[i] += d;
            }
            arr[idx] = val;
        }

        @Override
        public int range(int left, int right) {
            return cumSum[right + 1] - cumSum[left];
        }
    }
}
