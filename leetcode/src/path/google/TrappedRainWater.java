package path.google;

/**
 * 42. Trapping Rain Water
 * Hard
 *
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
 * (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 */
public class TrappedRainWater {

    public int trap(int[] height) {
        return trapTwoArraysMax(height);
    }

    public int trapScanFromMaxInEachDirection(int[] height) {
        if (height == null || height.length < 3)
            return 0;
        int N = height.length;

        int maxEl = -1, maxIndex = -1;
        for (int i = 0; i < N; i++) {
            if (height[i] > maxEl) {
                maxEl = height[i];
                maxIndex = i;
            }
        }

        int sum = 0;
        int maxRunning = height[0];
        for (int i = 1; i < maxIndex; i++) {
            if (height[i] >= maxRunning)
                maxRunning = height[i];
            else
                sum += maxRunning - height[i];
        }
        maxRunning = height[N - 1];
        for (int i = N - 2; i > maxIndex; i--) {
            if (height[i] >= maxRunning)
                maxRunning = height[i];
            else
                sum += maxRunning - height[i];
        }

        return sum;
    }

    public int trapTwoArraysMax(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int N = height.length;
        int[] lToRMaxes = new int[N];
        lToRMaxes[0] = height[0];
        for (int i = 1; i < N; i++) {
            if (height[i] > lToRMaxes[i - 1])
                lToRMaxes[i] = height[i];
            else
                lToRMaxes[i] = lToRMaxes[i - 1];
        }
        int[] rToLMaxes = new int[N];
        rToLMaxes[N - 1] = height[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            if (height[i] > rToLMaxes[i + 1])
                rToLMaxes[i] = height[i];
            else
                rToLMaxes[i] = rToLMaxes[i + 1];
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int minCapacity = Math.min(lToRMaxes[i], rToLMaxes[i]);
            sum += minCapacity - height[i];
        }
        return sum;
    }

    public int trapOnePassNoSpace(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1;
        int left = 0, right = 0;

        while (l < r) {
            if (height[l] < height[r]) {
                left = Math.max(height[l], left);
                res += (left - height[l]);
                ++l;
            } else {
                right = Math.max(height[r], right);
                res += (right - height[r]);
                --r;
            }
        }

        return res;
    }
}
