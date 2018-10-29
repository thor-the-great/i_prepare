import java.util.LinkedList;
import java.util.List;

public class ZeroesToMaxOnes {

    int getMaxZeroesToOnes(int[] nums, int k) {
        List<Integer> zeroes = new LinkedList<>();
        zeroes.add(-1);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeroes.add(i);
        }

        zeroes.add(nums.length);
        int maxSoFar = 0;
        for (int i = 0; i < zeroes.size(); i++) {
            int bigIndex = (i + k + 1) >= zeroes.size() ? zeroes.size() - 1 : (i + k + 1);
            int l = zeroes.get(bigIndex) - zeroes.get(i) - 1;
            maxSoFar = Math.max(maxSoFar, l);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        ZeroesToMaxOnes obj = new ZeroesToMaxOnes();
        int[] nums = new int[] {1, 1, 0, 0, 1, 1, 0, 1};
        System.out.println(obj.getMaxZeroesToOnes(nums, 1));
        System.out.println(obj.getMaxZeroesToOnes(nums, 2));

        nums = new int[] {1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1};
        System.out.println(obj.getMaxZeroesToOnes(nums, 1));
        System.out.println(obj.getMaxZeroesToOnes(nums, 2));
        System.out.println(obj.getMaxZeroesToOnes(nums, 3));

        nums = new int[] {1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1};
        System.out.println(obj.getMaxZeroesToOnes(nums, 1));
        System.out.println(obj.getMaxZeroesToOnes(nums, 2));
        System.out.println(obj.getMaxZeroesToOnes(nums, 3));

        nums = new int[] {1, 1, 1, 1, 1, 1};
        System.out.println(obj.getMaxZeroesToOnes(nums, 1));
        System.out.println(obj.getMaxZeroesToOnes(nums, 2));
    }
}