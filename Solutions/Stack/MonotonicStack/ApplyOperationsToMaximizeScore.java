package Solutions.Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//2818. Apply Operations to Maximize Score
//https://leetcode.com/problems/apply-operations-to-maximize-score
public class ApplyOperationsToMaximizeScore {
    int mod = 1000_000_007;

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] primeScore = new int[n];
        HashMap<Integer, Integer> primeScoreMap = new HashMap<Integer, Integer>();
        primeScoreMap.put(1, 0);
        for (int idx = 0; idx < n; ++idx) {
            int score = 0;
            int elem = nums.get(idx);
            if (primeScoreMap.containsKey(elem)) {
                primeScore[idx] = primeScoreMap.get(elem);
            } else {
                for (int factor = 2; factor <= Math.sqrt(nums.get(idx)); factor++) {
                    if (elem % factor == 0) {
                        score++;
                        while (elem % factor == 0)
                            elem /= factor;
                    }
                }
                if (elem > 1)
                    score++;
                if (score == 0)
                    score = 1;
                primeScore[idx] = score;
                primeScoreMap.put(nums.get(idx), score);
            }

        }
        // print(primeScore);
        // have to compute for all index left and right where prime score is higher than
        // it
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> nums.get(b) - nums.get(a));
        for (int idx = 0; idx < n; ++idx) {
            pq.offer(idx);
        }
        long result = 1;
        int[][] prevAndNext = findPrevAndNext(primeScore);
        // print(prevAndNext);
        int countLeft = k;
        while (countLeft >= 1) {
            if (pq.size() == 0)
                break;
            int top = pq.poll();
            int left = prevAndNext[top][0];
            int right = prevAndNext[top][1];
            // you will have left + 1 to mid to right - 1
            long possibleSub = (long) (top - left) * (long) (right - top);
            long subToCreate = Math.min(countLeft, possibleSub);
            long scoreMult = Pow(nums.get(top), subToCreate);
            // System.out.println("for " + nums.get(top) + " total " + possibleSub + " score
            // mult " + scoreMult);
            result = (result * scoreMult) % mod;
            countLeft -= subToCreate;
        }
        return (int) result;
    }

    public long Pow(long x, long n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        long half = Pow(x, n / 2);
        long res = (half * half) % mod;
        if (n % 2 == 1) {
            res = (res * x) % mod;
        }
        return res;

    }

    // next biggest element and previos same or largest element
    public int[][] findPrevAndNext(int[] scores) {
        int n = scores.length;
        int[][] result = new int[n][2];
        Deque<Integer> decreasing = new ArrayDeque<Integer>();
        for (int idx = 0; idx <= n; ++idx) {
            while (decreasing.size() != 0 && (idx == n || scores[decreasing.peekLast()] < scores[idx])) {
                int top = decreasing.pollLast();
                int right = idx;
                result[top][1] = right;
            }
            if (idx != n)
                result[idx][0] = decreasing.size() == 0 ? -1 : decreasing.peekLast();
            decreasing.offerLast(idx);
        }
        return result;
    }
}
