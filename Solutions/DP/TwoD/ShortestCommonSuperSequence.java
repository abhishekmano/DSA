package Solutions.DP.TwoD;

//1092. Shortest Common Supersequence 
//https://leetcode.com/problems/shortest-common-supersequence
public class ShortestCommonSuperSequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        // Idea is first we have to find LCS of both strings
        // Iterate through both if LCS is not there add it to the result;
        String common = lcs(str1, str2);
        StringBuilder result = new StringBuilder();
        int m = str1.length();
        int n = str2.length();
        int c = common.length();
        int ptr1 = 0;
        int ptr2 = 0;
        int ptrc = 0;
        // System.out.println(common);
        while (ptrc < c) {
            while (str1.charAt(ptr1) != common.charAt(ptrc)) {
                result.append(str1.charAt(ptr1));
                ptr1++;
            }
            while (str2.charAt(ptr2) != common.charAt(ptrc)) {
                result.append(str2.charAt(ptr2));
                ptr2++;
            }
            result.append(common.charAt(ptrc));
            ptrc++;
            ptr1++;
            ptr2++;
        }
        while (ptr1 < m) {
            result.append(str1.charAt(ptr1));
            ptr1++;
        }
        while (ptr2 < n) {
            result.append(str2.charAt(ptr2));
            ptr2++;
        }
        return result.toString();
    }

    public String lcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][j] means count of common string in a[:i] and b[:j]
        for (int idx = 1; idx <= m; ++idx) {
            for (int idy = 1; idy <= n; ++idy) {
                if (a.charAt(idx - 1) == b.charAt(idy - 1)) {
                    dp[idx][idy] = dp[idx - 1][idy - 1] + 1;
                } else {
                    dp[idx][idy] = Math.max(dp[idx][idy - 1], dp[idx - 1][idy]);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        int idx = m;
        int idy = n;
        while (idx >= 1 && idy >= 1) {
            if (a.charAt(idx - 1) == b.charAt(idy - 1)) {
                result.append(a.charAt(idx - 1));
                idx--;
                idy--;
            } else {
                if (idx - 1 >= 0 && idy - 1 >= 0 && dp[idx - 1][idy] > dp[idx][idy - 1]) {
                    idx--;
                } else {
                    idy--;
                }
            }
        }
        return result.reverse().toString();
    }
}
