package Solutions.Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

//2297. Jump Game VIII
//https://leetcode.com/problems/jump-game-viii/
public class JumpGameEight {
    // using monotonic stack and DP to find minJump
    public long minCost2(int[] nums, int[] costs) {
        // from any index you can jump to next highest or equal
        // from next smallest
        // find minimum cost
        int[] jumpHigh = jumpNext(nums);
        int[] jumpSmall = jumpNextSmall(nums);
        int n = nums.length;
        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[0] = 0;
        for (int idx = 0; idx < n; ++idx) {
            if (minCost[idx] == Long.MAX_VALUE)
                continue;
            int nextS = jumpSmall[idx];
            int nextH = jumpHigh[idx];
            if (nextS != -1) {
                minCost[nextS] = Math.min(minCost[nextS], minCost[idx] + costs[nextS]);
            }
            if (nextH != -1) {
                minCost[nextH] = Math.min(minCost[nextH], minCost[idx] + costs[nextH]);
            }
        }
        return minCost[n - 1] == Long.MAX_VALUE ? -1 : minCost[n - 1];

    }

    // this solution uses monotonic stack and pq
    // runs in n log n but i guess can p solved in dp as well in n
    public long minCost(int[] nums, int[] costs) {
        // from any index you can jump to next highest or equal
        // from next smallest
        // find minimum cost
        int[] jumpHigh = jumpNext(nums);
        int[] jumpSmall = jumpNextSmall(nums);
        int n = nums.length;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> (int) (a.cost - b.cost));
        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[0] = 0;
        pq.offer(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair top = pq.remove();
            if (minCost[top.index] < top.cost)
                continue;
            minCost[top.index] = top.cost;
            int jump1 = jumpHigh[top.index];
            if (jump1 != -1) {
                long cost = top.cost + costs[jump1];
                if (cost < minCost[jump1]) {
                    minCost[jump1] = cost;
                    pq.offer(new Pair(jump1, cost));
                }
            }
            int jump2 = jumpSmall[top.index];
            if (jump2 != -1) {
                long cost = top.cost + costs[jump2];
                if (cost < minCost[jump2]) {
                    minCost[jump2] = cost;
                    pq.offer(new Pair(jump2, cost));
                }
            }
        }
        return minCost[n - 1] == Long.MAX_VALUE ? -1 : minCost[n - 1];

    }

    public record Pair(int index, long cost) {
    };

    // higher jump can be found using a mono decreasing stack
    public int[] jumpNext(int[] num) {
        int n = num.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> mono = new ArrayDeque<Integer>();
        for (int idx = 0; idx < n; ++idx) {
            while (!mono.isEmpty() && (num[idx] >= num[mono.peek()])) {
                var top = mono.pop();
                res[top] = idx;
            }
            mono.push(idx);
        }
        return res;

    }

    // jumpnext small should have mono incrasing
    public int[] jumpNextSmall(int[] num) {
        int n = num.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> mono = new ArrayDeque<Integer>();
        for (int idx = 0; idx < n; ++idx) {
            while (!mono.isEmpty() && (num[idx] < num[mono.peek()])) {
                var top = mono.pop();
                res[top] = idx;
            }
            mono.push(idx);
        }
        return res;

    }
}
