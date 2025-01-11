package Solutions.String;

//https://leetcode.com/problems/construct-k-palindrome-strings/
//1400. Construct K Palindrome Strings
public class ConstructKPalindrome {
    // Simple maths every palindrome can at most have one character with odd count
    public boolean canConstruct(String s, int k) {
        if (s.length() < k)
            return false;
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        int oddCount = 0;
        for (int elem : count) {
            if (elem % 2 == 1) {
                oddCount++;
            }
        }
        if (oddCount > k)
            return false;
        return true;

    }
}
