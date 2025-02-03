package Solutions.String;

//1768. Merge Strings Alternately
//https://leetcode.com/problems/merge-strings-alternately
public class MergeStringAlternative {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder res = new StringBuilder();
        int m = word1.length();
        int n = word2.length();
        for (int idx = 0; idx < Math.min(m, n); ++idx) {
            res.append(word1.charAt(idx));
            res.append(word2.charAt(idx));
        }
        if (m < n) {
            res.append(word2.substring(m));
        } else if (n < m) {
            res.append(word1.substring(n));
        }
        return res.toString();
    }

    public String mergeAlternately2(String word1, String word2) {
        StringBuilder res = new StringBuilder();
        int m = word1.length();
        int n = word2.length();
        int ptr1 = 0;
        int ptr2 = 0;
        boolean first = true;
        while (ptr1 < m && ptr2 < n) {
            if (first) {
                res.append(word1.charAt(ptr1));
                ptr1++;
            } else {
                res.append(word2.charAt(ptr2));
                ptr2++;
            }
            first = !first;
        }
        if (ptr1 < m) {
            res.append(word1.substring(ptr1, m));
        }
        if (ptr2 < n) {
            res.append(word2.substring(ptr2, n));
        }
        return res.toString();
    }
}
