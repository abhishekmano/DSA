package Solutions.TwoPointer;

//11. Container With Most Water
//https://leetcode.com/problems/container-with-most-water
public class ContainerWithMostWater {
    // Optimized two pointer
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            int min = Math.min(height[start], height[end]);
            int area = (end - start) * min;
            max = Math.max(area, max);
            while (start < end && height[start] <= min)
                start++;
            while (start < end && height[end] <= min)
                end--;
        }
        return max;
    }

    // Normal Two Pointer
    public int maxArea2(int[] height) {
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            int min = Math.min(height[start], height[end]);
            int area = (end - start) * min;
            max = Math.max(area, max);
            if (min == height[start]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }
}
