package path.google;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int N = nums.length;
        int i = 0;
        while (i < N) {
            if (nums[i] <= 0 || nums[i] > N || nums[i] == i + 1)
                i++;
            else if (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else
                i++;
        }
        int j = 0;
        while (j < N && nums[j] == j + 1) {
            j++;
        }
        return j + 1;
    }

    void swap(int[] arr, int i, int j) {
        /*int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;*/
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    public static void main(String[] args) {
        FirstMissingPositive obj = new FirstMissingPositive();
        System.out.println(obj.firstMissingPositive(new int[] {3, 6, -1, 1, -3, 3}));
    }
}
