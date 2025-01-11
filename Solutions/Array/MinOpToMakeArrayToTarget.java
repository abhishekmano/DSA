package Solutions.Array;

//3229. Minimum Operations to Make Array Equal to Target
//https://leetcode.com/problems/minimum-operations-to-make-array-equal-to-target/
public class MinOpToMakeArrayToTarget {
    public long minimumOperations(int[] nums, int[] target) {
        long res = 0;
        // Stores current increment or decrement value
        long incr = 0;
        long decr = 0;
        int n = nums.length;
        for (int idx = 0; idx < n; ++idx) {
            var diff = target[idx] - nums[idx];
            if (diff > 0) {
                // if the current difference is higher than the increment value then only more
                // increment is needed
                if (diff > incr) {
                    res += (diff - incr);
                    // res += diff;
                    // res -= incr;
                }
                incr = diff;
                decr = 0;
            }
            // gotta reduce
            else if (diff < 0) {
                // but the difference is smaller than what we are decreasing
                if (diff < decr) {
                    // since it is a negative value
                    res += (decr - diff);
                    // res -= diff;
                    // res += decr;
                }
                incr = 0;
                decr = diff;
            } else {
                incr = 0;
                decr = 0;
            }
        }
        return res;

    }

    // Or handle positive and negative seperately
    public long minimumOperations2(int[] nums, int[] target) {
        int n = nums.length;
        long[] diff = new long[n];
        long[] diffNeg = new long[n];
        for (int idx = 0; idx < n; ++idx) {
            diff[idx] = target[idx] - nums[idx];
            if (diff[idx] < 0) {
                diffNeg[idx] = -diff[idx];
                diff[idx] = 0;
            }
        }
        long pos = MinOp(diff);
        long neg = MinOp(diffNeg);
        return pos + neg;
    }

    public long MinOp(long[] target) {
        long total = target[0];
        long lastUsed = target[0];
        int n = target.length;
        for (int idx = 1; idx < n; ++idx) {
            // Increase the totalReusable to highest
            // Total increase by the difference
            // 2 , 4
            // initially total = 2 ;
            // total += 2 + (4 - 2);
            if (target[idx] > lastUsed) {
                total += (target[idx] - lastUsed);
                lastUsed = target[idx];
            }
            // Encountered a small number last Resuable is reduced
            else {
                lastUsed = target[idx];
            }
        }
        return total;
    }
}
