package Solutions.Queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

// /1696. Jump Game VI
//https://leetcode.com/problems/jump-game-vi
public class JumpGameSix {
    // this uses monotonic queue like sliding window max
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        Deque<Integer> mono = new ArrayDeque<Integer>();
        for (int idx = 0; idx < n; ++idx) {
            while (mono.size() != 0 && idx - mono.peekFirst() > k) {
                mono.pollFirst();
            }
            int max = 0;
            if (!mono.isEmpty()) {
                max = dp[mono.peekFirst()];
            }
            dp[idx] = max + nums[idx];
            while (mono.size() != 0 && dp[mono.peekLast()] <= dp[idx]) {
                mono.pollLast();
            }
            mono.offerLast(idx);
        }
        return dp[n - 1];
    }

    public void print(int[] array) {
        String result = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));

        result = "[" + result + "]";
        System.out.println(result);
    }

    public static void print(Deque<Integer> deque, int[] array) {
        System.out.print("[");
        for (int element : deque) {
            System.out.print(array[element] + ", ");
        }
        System.out.println("\b\b]");
    }
}
