package Solutions.String.PatternMatching;

import java.util.Stack;

public class RemoveAllOccurrence {
    public String removeOccurrences(String s, String part) {
        int n = s.length();
        int m = part.length();
        int[] kmpLPS = lps(part);
        Stack<Character> stack = new Stack<>();
        int[] pIndex = new int[n + 1];
        for (int idx = 0, pidx = 0; idx < n; ++idx) {
            char ch = s.charAt(idx);
            stack.push(ch);
            // there is a match
            if (ch == part.charAt(pidx)) {
                pidx++;
                pIndex[stack.size()] = pidx;
                // complete match
                if (pidx == m) {
                    for (int count = 0; count < m; ++count)
                        stack.pop();
                    pidx = stack.size() == 0 ? 0 : pIndex[stack.size()];
                }

            }
            // mismatch
            else {
                // it was first character to match
                if (pidx == 0) {
                    pIndex[stack.size()] = 0;
                } else {
                    pidx = kmpLPS[pidx - 1];
                    idx--;
                    stack.pop();
                }
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }

    public int[] lps(String part) {
        int n = part.length();
        int[] longestPS = new int[n];
        longestPS[0] = 0;
        int prev = 0;
        for (int idx = 1; idx < n; ++idx) {
            if (part.charAt(idx) == part.charAt(prev)) {
                prev++;
                longestPS[idx] = prev;
            } else if (prev == 0) {
                longestPS[idx] = 0;
            } else {
                prev = longestPS[prev - 1];
                idx--;
            }
        }
        return longestPS;
    }
}
