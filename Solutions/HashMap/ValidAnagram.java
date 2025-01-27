package Solutions.HashMap;

//242. Valid Anagram
//https://leetcode.com/problems/valid-anagram
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] counter = new int[26];

        for (char ch : s.toCharArray()) {
            counter[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            counter[ch - 'a']--;
        }
        for (int idx = 0; idx < 26; ++idx) {
            if (counter[idx] != 0)
                return false;
        }
        return true;

    }
}
