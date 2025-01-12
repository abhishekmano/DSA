package Solutions.HashMap;

import java.util.HashSet;

//2168. Unique Substrings With Equal Digit Frequency
//https://leetcode.com/problems/unique-substrings-with-equal-digit-frequency/
public class UniqueSubString {
    // Rolling Hash to improve performance
    // since string hash can be Slow
    public int equalDigitFrequency(String s) {
        HashSet<Long> unique = new HashSet();
        int n = s.length();
        long prime = 31;
        long mod = 10_000_000L;
        for (int idx = 0; idx < n; ++idx) {
            int[] count = new int[10];
            long hash = 0;
            for (int idy = idx; idy < n; ++idy) {
                int digit = s.charAt(idy) - '0';
                count[digit]++;
                hash = (hash * prime + digit + 1) % mod;
                if (isAllSame(count)) {
                    unique.add(hash);
                }

            }
        }
        return unique.size();
    }

    public int equalDigitFrequency2(String s) {
        HashSet<String> unique = new HashSet();
        int n = s.length();
        for (int idx = 0; idx < n; ++idx) {
            int[] count = new int[10];
            for (int idy = idx; idy < n; ++idy) {
                count[s.charAt(idy) - '0']++;
                if (isAllSame(count)) {
                    String sub = s.substring(idx, idy + 1);
                    unique.add(sub);
                }
            }
        }
        return unique.size();
    }

    public boolean isAllSame(int[] count) {
        int nonZero = 0;
        for (int num : count) {
            if (num != 0) {
                if (nonZero != 0 && num != nonZero) {
                    return false;
                }
                nonZero = num;
            }
        }
        return true;
    }
}
