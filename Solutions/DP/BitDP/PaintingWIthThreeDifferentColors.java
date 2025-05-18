package Solutions.DP.BitDP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//https://leetcode.com/problems/painting-a-grid-with-three-different-colors/
//1931. Painting a Grid With Three Different Colors
public class PaintingWIthThreeDifferentColors {
    public int colorTheGrid(int m, int n) {
        int MOD = 1_000_000_007;
        // since m = 5 the row color can be expressed as X-X-X-X-X where X = 0 , 1, 2
        // max value is 3^m;
        int maxValue = (int) Math.pow(3, m);
        HashSet<Integer> masks = new HashSet<>();
        // Find all possible masks which doesnt have same color in adj
        for (int num = 0; num < maxValue; ++num) {
            int value = num;
            int prev = -1;
            boolean valid = true;
            for (int idx = 0; idx < m; ++idx) {
                int digit = value % 3;
                if (digit == prev) {
                    valid = false;
                    break;
                }
                prev = digit;
                value /= 3;
            }
            if (valid) {
                masks.add(num);
            }
        }
        // For each valid row painting find its possible other rows
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int mask1 : masks) {
            adj.put(mask1, new ArrayList<Integer>());
            for (int mask2 : masks) {
                if (!hasConflict(mask1, mask2, m)) {
                    adj.get(mask1).add(mask2);
                }
            }
        }
        // create a map of mask to count in each row we have to do it n times;
        HashMap<Integer, Integer> dp = new HashMap<>();
        for (int mask : masks) {
            dp.put(mask, 1);
        }
        for (int idx = 1; idx < n; ++idx) {
            HashMap<Integer, Integer> newdp = new HashMap<>();
            for (int mask : masks) {
                int total = 0;
                for (int ad : adj.get(mask)) {
                    total = (total + dp.get(ad)) % MOD;
                }
                newdp.put(mask, total);
            }
            dp = newdp;
        }
        int result = 0;
        for (int num : dp.values()) {
            result = (result + num) % MOD;
        }
        return result;
    }

    public boolean hasConflict(int mask1, int mask2, int m) {
        for (int idx = 0; idx < m; ++idx) {
            int a = mask1 % 3;
            int b = mask2 % 3;
            if (a == b)
                return true;
            mask1 /= 3;
            mask2 /= 3;
        }
        return false;
    }
}
