package Solutions.Greedy;

//Same as 678. Valid Parenthesis String except odd length is invalid
//2116. Check if a Parentheses String Can Be Valid
//https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid
public class CheckIfParenthesesCanBeValid {

    public boolean canBeValid(String s, String locked) {
        int open = 0;
        int closed = 0;
        int n = s.length();
        if (n % 2 == 1)
            return false;
        for (int idx = 0; idx < n; ++idx) {
            char left = s.charAt(idx);
            char right = s.charAt(n - idx - 1);
            if (left == '(' || locked.charAt(idx) == '0') {
                open++;
            } else {
                open--;
                if (open < 0)
                    return false;
            }
            if (right == ')' || locked.charAt(n - idx - 1) == '0') {
                closed++;
            } else {
                closed--;
                if (closed < 0)
                    return false;
            }
        }
        return true;
    }
}
