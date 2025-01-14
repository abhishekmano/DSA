package Solutions.TwoPointer;

//125. Valid Palindrome
//https://leetcode.com/problems/valid-palindrome/description
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0;
        int right = n - 1;
        while (left < right) {
            char start = s.charAt(left);
            char end = s.charAt(right);
            if (!Character.isLetterOrDigit(start)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(end)) {
                right--;
                continue;
            }
            start = Character.toLowerCase(start);
            end = Character.toLowerCase(end);
            if (start != end) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
