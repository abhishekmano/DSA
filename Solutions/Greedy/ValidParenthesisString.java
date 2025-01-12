package Solutions.Greedy;

//678. Valid Parenthesis String
//https://leetcode.com/problems/valid-parenthesis-string
public class ValidParenthesisString {
    // iterate from both sides and check whether count becomes negative at any point
    // checking from both side is mandatory
    public boolean checkValidString(String s) {
        int open = 0;
        int closed = 0;
        int n = s.length();
        for (int idx = 0; idx < n; ++idx) {
            char left = s.charAt(idx);
            char right = s.charAt(n - idx - 1);
            if (left == '(' || left == '*') {
                open++;
            } else {
                open--;
                if (open < 0)
                    return false;
            }
            if (right == ')' || right == '*') {
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
