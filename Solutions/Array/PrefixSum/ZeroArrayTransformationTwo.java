package Solutions.Array.PrefixSum;
//3356. Zero Array Transformation II

//https://leetcode.com/problems/zero-array-transformation-ii

//Copied From solution since found it helpful
// Similarly, let sum be the maximum total increase/decrease at each index i.
// For each nums[i], We greedily expand k until sum >= nums[i].
// If we exhaust all queries but there're still some numbers bigger than 0, return -1.

// Consider an index i and a query in the form [l, r, val], there're 3 scenarios:
// (1) i < l, the query may be helpful later.
// (2) l <= i <= r, the query can be used right now.
// (3) r < i, the query is useless afterwards.
public class ZeroArrayTransformationTwo {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] sum = new int[n];
        int m = queries.length;
        int index = 0;
        for (int idx = 0; idx < n; ++idx) {
            if (idx != 0)
                sum[idx] += sum[idx - 1];
            while (sum[idx] + nums[idx] > 0) {
                if (index == m)
                    return -1;
                int[] q = queries[index];
                int l = q[0];
                int r = q[1];
                int v = q[2];
                // useless query
                if (r < idx) {
                    index++;
                    continue;
                }
                // current window
                if (l <= idx && idx <= r) {
                    sum[idx] -= v;
                    if (r + 1 < n)
                        sum[r + 1] += v;
                    index++;
                } else {
                    sum[l] -= v;
                    if (r + 1 < n)
                        sum[r + 1] += v;
                    index++;
                }
            }
        }
        return index;
    }
}
