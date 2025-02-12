package Solutions.String.PatternMatching;

//https://leetcode.com/problems/repeated-string-match/
//686. Repeated String Match
public class RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        int[] kmplps = lps(b);
        int n = b.length();
        StringBuilder combinedString = new StringBuilder(a);
        int count = 1;
        while (combinedString.length() < n) {
            combinedString.append(a);
            count++;
        }
        if (contains(combinedString.toString(), b, kmplps))
            return count;
        if (contains(combinedString.append(a).toString(), b, kmplps))
            return count + 1;
        return -1;
    }

    public boolean contains(String s, String pattern, int[] lps) {
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
