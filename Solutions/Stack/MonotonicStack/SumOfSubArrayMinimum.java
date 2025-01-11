package Solutions.Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

//907. Sum of Subarray Minimums
//https://leetcode.com/problems/sum-of-subarray-minimums
public class SumOfSubArrayMinimum {
    public int sumSubarrayMins(int[] arr) {
        int mod = 1_000_000_007;
        int n = arr.length;
        long res = 0;
        Deque<Pair> mono = new ArrayDeque<Pair>();
        // for every element We have to find smallest element on both side that limits
        // the range of current element to be minimum
        // Every element popped from stack the top element in stack is left min and
        // current is right min
        for (int idx = 0; idx <= n; ++idx) {
            while (!mono.isEmpty() && (idx == n || arr[idx] <= mono.peek().num)) {
                var top = mono.pop();
                int mid = top.index;
                int left = -1;
                if (!mono.isEmpty()) {
                    left = mono.peek().index;
                }
                int right = idx;
                long count = (right - mid) * (mid - left);
                count = count % mod;
                res += (count * top.num) % mod;
                res = res % mod;
            }
            if (idx != n)
                mono.push(new Pair(arr[idx], idx));
        }
        return (int) res;
    }

    public record Pair(int num, int index) {
    };
}
