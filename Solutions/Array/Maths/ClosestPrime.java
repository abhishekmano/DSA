package Solutions.Array.Maths;

import java.util.Arrays;

//2523. Closest Prime Numbers in Range
//https://leetcode.com/problems/closest-prime-numbers-in-range
public class ClosestPrime {
    // solved using sieve of eratosthenes
    public int[] closestPrimes(int left, int right) {
        boolean[] seive = sieve(right);
        int[] res = new int[] { -1, -1 };
        // print(seive);
        int prev = -1;
        int minDiff = Integer.MAX_VALUE;
        for (int idx = left; idx <= right; ++idx) {
            if (seive[idx] == true) {
                if (prev != -1) {
                    int diff = idx - prev;
                    if (diff < minDiff) {
                        minDiff = diff;
                        res = new int[] { prev, idx };
                    }
                }
                prev = idx;
            }
        }
        return res;
    }

    public boolean[] sieve(int max) {
        boolean[] res = new boolean[max + 1];
        Arrays.fill(res, true);
        res[0] = false;
        res[1] = false;
        for (int idx = 2; idx <= max; ++idx) {
            if (res[idx] == false)
                continue;
            for (int next = idx + idx; next <= max; next += idx) {
                if (res[next]) {
                    res[next] = false;
                }
            }

        }
        return res;

    }

    public static void print(boolean[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}
