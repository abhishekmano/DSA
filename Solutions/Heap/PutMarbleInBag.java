package Solutions.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

//2551. Put Marbles in Bags
//https://leetcode.com/problems/put-marbles-in-bags
public class PutMarbleInBag {
    // N Log N
    public long putMarbles(int[] weights, int k) {
        // we have to choose k - 1 values which are max and min to calculate
        int n = weights.length;
        if (n == 1 || n == 2)
            return 0;
        int[] cost = new int[n - 1];
        // first and last weight will be in all result so ignore it
        // it will remove when we subtract max and min
        // at every other point if we divide further the cost that get added to the
        // result is
        // current weight + next weight
        // except last element we cannot further divide
        for (int idx = 0; idx < n - 1; ++idx) {
            cost[idx] = weights[idx] + weights[idx + 1];

        }
        long result = 0;
        Arrays.sort(cost);
        for (int idx = 0; idx < k - 1; ++idx) {
            result += cost[n - 2 - idx];
            result -= cost[idx];
        }
        return result;
        // you gotta pick k-1 points

    }

    // N Log K Time
    public long putMarbles2(int[] weights, int k) {
        // we have to choose k - 1 values which are max and min to calculate
        int n = weights.length;
        if (n == 1 || n == 2)
            return 0;
        int cost;
        PriorityQueue<Integer> min = new PriorityQueue<Integer>();
        PriorityQueue<Integer> max = new PriorityQueue<Integer>((a, b) -> b - a);
        // first and last weight will be in all result so ignore it
        // it will remove when we subtrack max and min
        for (int idx = 0; idx < n - 1; ++idx) {
            cost = 0;
            cost += weights[idx];
            cost += weights[idx + 1];
            min.offer(cost);
            max.offer(cost);
            if (min.size() > k - 1)
                min.remove();
            if (max.size() > k - 1)
                max.remove();

        }
        long maxScore = 0;
        long minScore = 0;
        while (min.size() != 0) {
            maxScore += min.poll();
            minScore += max.poll();
        }
        return maxScore - minScore;
        // you gotta pick k-1 points

    }
}
