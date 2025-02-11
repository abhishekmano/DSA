package Algorithms.String;

public class RabinKarp {
    private static final int MOD = 100000;
    private static final int BASE = 31;

    public boolean rabinKarp(String s, String pattern) {
        int patternHash = 0;
        int h = 1;
        int runningHash = 0;
        int n = pattern.length();
        int m = s.length();
        for (int idx = 1; idx < m; ++idx) {
            h = (h * BASE) % MOD;
        }
        for (int idx = 0; idx < n; ++idx) {
            int digit = s.charAt(idx) - 'a';
            patternHash = ((patternHash * BASE) + digit) % MOD;
        }
        // Calculate the hash for the pattern and initial window
        for (int idx = 0; idx < n; ++idx) {
            runningHash = ((runningHash * BASE) + s.charAt(idx) - 'a') % MOD;
        }

        // Check if the initial window matches the pattern
        if (runningHash == patternHash) {
            if (s.substring(0, n).equals(pattern))
                return true;
        }

        // Process the rest of the string
        for (int idx = n; idx < s.length(); ++idx) {
            int deducted = (runningHash - (s.charAt(idx - n) - 'a') * h) % MOD;
            int addBack = deducted * BASE + s.charAt(idx) - 'a';
            runningHash = addBack % MOD;
            if (runningHash < 0)
                runningHash += MOD;
            if (runningHash == patternHash) {
                if (s.substring(idx - n + 1, n).equals(pattern))
                    return true;
            }
        }

        return false;
    }
}
