package binary_search;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
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

    public boolean isPerfectSquare(int num) {
        if ( num == 1)
            return true;
        int l = 0, r = num;

        while (l < r) {
            float m = l + (float)(r - l)/2;
            float div1 = (float)num/m;
            float div2 = (float)num/(m + 1);
            if ((div1 >= m) && (div2 <= m)) {
                 if (div1 == m)
                    return true;
                else
                    return false;
            } else if (div1 < m) {
                r = Math.round(m);
            } else
                l = (int) m;
        }
        return false;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        while(l < r) {
            int m = l + (r - l)/2;
            if (target == letters[m]) {
                if (m == letters.length - 1)
                    return letters[0];
                else {
                    for( int i = m + 1; i < letters.length; i ++) {
                        if (letters[i] != target)
                            return letters[i];
                    }
                    return letters[0];
                }
            }
            if (letters[m] < target && letters[m + 1] > target) {
                return letters[m + 1];
            } else if (letters[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return letters[0];
    }

    public int findMinRotatedNoDups(int[] nums) {
        int l = 0, r = nums.length - 1;
        //handle edge cases - if there is only 1 element or elements were not rotated
        if (l == r || nums[l]<nums[r])
            return nums[0];
        while (l < r) {
            int m = l + (r-l)/2;
            if (nums[m] > nums[m+1])
                return nums[m+1];
            else if (nums[l] > nums[m])
                r = m;
            else
                l = m;
        }
        return nums[r];
    }

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            while(nums[l] == nums[r] && l != r) {
                l++;
            }

            if (nums[l] <= nums[r])
                return nums[l];

            int m = l + (r-l)/2;
            if (nums[m] >= nums[l])
                l = m + 1;
            else
                r = m;
        }
        return nums[r];
    }

    /*public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList();
        int point1=0, point2=0;
        int currentElement1 = Integer.MIN_VALUE, currentElement2 = Integer.MIN_VALUE;

        while (point1 < nums1.length && point2 < nums2.length) {
            if (nums1[point1] == currentElement1) {
                point1++;
                continue;
            }
            if (nums2[point2] == currentElement2) {
                point2++;
                continue;
            }
            if (nums1[point1] == nums2[point2]) {
                result.add(nums1[point1]);
                currentElement1 = nums1[point1];
                currentElement2 = nums2[point2];
                point1++;
                point2++;
            }
            else if (nums1[point1] > nums2[point2])
                point2++;
            else
                point1++;
        }
        int[] resultArray = new int[result.size()];
        for(int i =0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }*/

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            return doThing(nums2, nums1);
        } else
            return doThing(nums1, nums2);
    }

    int[] doThing(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        Set<Integer> result = new HashSet<>();

        for(int i=0; i < nums2.length; i++) {
            //if (Arrays.binarySearch(nums1, nums2[i]) >= 0 && !result.contains(nums2[i])) {
            if (findBS(nums1, nums2[i]) && !result.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }

        int[] resultArray = new int[result.size()];
        int i =0;
        for (int j : result) {
            resultArray[i] = j;
            i++;
        }
        return resultArray;
    }

    boolean findBS(int[] arr, int num) {
        boolean result = false;
        int r=arr.length - 1, l = 0;
        while (l <= r) {
            int m = l + (r - l )/2;
            if (arr[m] == num)
                return true;
            if (arr[m] < num)
                l = m + 1;
            else
                r = m - 1;
        }
        return result;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length <= nums2.length)
            return findIntersect(nums1, nums2);
        else
            return findIntersect(nums2, nums1);
    }

    private int[] findIntersect(int[] smaller, int[] larger) {
        Arrays.sort(smaller);
        int hits = 0;
        for(int bigElement : larger) {
            if (findBS(smaller, bigElement, hits)) {
                hits++;
            }
        }
        //at the end in smaller we have unmuched elements first and then - all muched elements, pivot point is (smaller.length - hits)
        int[] resultArray = new int[hits];
        int offset = smaller.length - hits;
        for(int i=0; i<hits; i++) {
            resultArray[i] = smaller[offset + i];
        }
        return resultArray;
    }

    private boolean findBS(int[] arrList, int num, int hits) {
        boolean result = false;
        int l=0, r = arrList.length - 1 - hits;
        while(l <= r) {
            int m = l + (r-l)/2;
            int el = arrList[m];
            if (el == num) {
                //we shift all elements to the left and put our hit to the end.
                int i;
                for (i = m; i != arrList.length - 1 - hits; i++) {
                    arrList[i] = arrList[i + 1];
                }
                arrList[i] = el;
                return true;
            }
            if (el > num)
                r = m - 1;
            else
                l = m  +1;
        }
        return result;
    }

    public int[] twoSum(int[] numbers, int target) {
        int l=0, r = numbers.length - 1;
        int rPoint = -1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (numbers[m] <= target && numbers[m+1]>target) {
                rPoint = m +1;
                break;
            }
            if (numbers[m] > target)
                r = m;
            else
                l = m +1;
        }
        if (rPoint == -1) rPoint = numbers.length - 1;
        int lPoint = 0;
        while(rPoint >=lPoint) {
            int rNum = numbers[rPoint];
            int lNum = numbers[lPoint];
            if (rNum + lNum == target)
                return new int[]{lPoint + 1, rPoint +1};
            if (rNum + lNum > target) {
                rPoint--;
            } else {
                lPoint++;
            }
        }
        return new int[]{-1, -1};
    }

    public int findDuplicate(int[] nums) {
        //based on Floyd's Tortoise and Hare (Cycle Detection)
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        //detect the cycle start
        int startP1 = nums[0];
        int startP2 = slow;

        while (startP1 != startP2) {
            startP1 = nums[startP1];
            startP2 = nums[startP2];
        }
        return startP1;
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int W = 2*nums[nums.length - 1];

        int[] multiplicity = new int[nums.length];
        for (int i =1; i < nums.length; ++i) {
            if (nums[i] == nums[i -1])
                multiplicity[i] = 1 + multiplicity[i -1];
        }

        int[] prefix = new int[W];
        int left = 0;
        for (int i =1; i < W; ++i) {
            while(left < nums.length && nums[left] == i)
                left++;
            prefix[i] = left;
        }

        int l = 0, r = nums[nums.length - 1] - nums[0];
        while (l < r) {
            int m = l + (r - l) / 2;
            int count = 0;

            for (int i = 0; i < nums.length; i++) {
                count += prefix[nums[i] + m] - prefix[nums[i]] + multiplicity[i];
            }
            if (count >= k )
                r = m;
            else
                l = m + 1;
        }

        return l;
    }

    public int splitArray(int[] nums, int m) {
        //approach based on Binary search
        //get the limits - low = -1, high is the sum of all elements
        long low = 0;
        long high = 0;
        for (int element : nums) {
            high += element;
        }
        while (low + 1 < high) {
            long med = low + (high - low) /2;
            //sum of current continues segment
            long currSegmentSum = 0;
            int segment = 1;
            for (int i =0; i < nums.length; i++) {
                long sumPlusNext = currSegmentSum  + nums[i];
                //if sum in segment > med - switch to next segment
                if (sumPlusNext <= med) {
                    currSegmentSum = sumPlusNext;
                } else {
                    //special case - if one element alone is greater than med
                    if (nums[i] > med) {
                        segment = m + 1;
                        break;
                    }
                    i--;
                    segment++;
                    currSegmentSum = 0;
                }
            }
            //now shift edges of BS - if less segments enough to fit the med then high edge must be lowered
            if (segment <= m )
                high = med;
            else
                low = med;
        }
        return (int) high;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        //System.out.println(intArrayToString(obj.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
        //System.out.println(intArrayToString(obj.searchRange(new int[]{1}, 0)));
        //System.out.println(intArrayToString(obj.searchRange(new int[]{0,0,0,0,1,2,3,3,4,5,6,6,7,8,8,8,9,9,10,10,11,11}, 0)));

        /*
        System.out.println(obj.isPerfectSquare(42));
        System.out.println(obj.isPerfectSquare(16));
        System.out.println(obj.isPerfectSquare(17));
        System.out.println(obj.isPerfectSquare(3));
        System.out.println(obj.isPerfectSquare(4));
        System.out.println(obj.isPerfectSquare(5));
        System.out.println(obj.isPerfectSquare(125));
        System.out.println(obj.isPerfectSquare(2147483647));
        System.out.println(obj.isPerfectSquare(808201));
        System.out.println(obj.isPerfectSquare(2147395600));
        */

        /*System.out.println(obj.nextGreatestLetter(new char[] {'b'}, 'g'));
        System.out.println(obj.nextGreatestLetter(new char[] {'b','d','k', 'm'}, 'b'));
        System.out.println(obj.nextGreatestLetter(new char[] {'e','e','e','e','e','e','n','n','n','n'}, 'e'));*/

        //System.out.println(obj.findMinRotatedNoDups(new int[] {3, 1, 2}));

        //System.out.println(obj.findMin(new int[] {3,3,3,1}));

        /*System.out.println(intArrayToString(obj.intersection(
                new int[] {1,2,2,1},
                new int[] {2,2}
                )));

        System.out.println(intArrayToString(obj.intersect(
                new int[] {1},
                new int[] {1,1}
        )));

        System.out.println(intArrayToString(obj.intersect(
                new int[] {6, 66,2, 2,100, 1, 4, 2, 1},
                new int[] {10, 1,1, 4,9, 2, 2, 100, 1, 2}
        )));*/

        /*System.out.println(intArrayToString(obj.twoSum(
                new int[] {0, 1, 3, 7, 9, 10, 15},     11
        )));*/

        /*System.out.println(intArrayToString(obj.twoSum(
                new int[] {-1, 0}, -1
        )));*/

        /*System.out.println(obj.findDuplicate(
                new int[] {1, 2, 3, 4, 5, 6, 3}));*/

        //System.out.println(obj.smallestDistancePair(new int[]{1,3,1}, 2));

        System.out.println(obj.splitArray(new int[]{1,2147483647}, 2));

        System.out.println(obj.splitArray(new int[]{7, 2, 5, 10, 8}, 3));
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
}
