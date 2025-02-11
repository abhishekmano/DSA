package Algorithms.String;

// Knuth-Morris-Pratt algorithm to check if `needle` is in `haystack` or not
public class KMPPatternMatching {
    public boolean contains(String s, String pattern) {
        int[] lps = lps(pattern);
        int n = s.length();
        int m = pattern.length();
        int pidx = 0;
        for (int idx = 0; idx < n; ++idx) {
            if (s.charAt(idx) == pattern.charAt(pidx)) {
                ++pidx;
                if (pidx == m)
                    return true;
            } else {
                if (pidx != 0) {
                    pidx = lps[pidx - 1];
                    idx--;
                }
            }
        }
        return false;
    }

    public int[] lps(String pattern) {
        int n = pattern.length();
        int[] result = new int[n];
        int prev = 0;
        for (int idx = 1; idx < n; ++idx) {
            if (pattern.charAt(idx) == pattern.charAt(prev)) {
                prev++;
                result[idx] = prev;
            } else if (prev == 0) {
                // nothing we need to do
                result[idx] = 0;
            } else {
                prev = result[prev - 1];
                idx--;
            }
        }
        return result;
    }

}
