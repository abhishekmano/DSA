package Solutions.Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWaterOne {
    // using monotonic stack
    public int trapMono(int[] height) {
        Deque<Integer> decreasing = new ArrayDeque<Integer>();
        int n = height.length;
        int result = 0;
        for (int idx = 0; idx < n; ++idx) {
            while (decreasing.size() != 0 && (height[idx] > height[decreasing.peek()])) {
                var top = decreasing.pop();
                if (!decreasing.isEmpty()) {
                    int left = height[decreasing.peek()];
                    int right = height[idx];
                    int min = Math.min(left, right);
                    int width = (idx - decreasing.peek() - 1);
                    if (height[top] < min) {
                        result += (min - height[top]) * width;
                    }
                }
            }
            decreasing.push(idx);
        }
        return result;
    }

    // using max on both sides logic //not monotonic stack
    public int trap(int[] height) {
        int n = height.length;
        int[] max = new int[n];
        int running = 0;
        for (int idx = 0; idx < n; ++idx) {
            max[idx] = running;
            running = Math.max(running, height[idx]);
        }
        running = 0;
        for (int idx = n - 1; idx >= 0; --idx) {
            max[idx] = Math.min(running, max[idx]);
            running = Math.max(running, height[idx]);
        }
        int result = 0;
        for (int idx = 0; idx < n; ++idx) {
            int h = height[idx];
            int m = max[idx];
            if (m > h) {
                result += (m - h);
            }
        }
        return result;
    }
}
