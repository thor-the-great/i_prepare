package binary_search;

import java.util.*;
import java.util.List;

public class Solution {

        public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums.length < 2)
            return result;
        int[] numsSorted = new int[nums.length];
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        Arrays.sort(numsSorted);
        int smallI = 0;
        int bigI = numsSorted.length - 1;
        while (smallI < bigI) {
            int testSum = numsSorted[smallI] + numsSorted[bigI];
            while(testSum > target) {
                bigI--;
                testSum = numsSorted[smallI] + numsSorted[bigI];
            }
            if (testSum == target) {
                result[0] = Arrays.binarySearch(nums, numsSorted[smallI]);
                result[1] = Arrays.binarySearch(nums, numsSorted[bigI]);;
                return result;
            } else {
                bigI++;
                smallI++;
            }
        }
        return result;
    }

    /*public int search(int[] nums, int target) {
        int begPointer = 0;
        int endPointer = nums.length - 1;
        while (begPointer <= endPointer) {
            int med = (endPointer + begPointer)/2;
            if (nums[med] == target)
                return med;
            else if (nums[med] < target) {
                begPointer = med +1;
            } else {
                endPointer = med - 1;
            }
        }
        return -1;
    }*/

    public int mySqrt(int x) {
        long l = 0;
        long r = (x / 2) + 1;
        while (l <= r) {
            long med = (l + r) / 2;
            if (med*med == x)
                return (int)med;
            if (med*med > x)
                r = med - 1;
            else
                l = med + 1;
        }
        return (int)r;
    }

    public int guessNumber(int n) {
        int l = 1, r = n;
        int gn = 0;
        int guessResult = -1;
        while(guessResult != 0 ) {
            gn = (int) (((long)l + (long)r)/2);
            guessResult = guess(gn);
            if (guessResult < 0) {
                r = gn - 1;
            } else if (guessResult > 0) {
                l = gn + 1;
            }
        }
        return (int) gn;
    }

    int guess(int n) {
        //return -Integer.compare(n, 6);
        return -Integer.compare(n, 1702766719);
        //                        2126753390
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int pivotIndex = -1;
        int i = 1;
        if (nums.length <= 1)
            pivotIndex = 0;
        //i - index of smallest element (first element in second series)
        while (pivotIndex == -1 && i < nums.length) {
            if (nums[i - 1] > nums[i]) {
                pivotIndex = i - 1;
                break;
            }
            i++;
        }
        int indexOne = findInSortedArray(nums, target, 0, pivotIndex);
        int indexTwo = findInSortedArray(nums, target, pivotIndex + 1, nums.length - 1);
        if (indexOne == -1 && indexTwo == -1)
            return -1;
        if (indexOne == -1)
            return indexTwo;
        else
            return indexOne;
    }

    int findInSortedArray(int[] array, int number, int i, int j) {
        int l = i;
        int r = j;
        while(l <= r) {
            int m = (l + r) / 2;
            if (array[m] == number)
                return m;
            if (array[m] < number )
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    public int firstBadVersion(int n) {
        boolean firstBad = isBadVersion(1);
        if (firstBad) {
            return 1;
        }
        int l = 1;
        int r = n;
        while (l < r) {
            int m = l + (r -l)/2;
            boolean mV = isBadVersion( m);
            /*if (!mV && isBadVersion(m+1))
                return m + 1;
            if (mV)
                r = (m -1);
            else
                l =  (m + 1);*/
            if(mV){
                r = m;
            } else
                l = m + 1;
        }
        return l;
    }

    boolean isBadVersion (int n ) {
        if (n >= 1702766719)
            return true;
        else
            return false;
    }

    public int findMin(int[] nums) {
        int l=0, r = nums.length -1;
        int x = nums[0];
        while (l < r) {
            int m = (l + r)/2;
            if (nums[m] > nums[m+1]) {
                return  nums[m + 1];
            }
            if (nums[m] > x) {
                l = m +1;
            } else {
                r = m;
            }
        }
        return x;
    }

    /*public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int x = -1;
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                x = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid +1;
            } else {
                right = mid-1;
            }
        }

        if (x == -1)
            return new int[] {-1, -1};

        // Post-processing:
        // End Condition: left + 1 == right
        int j = left;
        while (j <= x && nums[j] < target) {
            j++;
        }

        int k = x;
        while (k <= right && k + 1 < nums.length && nums[k + 1] == target) {
            k++;
        }
        return new int[]{j, k};
    }*/


    public int[] searchRange(int[] nums, int target) {
        int l=0, r = nums.length - 1;
        int x = -1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                x = m;
                break;
            }
            if (nums[m] < target) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }

        if (x == -1)
            return new int[] {-1, -1};

        do {
            if (nums[l] == target)
                break;
            l++;
        } while (l < x);

        do {
            if (nums[r] == target)
                break;
            r--;
        } while (r > x);
        return new int[] {l, r};

        /*int lPoint = findPoint(nums, l, x - 1, target);
        //int rPoint = findPoint(nums, x + 1, r, target);
        return new int[] {lPoint == -1 ? x : lPoint, rPoint == -1 ? x : rPoint};*/
    }

    int findPoint(int[] nums, int x, int y, int target) {
        int l = x, r = y;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return -1;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (x < arr[0])
            return getListFromArray(arr, 0, k - 1);

        if (x > arr[arr.length - 1])
            return getListFromArray(arr, arr.length - k, arr.length - 1);

        int l=0, r = arr.length-1;
        List<Integer> result = new ArrayList<Integer>();

        int xPlace = -1;
        while (l < r) {
            int m = l + (r - l)/2;
            if (x == arr[m]) {
                xPlace = m;
                break;
            }
            if (arr[m] > x) {
                r = m -1;
            } else
                l = m +1;
        }
        if (xPlace == -1) {
            int numOfElementsToReturn = k <= arr.length ? k : arr.length;
            //int[] result = new int[numOfElementsToReturn];
            for (int i =0; i < numOfElementsToReturn; i++) {
                result.add(arr[i]);
            }
        }
        return result;
    }

    List<Integer> getListFromArray(int[] array, int start, int end) {
        List<Integer> result = new ArrayList<Integer>();
        for(int i = start; i <= end; i++) {
            result.add(array[i]);
        }
        return result;
    }

    public int findPeakElement(int[] nums) {
        int l = -1, r = nums.length;
        int p = -1;
        while (l<r) {
            int m = l + (r-l)/2;
            boolean isMGreaterMPlus1 = getArrayElement(nums, m) >= getArrayElement(nums, m + 1);
            boolean isMGreaterMMinus1 = getArrayElement(nums, m) >= getArrayElement(nums, m - 1);
            if ( isMGreaterMPlus1 && isMGreaterMMinus1) {
                p = m;
                break;
            } else if (isMGreaterMPlus1 && !isMGreaterMMinus1) {
                r = m;
            } else {
                l = m;
            }
        }
        return p == -1 ? l : p;
    }

    int getArrayElement(int[] array, int index) {
        if (index < 0 || index >= array.length) {
            return Integer.MIN_VALUE;
        } else
            return array[index];
    }

/*
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int r = nums1.length;
        int l = 0;
        int partYOffset = (nums1.length + nums2.length + 1)/2;
        while (l <= r) {
            int partitionX = r + (l - r)/2;
            int partitionY = partYOffset - partitionX;

            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];

            int minRightX = partitionX >= nums1.length ? Integer.MAX_VALUE : nums1[partitionX];
            int minRightY = partitionY >= nums2.length ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((nums1.length + nums2.length) % 2 == 0) {
                    double median = ((double)(Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))) /2;
                    return median;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            }
            else if (maxLeftX > minRightY) {
                r = partitionX - 1;
            } else {
                l = partitionX + 1;
            }
        }
        return 0.0;
    }
*/

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length)
            return findMedianSortedArrays(nums2, nums1);

        int l=0, r = nums1.length;
        int largeOffset = (nums1.length + nums2.length + 1) / 2;
        while (l <= r) {
            int mSmall = l + (r- l)/2;
            int mLarge = largeOffset - mSmall;

            int minSmall = mSmall == 0 ? Integer.MIN_VALUE : nums1[mSmall - 1];
            int minLarge = mLarge == 0 ? Integer.MIN_VALUE : nums2[mLarge - 1];

            int maxSmall = mSmall >= nums1.length ? Integer.MAX_VALUE : nums1[mSmall];
            int maxLarge = mLarge >= nums2.length ? Integer.MAX_VALUE : nums2[mLarge];

            if (minSmall <= maxLarge && maxSmall >= minLarge) {
                if ((nums1.length + nums2.length) % 2 == 0) {
                    double med = ((double)(Math.max(minSmall, minLarge) + Math.min(maxSmall, maxLarge))) / 2;
                    return med;
                }
                else
                    return (double) Math.max(minSmall, minLarge);
            }

            if (minSmall > maxLarge)
                r = mSmall - 1;
            else
                l = mSmall + 1;
        }

        return 0.0;
    }

    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        double half = myPow(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else if (n > 0)
            return half * half * x;
        else
            return half * half / x;
    }

    public boolean isPerfectSquare(int num) {
        if ( num == 1)
            return true;
        int l = 0, r = num;
        java.math.BigInteger numBI = java.math.BigInteger.valueOf(num);
        while (l < r) {
            int m = l + (r - l)/2;
            java.math.BigInteger mBI = java.math.BigInteger.valueOf(m);
            java.math.BigInteger mSquare = mBI.multiply(mBI);
            int compare1 = mSquare.compareTo(numBI);
            if ( compare1 <= 0 ) {
                java.math.BigInteger mPlusOneSquare = java.math.BigInteger.valueOf(m + 1).multiply(java.math.BigInteger.valueOf(m + 1));;
                int compare2 = mPlusOneSquare.compareTo(numBI);
                if (compare2 >= 0) {
                    if (compare1 == 0 || compare2 == 0)
                        return true;
                    else
                        return false;
                }
                else if (compare1 == 1) {
                    r = m;
                }
                else {
                    l = m;
                }
            }
            else if (compare1 == 1) {
                r = m;
            }
            else {
                l = m;
            }
        }
        return false;
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int low = 0, high = nums[nums.length - 1] - nums[0];
        while (low < high) {
            int mid = low + (high - low)/2;
            int l = 0, count = 0;
            for (int r = 0; r < nums.length; r++) {
                while (nums[r] - nums[l] > mid) {
                    l++;
                }
                count+= r - l;
            }
            if (count < k)
                low = mid + 1;
            else
                high = mid;

        }
        return low;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        /*System.out.println(obj.mySqrt(8));
        System.out.println(obj.mySqrt(2));
        System.out.println(obj.mySqrt(4));
        System.out.println(obj.mySqrt(14));
        System.out.println(obj.mySqrt(9));
        System.out.println(obj.mySqrt(2147395599));
        */
        //System.out.println(obj.guessNumber(2126753390));
        /*System.out.println(obj.search(new int[] {4, 5, 6, 7, 13, 20, 0, 2, 3}, 0));
        System.out.println(obj.search(new int[] {4, 5, 6, 7, 13, 20, 0, 2, 3}, 8));
        System.out.println(obj.search(new int[] {4, 5, 6, 7, 13, 20, 0, 2, 3}, 7));
        System.out.println(obj.search(new int[] {1, 3}, 0));
        System.out.println(obj.search(new int[] {1, 3}, 1));*/

        //System.out.println(obj.firstBadVersion(2126753390));
        //System.out.println(obj.findMin(new int[]{ 2, 1 }));
        //System.out.println(obj.findMin(new int[]{ 4, 5, 6, 7, 0, 1, 2 }));
        //System.out.println(obj.findMin(new int[]{ 0, 1, 2, 4, 5, 6, 7 }));
        //System.out.println(obj.findMin(new int[]{ 3, 1, 2 }));
        //System.out.println(obj.searchRange(new int[]{ 1, 3}, 0));
        //System.out.println(obj.searchRange(new int[]{ 1}, 0));
        //System.out.println(obj.searchRange(new int[]{ 5,7,7,8,8,10}, 10));

        //System.out.println(intArrayToString(obj.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
        //System.out.println(intArrayToString(obj.searchRange(new int[]{1}, 0)));
        //System.out.println(intArrayToString(obj.searchRange(new int[]{0,0,0,0,1,2,3,3,4,5,6,6,7,8,8,8,9,9,10,10,11,11}, 0)));

        //System.out.println(intListToString(obj.findClosestElements(new int[] {1,2,3,4,5}, 4, 20)));


        /*System.out.println(obj.findPeakElement(new int[]{3,2,1}));
        System.out.println(obj.findPeakElement(new int[]{1,2}));
        System.out.println(obj.findPeakElement(new int[]{1}));
        System.out.println(obj.findPeakElement(new int[]{1,2,3,5, 6, 8, 3, 2, 10,1}));
        System.out.println(obj.findPeakElement(new int[]{-2147483648}));
        */

        /*System.out.println(obj.isPerfectSquare(16));
        System.out.println(obj.isPerfectSquare(17));
        System.out.println(obj.isPerfectSquare(3));
        System.out.println(obj.isPerfectSquare(4));
        System.out.println(obj.isPerfectSquare(5));
        System.out.println(obj.isPerfectSquare(125));
        System.out.println(obj.isPerfectSquare(2147483647));
        System.out.println(obj.isPerfectSquare(808201));
        System.out.println(obj.isPerfectSquare(2147395600));*/

        System.out.println(obj.smallestDistancePair(new int[] {1, 3, 1}, 1));

        System.out.println(obj.smallestDistancePair(new int[] {0, 5, 5, 3, 8, 10, 12 ,7}, 4));

    }

    static String intArrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    static String intListToString(List<Integer> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.size(); i++) {
            sb.append(a.get(i));
            if (i < a.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
