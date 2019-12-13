package design;

/**
 * 379. Design Phone Directory
 * Medium
 *
 * Design a Phone Directory which supports the following operations:
 *
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 * Example:
 *
 * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 *
 * // It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 *
 * // Assume it returns 1.
 * directory.get();
 *
 * // The number 2 is available, so return true.
 * directory.check(2);
 *
 * // It returns 2, the only number that is left.
 * directory.get();
 *
 * // The number 2 is no longer available, so return false.
 * directory.check(2);
 *
 * // Release number 2 back to the pool.
 * directory.release(2);
 *
 * // Number 2 is available again, return true.
 * directory.check(2);
 */
public class PhoneDirectory {

    /**
     * Use array, each element has a pointer to the next available, also keep pointer
     * variable. When get set the array[i] to -1. When releasing a number unset -1 to a
     * current pointer.
     *
     * Similar is possible with Set
     */
    int[] nums;
    int p;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        nums = new int[maxNumbers];
        for (int i = 0; i < maxNumbers; i++)
            nums[i] = i + 1 < maxNumbers ? i + 1 : 0;
        p = 0;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (nums[p] == -1)
            return -1;

        int num = p;
        p = nums[p];
        nums[num] = -1;
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return nums[number] != -1;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (nums[number] != -1)
            return;
        nums[number] = p;
        p = number;
    }
}
