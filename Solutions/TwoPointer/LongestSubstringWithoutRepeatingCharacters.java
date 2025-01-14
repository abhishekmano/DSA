package Solutions.TwoPointer;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    // Uses and optimized version which check the last seen of duplicate character
    // here highest is the left of the window
    public int lengthOfLongestSubstring3(String s) {
        int result = 0;
        int[] lastSeen = new int[128];
        int highest = 0;
        for (int right = 0; right < s.length(); ++right) {
            char ch = s.charAt(right);
            highest = Math.max(highest, lastSeen[ch]);
            result = Math.max(result, right - highest + 1);
            lastSeen[ch] = right + 1;
        }
        return result;
    }
    // both of these uses creating a valid window and then taking the max count
    // we check longest without repeating at every index

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int result = 0;
        boolean[] seen = new boolean[128];
        for (int right = 0; right < s.length(); ++right) {
            char ch = s.charAt(right);
            if (seen[ch]) {
                while (s.charAt(left) != ch) {
                    seen[s.charAt(left)] = false;
                    left++;
                }
                left++;
            } else {
                seen[ch] = true;
            }
            result = Math.max(result, right - left + 1);

        }
        return result;
    }

    public int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int result = 0;
        HashSet<Character> seen = new HashSet<Character>();
        for (int right = 0; right < s.length(); ++right) {
            char ch = s.charAt(right);
            if (seen.contains(ch)) {
                while (s.charAt(left) != ch) {
                    seen.remove(s.charAt(left));
                    left++;
                }
                left++;
            } else {
                seen.add(ch);
            }
            // System.out.println("left : " + left + " right: " + right);
            result = Math.max(result, right - left + 1);

        }
        return result;
    }
}
