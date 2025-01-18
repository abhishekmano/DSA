package Solutions.DP.OneD;

import java.util.HashSet;
import java.util.List;

//139. Word Break
//https://leetcode.com/problems/word-break
public class WordBreak {
    // O(n^3+m⋅k) time mk is for converting into set
    // O(n + m.k) space
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        HashSet<String> words = new HashSet<>();
        for (String word : wordDict)
            words.add(word);
        dp[0] = true;
        for (int idx = 1; idx <= n; ++idx) {
            for (int idy = 0; idy <= idx; ++idy) {
                if (dp[idy] && words.contains(s.substring(idy, idx))) {
                    dp[idx] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
