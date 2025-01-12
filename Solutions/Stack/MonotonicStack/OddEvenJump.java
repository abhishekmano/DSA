package Solutions.Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class OddEvenJump {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];
        even[n - 1] = true;
        odd[n - 1] = true;
        int[] oddNext = new int[n];
        int[] evenNext = new int[n];
        Integer[] sortedIndex = new Integer[n];
        for (int idx = 0; idx < n; ++idx) {
            sortedIndex[idx] = idx;
        }
        Arrays.sort(sortedIndex, (a, b) -> Integer.compare(arr[a], arr[b]));
        oddNext = nextHigh(sortedIndex, arr);
        for (int idx = 0; idx < n; ++idx) {
            sortedIndex[idx] = idx;
        }
        // This is to make sure that relative ordering of index is maintained while
        // sorting
        // if you reverse the sorted[idx] you get a wrong result if duplicate values are
        // present
        Arrays.sort(sortedIndex, (a, b) -> Integer.compare(arr[b], arr[a]));
        evenNext = nextHigh(sortedIndex, arr);
        for (int idx = n - 2; idx >= 0; --idx) {
            if (oddNext[idx] != -1) {
                odd[idx] = even[oddNext[idx]];
            }
            if (evenNext[idx] != -1) {
                even[idx] = odd[evenNext[idx]];
            }
        }
        int result = 0;
        for (int idx = 0; idx < n; ++idx) {
            if (odd[idx])
                result++;
        }
        return result;
    }

    // This is a helper which find smallest number bigger than x after x
    // as well as next biggest number smaller than x after x
    // sorted contains just the index not the actual value
    // for increasing array we get smallest element higher than itself;
    // when the array is decreasing we get largest element which is smaller than
    // itself
    public int[] nextHigh(Integer[] sorted, int[] nums) {
        Deque<Integer> mono = new ArrayDeque<Integer>();
        int n = sorted.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int idx = 0; idx < n; ++idx) {
            while (!mono.isEmpty() && mono.peek() < sorted[idx]) {
                res[mono.peek()] = sorted[idx];
                mono.pop();
            }
            mono.push(sorted[idx]);
        }
        return res;
    }
}
